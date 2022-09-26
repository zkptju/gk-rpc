package com.zkpgeek.gkrpc.codec;

/**
 * @description:反序列化
 * @author: ZKP
 * @time: 2022/9/25
 */
public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}
