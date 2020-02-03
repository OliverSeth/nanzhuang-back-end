package com.example.demo.utils;

/**
 * Created by Oliver Seth on 2019/4/6 12:50
 */
public class Result<T> {

    private Integer code;
    private T data;
    private String msg;

    private Result() {
        this.code = -99;
//        this.data="Empty result";
        this.msg = "Error";
    }

    private Result(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(0, data, "success");
    }

    public static Result success() {
        return new Result<>(0, null, "Success");
    }

    public static Result failed(Integer code, String data) {
        return new Result<>(code, data, "Failed");
    }

    public static Result exception(Integer code, String data) {
        return new Result<>(code, data, "Exception");
    }

    public static Result exception(Integer code, String data, String msg) {
        return new Result<>(code, data, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
