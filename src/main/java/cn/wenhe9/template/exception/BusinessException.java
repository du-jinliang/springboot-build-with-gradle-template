package cn.wenhe9.template.exception;

import cn.wenhe9.template.result.ResultResponseEnum;
import lombok.Data;

/**
 * @description: 业务异常
 * @author: DuJinliang
 * @date: 2023/11/3
 */
@Data
public class BusinessException extends ServiceException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer code, String message) {
        super(code, message);
    }

    public BusinessException(Integer code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    public BusinessException(ResultResponseEnum responseEnum) {
        this(responseEnum.getCode(), responseEnum.getMsg());
    }

    public static BusinessException of(String message) {
        return new BusinessException(ResultResponseEnum.BUSINESS_EXCEPTION.getCode(), message);
    }

}
