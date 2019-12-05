package com.hcx.springbootweb.config;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * @ClassName MyErrorAttributes
 * @Description TODO  自定义错误信息组件，将用户自定义错误信息返回
 * @Author 贺楚翔
 * @Date 2019-12-05 15:44
 * @Version 1.0
 **/
@Component
public class MyErrorAttributes  extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {

        Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
        errorAttributes.put("Author","hcx");
        Map<String,Object> hgg = (Map<String, Object>) requestAttributes.getAttribute("hgg", 0);
        errorAttributes.put("other",hgg);
        return errorAttributes;

    }
}
