package com.zkpgeek.gkrpc.server;

import com.zkpgeek.gkrpc.Request;
import com.zkpgeek.gkrpc.common.utils.ReflectionUtils;

/**
 * @description:调用具体服务
 * @author: ZKP
 * @time: 2022/9/25
 */
public class ServiceInvoker {

    public Object invoke(ServiceInstance service,
                         Request request) {
        return ReflectionUtils.invoke(
                service.getTarget(),
                service.getMethod(),
                request.getParameters()
        );
    }
}
