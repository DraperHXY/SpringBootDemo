package com.draper.config.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        // SpringBoot 基本 信息
        Map<String,Object> map  = super.getErrorAttributes(webRequest, includeStackTrace);

        // 放在 request 中，我们配置额外的信息
        Map<String,Object> extMap = (Map<String, Object>) webRequest.getAttribute("ext", RequestAttributes.SCOPE_REQUEST);
        map.put("ext", extMap);

        return map;
    }
}
