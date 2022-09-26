package com.zkpgeek.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: ZKP
 * @time: 2022/9/25
 */
public class JSONEncoderTest {

    @Test
    public void encode() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();
        bean.setName("zkpgeek");
        bean.setAge(18);
        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);
    }
}