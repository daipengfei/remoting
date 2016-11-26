package com.enniu.remoting.client;

import com.enniu.remoting.RpcRequest;
import com.enniu.remoting.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/*********************************
 *                               *
 Created by daipengfei on 16/11/26.
 *                               *
 ********************************/

public class NettyRpcConnection implements RpcConnection {

    private InetSocketAddress inetSocketAddress;

    private volatile boolean connected;

    public NettyRpcConnection(String host,int port) {
        inetSocketAddress = new InetSocketAddress(host,port);
    }

    @Override
    public RpcResponse send(RpcRequest rpcRequest) throws Throwable {
        if(!isConnected()){
            throw new IllegalArgumentException("not connected");
        }
        return null;
    }

    @Override
    public void connect() throws Throwable {
        if(connected){
            return;
        }
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast();
                        }
                    });
            ChannelFuture future = bootstrap.connect(inetSocketAddress).sync();
            connected = true;
            future.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    @Override
    public void close() throws Throwable {

    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public boolean isClosed() {
        return false;
    }


}
