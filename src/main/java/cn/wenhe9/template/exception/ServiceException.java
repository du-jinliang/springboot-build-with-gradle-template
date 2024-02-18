package cn.wenhe9.template.exception;

import lombok.Data;

/**
 * @description: 自定义异常基类
 * @author: DuJinliang
 * @date: 2023/11/3
 */
@Data
public class ServiceException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    public ServiceException() {
        super();
    }

    public ServiceException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public ServiceException(Throwable cause) {
        super(null, cause);
    }

    public ServiceException(String errorMessage, Throwable cause) {
        super(null, cause);
        this.errorMessage = errorMessage;
    }

    public ServiceException(Integer errorCode, String errorMessage, Throwable cause) {
        this(errorMessage, cause);
        this.errorCode = errorCode;
    }


    public ServiceException(Integer code, String message) {
        this.errorCode = code;
        this.errorMessage = message;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
