package com.enniu.remoting;

import java.util.concurrent.atomic.AtomicLong;

/*********************************
 *                               *
 Created by daipengfei on 16/11/26.
 *                               *
 ********************************/

public class RpcRequest {

    private static final AtomicLong REQUEST_ID = new AtomicLong(0);

    private final long requestId;

    private String               methodName;

    private Class<?>[]           parameterTypes;

    private Object[]             arguments;

    private String className;

    public RpcRequest() {
        requestId = newRequestId();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getRequestId() {
        return requestId;
    }

    private static long newRequestId(){
        return REQUEST_ID.getAndIncrement();
    }

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
    }
}
