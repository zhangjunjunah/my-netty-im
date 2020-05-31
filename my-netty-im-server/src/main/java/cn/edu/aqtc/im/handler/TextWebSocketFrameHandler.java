package cn.edu.aqtc.im.handler;

import cn.edu.aqtc.im.cache.UserChannelCache;
import cn.edu.aqtc.im.protocol.MessagePayload;
import cn.edu.aqtc.im.serivce.MessageDispatcher;
import cn.edu.aqtc.im.service.impl.ConversationService;
import cn.edu.aqtc.im.util.SpringUtils;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ClassName : TextWebSocketFrameHandler
 * @Description : websocket文本处理器
 * @Author : zhangjj
 * @Date: 2020-04-05
 */
@Slf4j
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<Object> {


    private MessageDispatcher messageDispatcher;

    private ConversationService conversationService;

    static {
        UserChannelCache.channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    //存储ip和channel的容器
    private static ConcurrentMap<String, Channel> channelMap = new ConcurrentHashMap<>();


    public TextWebSocketFrameHandler() {
        super();
        messageDispatcher = SpringUtils.getBean(MessageDispatcher.class);
        conversationService = SpringUtils.getBean(ConversationService.class);
    }

    /**
     * @Description 未注册状态
     * @Author zhangjj
     * @param ctx
     * @return void
     * @Date 2020-04-05
     **/
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("client :{},wait connect", getClientId(ctx));
        super.channelUnregistered(ctx);
    }

    private ChannelId getClientId(ChannelHandlerContext ctx) {
        return ctx.channel().id();
    }

    /**
     * @Description  Handler活跃状态，表示连接成功
     * @Author zhangjj
     * @param ctx:
     * @return void
     * @Date 2020-04-05
     **/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client:{},connect success", getClientId(ctx));
        UserChannelCache.channelGroup.add(ctx.channel());
    }

    /**
     * @Description  
     * @param ctx
     * @throws 
     * @return void
     * @Date 2020-04-05
     * @Author zhangjj
     **/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("client:{},connect close", getClientId(ctx));
        UserChannelCache.channelGroup.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("client:{} connect exception,exception msg:", getClientId(ctx), cause);
        UserChannelCache.channelGroup.remove(ctx.channel());
        ctx.close();
    }

    /**
     * @Description 读取消息
     * @Author zhangjj
     * @param channelHandlerContext
	 * @param msg
     * @return void
     * @Date 2020-04-05
     **/
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        //文本消息
        if (msg instanceof TextWebSocketFrame) {
            //获取当前channel绑定的IP地址
            InetSocketAddress ipSocket = (InetSocketAddress)channelHandlerContext.channel().remoteAddress();
            String address = ipSocket.getAddress().getHostAddress()+":"+ipSocket.getPort();
            log.info("address:{}",address);
            //将IP和channel的关系保存
            if (!channelMap.containsKey(address)){
                channelMap.put(address,channelHandlerContext.channel());
            }
            MessagePayload messagePayload = JSONObject.parseObject(((TextWebSocketFrame) msg).text(), MessagePayload.class);
            messagePayload.setChannel(channelHandlerContext.channel());
            messageDispatcher.dispatch(messagePayload);


        }
        //二进制消息
        if (msg instanceof BinaryWebSocketFrame) {
            log.info("receive binary msg：{}" , ((BinaryWebSocketFrame) msg).content().readableBytes());
            BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(Unpooled.buffer().writeBytes("hello".getBytes()));
            //给客户端发送的消息
            channelHandlerContext.channel().writeAndFlush(binaryWebSocketFrame);
        }
        //ping消息
        if (msg instanceof PongWebSocketFrame) {
            log.info("client pong success");
        }
        //关闭消息
        if (msg instanceof CloseWebSocketFrame) {
            log.info("client:{} close，channel close", getClientId(channelHandlerContext));
            Channel channel = channelHandlerContext.channel();
            channel.close();
        }
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            log.info("channel:{}, userEventTriggered", ctx.channel().id());
            //用户下线
            conversationService.userOffline(ctx.channel());
            ctx.channel().close();
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    public void sendMessage(String address) {
        Channel channel = channelMap.get(address);
        String message = "hello client:" + channel.id() + " ,welcome my-netty-im";
        channel.writeAndFlush(new TextWebSocketFrame(message));
    }

    private void sendMessageAll(ChannelHandlerContext channelHandlerContext) {
        String message = "群发信息,client " + getClientId(channelHandlerContext) + " on line";
        UserChannelCache.channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }
}
