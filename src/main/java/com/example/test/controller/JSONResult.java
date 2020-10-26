package com.example.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public static JSONResult build(Integer status, String message, Object data) {
        return new JSONResult(status, message, data);
    }

    public static JSONResult ok(Object data) {
        return new JSONResult(data);
    }

    public static JSONResult ok() {
        return new JSONResult(null);
    }

    public static JSONResult errorMessage(String message) {
        return new JSONResult(500, message, null);
    }

    public static JSONResult errorMap(Object data) {
        return new JSONResult(501, "error", data);
    }

    public static JSONResult errorTokenMessage(String message) {
        return new JSONResult(502, message, null);
    }

    public static JSONResult errorException(String message) {
        return new JSONResult(555, message, null);
    }

    public JSONResult() {

    }

    public JSONResult(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public JSONResult(Object data) {
        this.status = 200;
        this.message = "SUCCESS";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}