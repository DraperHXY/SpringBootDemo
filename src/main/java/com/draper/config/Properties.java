package com.draper.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Properties {

    @Value("${com.draper.name}")
    private String name;

    @Value("${com.draper.title}")
    private String title;

}
