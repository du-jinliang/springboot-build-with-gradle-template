package cn.wenhe9.template.exception;

import cn.wenhe9.template.result.ResultResponse;
import cn.wenhe9.template.result.ResultResponseEnum;
import cn.wenhe9.template.utils.LogInfoUtils;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.ServerException;

/**
 * @description: 统一异常处理
 * @author: DuJinliang
 * @date: 2023/11/3
 */

@Slf4j
@Primary
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ValidationException.class})
    public ResultResponse<String> handlerMemException(ValidationException e){
        log.error("出现异常,出现{}校验异常", e.getErrorMessage());
        return ResultResponse.build(e.getMessage(), ResultResponseEnum.VALIDATE_FAILED);
    }


    @ExceptionHandler(value = {BusinessException.class})
    public ResultResponse<Object> handlerBusinessException(BusinessException e){
        log.error("出现异常,{}", e.getMessage());
        return ResultResponse.fail()
                .code(e.getErrorCode())
                .msg(e.getErrorMessage());
    }

    @ExceptionHandler(value = {ServerException.class})
    public ResultResponse<String> handlerServerException(ServerException e){
        log.error("出现异常,{}", e.getMessage());
        return ResultResponse.build(e.getMessage(), ResultResponseEnum.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {MismatchedInputException.class})
    public ResultResponse<String> handlerServerException(MismatchedInputException e){
        log.error("出现异常,{}", e.getMessage());
        return ResultResponse.build(e.getMessage(), ResultResponseEnum.PARAM_ANALYSIS_ERROR);
    }

    @ExceptionHandler(value = {Throwable.class})
    public ResultResponse<Void> handlerException(Throwable e){
        log.error("出现异常,{}", LogInfoUtils.errInfo(e));
        return ResultResponse.build(null, ResultResponseEnum.INTERNAL_SERVER_ERROR);
    }

}
