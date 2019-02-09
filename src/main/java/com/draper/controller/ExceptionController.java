package com.draper.controller;

import com.draper.config.exception.JsonValueException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/exception")
@Controller
public class ExceptionController {

    @GetMapping("/defaultJsonException")
    public String defaultJsonException() throws JsonValueException {
        log.info("defaultJsonException");
        throw new JsonValueException("测试 json Exception");
    }

}
