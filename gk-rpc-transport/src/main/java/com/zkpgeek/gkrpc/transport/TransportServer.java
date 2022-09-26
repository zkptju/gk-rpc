package com.zkpgeek.gkrpc.transport;

/**
 * @description:
 * 1、启动，监听端口
 * 2、接收请求
 * 3、关闭监听
 * @author: ZKP
 * @time: 2022/9/25
 */
public interface TransportServer {
    void init(int port, RequestHandler handler);
    void start();
    void stop();
}
