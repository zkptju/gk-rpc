package com.zkpgeek.gkrpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:表示网络传输的一个端点
 * @author: ZKP
 * @time: 2022/9/25
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}
