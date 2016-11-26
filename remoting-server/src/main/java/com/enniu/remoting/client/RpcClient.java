package com.enniu.remoting.client;

/*********************************
 *                               *
 Created by daipengfei on 16/11/26.
 *                               *
 ********************************/

public interface RpcClient {

    <T> T proxy(Class<T> interfaceClass) throws Throwable;

}
