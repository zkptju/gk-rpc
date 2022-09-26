package com.zkpgeek.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: ZKP
 * @time: 2022/9/25
 */
public class JSONDecoderTest {

    @Test
    public void decode() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();
        bean.setName("zkpgeek");
        bean.setAge(18);
        byte[] bytes = encoder.encode(bean);
        Decoder decoder = new JSONDecoder();
        TestBean bean2 = decoder.decode(bytes, TestBean.class);
        // System.out.println(decode);
        assertEquals(bean.getName(), bean2.getName());
        assertEquals(bean.getAge(), bean2.getAge());
    }
}