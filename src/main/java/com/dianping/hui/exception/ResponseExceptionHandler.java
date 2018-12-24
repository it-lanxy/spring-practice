package com.dianping.hui.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

/**
 * @author: lanxinyu@meituan.com  2018-11-02 下午4:47
 * @Description:
 */

/**
 * 如果为控制器类的特定切面能够运用到整个应用程序的所有控制器中，那么这将会便利很多
 *
 * @ControllerAdvice 控制器类通知
 *
 * 在带有 @ControllerAdvice 注解的类『中』，
 * @ExceptionHandler
 * @InitBinder
 * @ModelAttribute 所标注的方法
 * 这些方法会运用到整个应用程序所有控制器中带有@RequestMapping注解的「方法」上。
 */
@ControllerAdvice
public class ResponseExceptionHandler {

    /**
     * demo {@link org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler}
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handlerResourceNotFoundException(Exception ex, WebRequest request) {
        return "error";
    }

    @InitBinder
    public void initBinder() {
        return ;
    }

    @ModelAttribute
    public void modelAtribute() {

    }
}
