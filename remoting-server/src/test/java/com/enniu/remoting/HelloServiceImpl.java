package com.enniu.remoting;

/*********************************
 *                               *
 Created by daipengfei on 16/11/26.
 *                               *
 ********************************/

public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name, long id) {
        return "hello : name=" + name + ",id=" + id;
    }

}
