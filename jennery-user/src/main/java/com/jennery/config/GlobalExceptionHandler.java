package com.jennery.config;

import com.jennery.blog.common.bean.Result;
import com.jennery.blog.common.exception.BizException;
import com.jennery.blog.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String DEFAULT_ERROR_KEY = "DEFAULT_PARAM_ERROR";
    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler(BizException.class)
    public <T> Result<T> handleBizException(BizException exception) {
        log.error("error during request: {}", exception.getMessage(), exception);
        return Result.failed(exception.getCode(), exception.getMessage());
    }


    @ExceptionHandler
    public Result<Void> handleMethodArgumentException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("client argument error: ", e);
        String errorKey = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        if (StringUtils.isEmpty(errorKey)) {
            errorKey = DEFAULT_ERROR_KEY;
        }
        String message = messageSource.getMessage(errorKey, null, new Locale(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE)));
        return Result.failed(ErrorCode.FAILED.getCode(), message);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Void> handleRequestParseException(HttpMessageNotReadableException e) {
        log.error("parse client param error: ", e);
        return Result.failed(ErrorCode.FAILED.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("system error: ",  e);
        return Result.failed(ErrorCode.FAILED.getCode(), ErrorCode.FAILED.getMsg());
    }

}
