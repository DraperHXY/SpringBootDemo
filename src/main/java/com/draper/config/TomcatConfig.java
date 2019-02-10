package com.draper.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    // 同样可以在配置文件中配置
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
//        factory.setPort(9999);
    }

}
