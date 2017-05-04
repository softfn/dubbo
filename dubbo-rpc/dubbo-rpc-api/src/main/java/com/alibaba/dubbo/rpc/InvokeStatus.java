package com.alibaba.dubbo.rpc;

/**
 * <p/>
 * InvokeStatus
 * <p/>
 *
 * @author wenbing.zhang@midea.com
 */
public class InvokeStatus implements Status {
    private int code;
    private String msg;

    public InvokeStatus() {
    }

    public InvokeStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }
}
