package com.draper.config.exception;

import com.draper.domain.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    // 交给 Spring MVC 的 error 处理机制，将 json 暴露出去
    @ExceptionHandler(JsonValueException.class)
    public String defaultJsonErrorHandler(HttpServletRequest request,Exception e){

        log.trace("JsonValueException 处理 [{}]", e.getMessage());

        Map<String,Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        map.put("exception", e.getMessage());
        map.put("url", request.getRequestURL());
        request.setAttribute("javax.servlet.error.status_code", 500);
        request.setAttribute("ext", map);
        return "forward:/error";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        log.trace("Exception 处理 [{}]",e.getMessage());

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        e.printStackTrace();
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    // 直接通过 ResponseBody 的方式将 json 暴露出去
    @ResponseBody
    @ExceptionHandler(MyException.class)
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e){

        log.trace("MyException 处理 [{}]", e.getMessage());

        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("some data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}
