package cn.wenhe9.template.exception;

import cn.wenhe9.template.result.ResultResponseEnum;
import lombok.Data;

/**
 * @description: 校验异常
 * @author: DuJinliang
 * @date: 2023/11/3
 */
@Data
public class ValidationException extends ServiceException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Integer code, String message) {
        super(code, message);
    }

    public ValidationException(Integer code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    public ValidationException(ResultResponseEnum resultResponseEnum) {
        this(resultResponseEnum.getCode(), resultResponseEnum.getMsg());
    }

    public static ValidationException of(String message) {
        return new ValidationException(ResultResponseEnum.VALIDATE_FAILED.getCode(), message);
    }


}
