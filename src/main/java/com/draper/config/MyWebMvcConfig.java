package com.draper.config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 目标：实现 SpringMVC 的拓展，且并不影响其他的配置
 * <p>
 * 首先看一下 SpringMVC 的自动配置文件
 * {@link org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration}
 * 根据描述  {@code @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)}
 * 如果容器中包含 WebMvcConfigurationSupport 的组件，自动配置不会生效
 * 所以我们拓展 MVC 就不能继承 WebMvcConfigurationSupport
 * <p>
 * 其次，注意不能使用 {@link org.springframework.web.servlet.config.annotation.EnableWebMvc}
 * 这个注解的功能意味着全面接管 MVC 配置，自动配置便失效了
 * 这个注解导入了 {@link DelegatingWebMvcConfiguration}
 * 这个类继承了 {@link org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport}
 * 故无法使用自动配置
 */
//@EnableWebMvc 不要接管 SpringMVC
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 用来添加没有什么处理方法的快捷操作
        registry.addViewController("/").setViewName("login");
//        registry.addViewController("/").setViewName("index"); 奇怪的是这个同样可以生效

        registry.addViewController("/index.html").setViewName("login");
//        registry.addViewController("/user2/login").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/asserts2/**").addResourceLocations("classpath:/static/asserts2/");
//    }

    // 虽然不推荐了，但是还可以用
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/index").setViewName("index.html");
            }
        };
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user2/login","/asserts/**","/webjars/**","/asserts2/**");
    }
}
