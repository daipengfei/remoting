package com.enniu.remoting;

/*********************************
 *                               *
 Created by daipengfei on 16/11/26.
 *                               *
 ********************************/

public class RpcResponse {

    private Object result;

    private Throwable exception;

    private long requestId;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

}
