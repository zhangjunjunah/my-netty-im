package cn.edu.aqtc.im.controller;

import cn.edu.aqtc.im.bean.ChatUser;
import cn.edu.aqtc.im.bean.RestResult;
import cn.edu.aqtc.im.entity.ImUser;
import cn.edu.aqtc.im.service.inter.IUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : UserController
 * @Description : 用户相关请求
 * @Author : zhangjj
 * @Date: 2020-04-11
 */
@RestController
@RequestMapping(value = "/api/user")
@Api(value="用户接口",tags={"用户接口"})
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RestResult login(@RequestBody ChatUser chatUser) {
        return userService.login(chatUser);


    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RestResult register(@RequestBody ImUser imUser) {
        return userService.register(imUser);
    }
}
