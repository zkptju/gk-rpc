package com.zkpgeek.gkrpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @description:
 * 处理网络请求的handler
 * @author: ZKP
 * @time: 2022/9/25
 */
public interface RequestHandler {
    void onRequest(InputStream receive, OutputStream toResp);
}
