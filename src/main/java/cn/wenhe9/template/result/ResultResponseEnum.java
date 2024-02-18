package cn.wenhe9.template.result;

import lombok.Getter;

/**
 * @description: 统一返回状态枚举
 * @author: DuJinliang
 * @date: 2023/11/3
 */
@Getter
public enum ResultResponseEnum {
    SUCCESS(0, "succeed"),

    FAIL(400, "fail"),

    INTERNAL_SERVER_ERROR(40000, "系统出现异常"),

    BUSINESS_EXCEPTION(40001, "业务出现异常"),

    VALIDATE_FAILED(40002, "请求参数验证失败"),

    SQL_PARAMS_ERROR(40003, "sql参数可能存在不合法，请检查"),

    PARAM_ANALYSIS_ERROR(40004, "参数解析错误，请检查请求参数"),

    DATA_ENCODE_ERROR(40005, "数据格式转换异常"),

    DATA_NOT_FOUND(40006, "没有找到对应数据"),

    KNOWN_LOCK_BEAN_NAME(306001, "未知的加锁类型"),

    CAN_NOT_GET_LOCK(306002, "获取锁失败");


    private Integer code;
    private String msg;

    ResultResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
