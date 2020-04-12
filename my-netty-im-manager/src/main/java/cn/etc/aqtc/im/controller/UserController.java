package cn.etc.aqtc.im.controller;

import cn.edu.aqtc.im.bean.RestResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RestResult login(){
        return RestResult.getSuccessRestResult();
    }
}
