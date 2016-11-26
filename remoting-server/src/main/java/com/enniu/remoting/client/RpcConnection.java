package com.enniu.remoting.client;

import com.enniu.remoting.RpcRequest;
import com.enniu.remoting.RpcResponse;

/*********************************
 *                               *
 Created by daipengfei on 16/11/26.
 *                               *
 ********************************/

public interface RpcConnection {

    RpcResponse send(RpcRequest rpcRequest) throws Throwable;

    void connect() throws Throwable;

    void close() throws Throwable;

    boolean isConnected();

    boolean isClosed();
}
