package com.xiaoxin.common.exception;

import com.xiaoxin.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;


/**
 * @author:XIAOXIN
 * @date:2023/07/26
 **/
//定义一个全局异常处理构造器,下述注解只能捕捉到controller中的异常
@ControllerAdvice
public class Exceptionhandler {
    private static Logger logger = LoggerFactory.getLogger(Exceptionhandler.class);

    //    尝试能否捕捉到sql的错误
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public Result sqlException(CustomerException e) {
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
//    应为返回的的是json数据，所以是通过json转装换位json数据
    @ResponseBody
    public Result exception(Exception e) {
//        存在系统错误的时候，这个时候打印日志出来
        logger.error(e.getMessage(), e);
        return Result.error("系统错误");
    }

}
