package com.hcx.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author 贺楚翔
 * @Date 2019-12-03 13:30
 * @Version 1.0
 **/
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hcx")
    public String hello(){
        return "hcx";
    }

    @PostMapping("/user/login")
    public String toList(@RequestParam("username") String username, @RequestParam("password") String password, Map<String,Object> map, HttpSession session){
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            map.put("msg","用户名面错误");
            return "login";
        }
    }
}
