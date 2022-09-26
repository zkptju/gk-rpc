package com.zkpgeek.gkrpc;

import lombok.Data;

/**
 * @description:表示RPC的一个请求
 * @author: ZKP
 * @time: 2022/9/25
 */
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;
}
