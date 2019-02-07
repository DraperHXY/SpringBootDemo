# feat

* Thymeleaf
* i18n
* Log Aspect
* Global Handler Exception
* Extend Spring MVC Web Config



# bug

## 日志

### Swagger2 的日志太多

```yml
logging:
  level:
    springfox.documentation: trace
```

进行这个配置并没有减少日志，不知为什么



## 静态资源

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

