package com.zkpgeek.gkrpc.client;

import com.zkpgeek.gkrpc.Peer;
import com.zkpgeek.gkrpc.codec.Decoder;
import com.zkpgeek.gkrpc.codec.Encoder;
import com.zkpgeek.gkrpc.codec.JSONDecoder;
import com.zkpgeek.gkrpc.codec.JSONEncoder;
import com.zkpgeek.gkrpc.transport.HTTPTransportClient;
import com.zkpgeek.gkrpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: ZKP
 * @time: 2022/9/25
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass =
            HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass =
            RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );
}
