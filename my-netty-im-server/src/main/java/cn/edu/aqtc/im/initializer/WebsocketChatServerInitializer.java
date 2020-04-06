package cn.edu.aqtc.im.initializer;

import cn.edu.aqtc.im.handler.TextWebSocketFrameHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @ClassName : WebsocketChatServerInitializer
 * @Description : websocket渠道初始化器
 * @Author : zhangjj
 * @Date: 2020-04-04
 */
public class WebsocketChatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //websocket协议本身是基于Http协议的，所以需要Http解码器
        pipeline.addLast(new HttpServerCodec());
        //netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
        pipeline.addLast(new HttpObjectAggregator(64*1024));
        //以块的方式来写的处理器
        pipeline.addLast(new ChunkedWriteHandler());
        //pipeline.addLast(new HttpRequestHandler("/ws"));
        //这个是websocket的handler，是netty提供的
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
