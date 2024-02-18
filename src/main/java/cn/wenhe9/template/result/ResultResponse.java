package cn.wenhe9.template.result;

import lombok.Getter;

/**
 * @description: 统一返回结果
 * @author: DuJinliang
 * @date: 2023/11/3
 */
@Getter
public class ResultResponse<T> {
    private Integer code;
    private String msg;
    private T result;

    private ResultResponse(){
    }

    protected static <T> ResultResponse<T> build(T data){
        ResultResponse<T> resultResponse = new ResultResponse<>();
        resultResponse.setData(data);
        return resultResponse;
    }

    public static <T> ResultResponse<T> build(T data, ResultResponseEnum responseEnum){
        ResultResponse<T> resultResponse = build(data);
        resultResponse.setCode(responseEnum.getCode());
        resultResponse.setMsg(responseEnum.getMsg());
        return resultResponse;
    }


    public static <T> ResultResponse<T> success(T data){
        ResultResponse<T> resultResponse = build(data, ResultResponseEnum.SUCCESS);
        return resultResponse;
    }

    public static <T> ResultResponse<T> success(){
        ResultResponse<T> resultResponse = build(null, ResultResponseEnum.SUCCESS);
        return resultResponse;
    }

    public static <T> ResultResponse<T> fail(T data){
        ResultResponse<T> resultResponse = build(data, ResultResponseEnum.FAIL);
        return resultResponse;
    }

    public static <T> ResultResponse<T> fail(){
        ResultResponse<T> resultResponse = build(null, ResultResponseEnum.FAIL);
        return resultResponse;
    }

    public ResultResponse<T> code(Integer code){
        this.code = code;
        return this;
    }

    public ResultResponse<T> msg(String msg){
        this.msg = msg;
        return this;
    }

    private void setCode(Integer code) {
        this.code = code;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    private void setData(T data) {
        this.result = data;
    }
}
