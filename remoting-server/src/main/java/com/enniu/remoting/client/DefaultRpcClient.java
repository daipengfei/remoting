package com.enniu.remoting.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.enniu.remoting.RpcRequest;
import com.enniu.remoting.RpcResponse;

/*********************************
 *                               *
 Created by daipengfei on 16/11/26.
 *                               *
 ********************************/

public class DefaultRpcClient implements RpcClient {

    private final RpcInvoker    invoker;

    private final RpcConnection rpcConnection;

    public DefaultRpcClient(String host, int port) {
        rpcConnection = new NettyRpcConnection(host, port);
        invoker = new RpcInvoker();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T proxy(Class<T> interfaceClass) throws Throwable {
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException("interfaceClass must be a interface!");
        }
        return (T) Proxy.newProxyInstance(interfaceClass.getClass().getClassLoader(), new Class[] { interfaceClass },
            invoker);
    }

    private class RpcInvoker implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            RpcRequest request = new RpcRequest();
            request.setClassName(method.getDeclaringClass().getName());
            request.setMethodName(method.getName());
            request.setParameterTypes(method.getParameterTypes());
            request.setArguments(args);
            RpcResponse response = null;
            try {
                response = rpcConnection.send(request);
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
            if (response.getException() != null) {
                throw response.getException();
            } else {
                return response.getResult();
            }
        }
    }

}
