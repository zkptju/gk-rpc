package com.zkpgeek.gkrpc.transport;

import com.zkpgeek.gkrpc.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @description:
 * @author: ZKP
 * @time: 2022/9/25
 */
public class HTTPTransportClient implements TransportClient{

    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" +peer.getHost() + ":"
                + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpconn = (HttpURLConnection) new URL(url).openConnection();
            httpconn.setDoOutput(true);
            httpconn.setDoInput(true);
            httpconn.setUseCaches(false);
            httpconn.setRequestMethod("POST");
            httpconn.connect();
            IOUtils.copy(data, httpconn.getOutputStream());
            int resultCode = httpconn.getResponseCode();
            if (resultCode == HttpURLConnection.HTTP_OK) {
                return httpconn.getInputStream();
            } else {
                return httpconn.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
