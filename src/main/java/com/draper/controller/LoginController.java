package com.draper.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Controller
public class LoginController {

    @PostMapping("/user2/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, String> map,
                        HttpSession session){
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            log.trace("登录成功");
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        } else {
            map.put("msg","用户名密码错误");
            log.trace("登录失败");
            return "login";
        }
    }

}
