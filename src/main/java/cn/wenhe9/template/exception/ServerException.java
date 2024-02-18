package cn.wenhe9.template.exception;


import cn.wenhe9.template.result.ResultResponseEnum;
import lombok.Data;

/**
 * @description: 系统异常类
 * @author: DuJinliang
 * @create: 2023/11/3
 */

@Data
public class ServerException extends ServiceException {

    public ServerException(String message) {
        super(message);
    }

    public ServerException(Integer code, String message) {
        super(code, message);
    }

    public ServerException(Integer code, String message, Throwable throwable) {
        super(code, message, throwable);
    }

    public ServerException(ResultResponseEnum responseEnum) {
        super(responseEnum.getCode(), responseEnum.getMsg());
    }

    public static ServerException of(String message) {
        return new ServerException(ResultResponseEnum.INTERNAL_SERVER_ERROR.getCode(), message);
    }


}
