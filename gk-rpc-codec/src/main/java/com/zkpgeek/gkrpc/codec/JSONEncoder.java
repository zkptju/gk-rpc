package com.zkpgeek.gkrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @description:基于JSON的序列化实现
 * @author: ZKP
 * @time: 2022/9/25
 */
public class JSONEncoder implements Encoder{
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
