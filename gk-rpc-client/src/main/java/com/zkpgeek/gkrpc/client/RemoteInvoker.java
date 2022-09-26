package com.zkpgeek.gkrpc.client;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.zkpgeek.gkrpc.Request;
import com.zkpgeek.gkrpc.Response;
import com.zkpgeek.gkrpc.ServiceDescriptor;
import com.zkpgeek.gkrpc.codec.Decoder;
import com.zkpgeek.gkrpc.codec.Encoder;
import com.zkpgeek.gkrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description:调用远程服务的代理类
 * @author: ZKP
 * @time: 2022/9/25
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz,
                         Encoder encoder,
                         Decoder decoder,
                         TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy,
                         Method method,
                         Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);
        Response resp = invokeRemote(request);
        if (resp.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote: " + resp);
        }
        return resp.getData();
    }

    private Response invokeRemote(Request request) {
        Response resp = null;
        TransportClient client = null;
        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream receive = client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(receive, receive.available());
            resp = decoder.decode(inBytes, Response.class);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            resp = new Response();
            resp.setCode(1);
            resp.setMessage("RpcClient got error: " +
                    e.getClass() +
                    " : " + e.getMessage());
        }finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return resp;
    }
}
