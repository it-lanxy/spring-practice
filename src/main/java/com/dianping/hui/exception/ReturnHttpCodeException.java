package com.dianping.hui.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: lanxinyu@meituan.com  2018-11-02 下午12:05
 * @Description:
 */

/**
 * 如果返回http状态码，和描述
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "resource not found.")
public class ReturnHttpCodeException extends RuntimeException {
}
