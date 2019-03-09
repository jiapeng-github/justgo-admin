package com.justgo.admin.filter;

import com.alibaba.dubbo.rpc.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

/**
 * Created by fancz on 2018/5/14.
 */
@Service
public class LogFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = RpcContext.getContext().getAttachments().get("TRACE_ID");
        if(StringUtils.isEmpty(traceId)){
            traceId = MDC.get("TRACE_ID");
        }
        MDC.put("TRACE_ID",traceId);
        RpcContext.getContext().getAttachments().put("TRACE_ID",traceId);
        return invoker.invoke(invocation);
    }

}
