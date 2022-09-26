package com.zkpgeek.gkrpc.server;

import com.zkpgeek.gkrpc.Request;
import com.zkpgeek.gkrpc.Response;
import com.zkpgeek.gkrpc.ServiceDescriptor;
import com.zkpgeek.gkrpc.codec.Decoder;
import com.zkpgeek.gkrpc.codec.Encoder;
import com.zkpgeek.gkrpc.common.utils.ReflectionUtils;
import com.zkpgeek.gkrpc.transport.RequestHandler;
import com.zkpgeek.gkrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: ZKP
 * @time: 2022/9/25
 */
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer() {

    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.net = ReflectionUtils.newInstance(
                config.getTransportClass());
        this.net.init(config.getPort(), handler);
        this.encoder = ReflectionUtils.newInstance(
                config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(
                config.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            Response resp = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(receive, receive.available());
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request: {}", request);
                ServiceInstance sis = serviceManager.lookup(request);
                Object res = serviceInvoker.invoke(sis, request);
                resp.setData(res);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                resp.setCode(1);
                resp.setMessage("RpcServer got error: "
                        + e.getClass().getName()
                        + ":" + e.getMessage());
            } finally {
                try {
                    byte[] outBytes = encoder.encode(resp);
                    toResp.write(outBytes);
                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };
}
