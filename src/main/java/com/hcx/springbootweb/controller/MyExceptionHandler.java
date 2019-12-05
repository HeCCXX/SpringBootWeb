package com.hcx.springbootweb.controller;

import com.hcx.springbootweb.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyExceptionHandler
 * @Description TODO
 * @Author 贺楚翔
 * @Date 2019-12-05 14:50
 * @Version 1.0
 **/
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();

        request.setAttribute("javax.servlet.error.status_code",404);
        map.put("code","user not exist");
        map.put("message",e.getMessage());
        request.setAttribute("hgg",map);
        return "forward:/error";
    }
}
