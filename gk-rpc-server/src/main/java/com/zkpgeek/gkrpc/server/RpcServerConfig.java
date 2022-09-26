package com.zkpgeek.gkrpc.server;

import com.zkpgeek.gkrpc.codec.Decoder;
import com.zkpgeek.gkrpc.codec.Encoder;
import com.zkpgeek.gkrpc.codec.JSONDecoder;
import com.zkpgeek.gkrpc.codec.JSONEncoder;
import com.zkpgeek.gkrpc.transport.HTTPTransportServer;
import com.zkpgeek.gkrpc.transport.TransportServer;
import lombok.Data;

/**
 * @description:Server配置
 * @author: ZKP
 * @time: 2022/9/25
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass =
            HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;

}
