package com.zkpgeek.gkrpc.transport;

import com.zkpgeek.gkrpc.Peer;

import java.io.InputStream;

/**
 * @description:
 * 1、创建连接
 * 2、发送数据并且等待响应
 * 3、关闭连接
 * @author: ZKP
 * @time: 2022/9/25
 */
public interface TransportClient {
    void connect(Peer peer);
    InputStream write(InputStream data);
    void close();
}
