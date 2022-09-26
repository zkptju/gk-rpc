package com.zkpgeek.gkrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @description:
 * @author: ZKP
 * @time: 2022/9/25
 */
public class JSONDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
