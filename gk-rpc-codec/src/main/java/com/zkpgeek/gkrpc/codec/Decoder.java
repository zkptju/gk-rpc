package com.zkpgeek.gkrpc.codec;

/**
 * @description:εεΊεε
 * @author: ZKP
 * @time: 2022/9/25
 */
public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}
