package com.hcx.springbootweb.exception;

/**
 * @ClassName UserNotExistException
 * @Description TODO  自定义异常
 * @Author 贺楚翔
 * @Date 2019-12-05 14:47
 * @Version 1.0
 **/
public class UserNotExistException extends RuntimeException{

    public UserNotExistException() {
        super("用户不存在！");
    }
}
