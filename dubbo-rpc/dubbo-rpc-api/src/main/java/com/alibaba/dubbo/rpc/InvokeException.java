package com.alibaba.dubbo.rpc;

/**
 * <p/>
 * InvokeException
 * <p/>
 *
 * @author softfn
 */
public class InvokeException extends RuntimeException {
    /**
     * 状态码
     */
    private Status status;

    public InvokeException() {
        super();
    }

    public InvokeException(Status status) {
        super(status.msg());
        this.status = status;
    }

    public InvokeException(Status status, String message) {
        super(message);
        this.status = status;
    }

    public InvokeException(Status status, Throwable cause) {
        super(status.msg(), cause);
        this.status = status;
    }

    public InvokeException(Status status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
