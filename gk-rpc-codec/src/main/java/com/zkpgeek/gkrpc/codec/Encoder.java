package com.zkpgeek.gkrpc.codec;

/**
 * @description:序列化
 * @author: ZKP
 * @time: 2022/9/25
 */
public interface Encoder {
    byte[] encode(Object obj);
}
