# feat

* Thymeleaf
* i18n
* Log Aspect
* Global Handler Exception
* Extend Spring MVC Web Config
* Interceptor
* Druid



# bug


## Swagger2 的日志太多(已解决)

```yml
logging:
  level:
    springfox.documentation: trace
```

进行这个配置并没有减少日志，不知为什么


正确的配置是
```yml
logging:
  level:
    springfox.documentation: error
```

这样将会屏蔽 springfox 在 error 级别以下的包




## 静态资源 (已解决)

同样是放在 resources/static 下

``http://localhost:8080/SpringBootDemo/asserts/css/signin.css`` 可以被访问

``http://localhost:8080/SpringBootDemo/asserts1/css/signin.css`` 不能被访问

本来计划单独试一下静态资源的单独映射，现在记录下来待解决

```java
void addResourceHandlers(ResourceHandlerRegistry registry) {
}
```



## Exception

并没有捕获相应的异常，无法查看具体的错误信息

```java
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        log.info("Exception 处理");

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ResponseBody
    @ExceptionHandler(MyException.class)
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e){

        log.info("MyException 处理");

        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("some data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}
```



## Thymeleaf

有问题待解决，为什么同样配置一会儿生效一会儿不行





# 值得注意的底层

## Data Access Layer

###  Jdbc

####``org.springframework.boot.autoconfigure.jdbc``



* ``DataSourceInitializerInvoker`` 实现 ApplicationListener 接口用来配置初始化 dataSource 前后的其他内容

* ``DataSourceInitializer`` 初始化 DataSource, Table, Data

* ``JdbcTemplateAutoConfiguration``  初始化 JdbcTemplate

* ``DataSourceConfiguration`` 用来配置连接池，默认使用 ``Hikari``

  ``` Java
  /**
   * Hikari DataSource configuration.
   */
  @ConditionalOnClass(HikariDataSource.class)
  @ConditionalOnMissingBean(DataSource.class)
  @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.zaxxer.hikari.HikariDataSource", matchIfMissing = true)
  static class Hikari {
  	@Bean
  	@ConfigurationProperties(prefix = "spring.datasource.hikari")
  	public HikariDataSource dataSource(DataSourceProperties properties) {
  		HikariDataSource dataSource = createDataSource(properties,
  				HikariDataSource.class);
  		if (StringUtils.hasText(properties.getName())) {
  			dataSource.setPoolName(properties.getName());
  		}
  		return dataSource;
  	}
  }
  ```

  



## Web

### WebContainer

#### ``org.springframework.boot.autoconfigure.web.embedded``

* ``EmbeddedWebServerFactoryCustomizerAutoConfiguration`` 用来选择**特定的** Web 容器

* ``JettyWebServerFactoryCustomizer``
* ``NettyWebServerFactoryCustomizer``
* ``TomcatWebServerFactoryCustomizer``
* ``UndertowWebServerFactoryCustomizer``



