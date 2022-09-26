package com.zkpgeek.gkrpc.example;

import com.zkpgeek.gkrpc.client.RpcClient;

/**
 * @description:
 * @author: ZKP
 * @time: 2022/9/25
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int r1 = service.add(1, 2);
        int r2 = service.minus(10, 8);
        System.out.println(r1);
        System.out.println(r2);
    }
}
