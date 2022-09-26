package com.zkpgeek.gkrpc.example;

import com.zkpgeek.gkrpc.server.RpcServer;
import com.zkpgeek.gkrpc.server.RpcServerConfig;

/**
 * @description:
 * @author: ZKP
 * @time: 2022/9/25
 */
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
