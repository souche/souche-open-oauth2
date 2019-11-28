package com.souche.open.soucheopenoauth2example;

import com.alibaba.fastjson.JSONObject;

public class ResultV0 {

    private static final long serialVersionUID = 1L;


    private Boolean success;
    private Integer code;
    private String msg;
    private JSONObject data;


    public ResultV0(Boolean success, Integer code, String msg, JSONObject data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultV0{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
