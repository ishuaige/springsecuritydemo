package com.niuma.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author niuma
 * @create 2023-01-27 13:49
 */
@RestController
public class AuthController {

    @RequestMapping("login-success")
    public String loginSuccess() {
        return "登录成功咯！";
    }

    @RequestMapping("/r/r1")
    public String getR1() {
        return "获取r1资源！";
    }

    @RequestMapping("/r/r2")
    public String getR2() {
        return "获取r2资源！";
    }
}
