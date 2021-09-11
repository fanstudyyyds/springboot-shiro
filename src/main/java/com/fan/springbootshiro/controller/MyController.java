package com.fan.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @author  fan
 * @date  2021/9/11 14:22
 * @aa  玉树临风美少年,揽镜自顾夜不眠
 */
@Controller
public class MyController {

    @RequestMapping("/")
    public String toIndex() {
        return "/index";
    }

    @RequestMapping("/user/add")
    public String add() {
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String update() {
        return "/user/update";
    }

    @RequestMapping("/tologin")
    public String toLogin() {
        return "/login";
    }

    @RequestMapping("/login")
    public String login(String username, String passwoed, Model model) {
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, passwoed);
        try {
            subject.login(token);
            return "/index";
        } catch (UnknownAccountException e) {
           model.addAttribute("msg","用户名错误");
            return "/login";
        }
        catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","密码错误");
            return "/login";
        }
    }


    @RequestMapping("/noauth")
    public String noauth() {
        return "/noauth";
    }

}
