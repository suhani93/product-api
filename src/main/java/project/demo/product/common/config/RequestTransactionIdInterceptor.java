package project.demo.product.common.config;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class RequestTransactionIdInterceptor implements HandlerInterceptor {

    public static final String TRACE_HEADER = "X-Trace-Id";
    public static final String TRANSACTION_ID = "transactionId";

    public RequestTransactionIdInterceptor() {
        MDC.put("transactionId", UUID.randomUUID().toString());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceID = UUID.randomUUID().toString();
        String headerText = request.getHeader(TRACE_HEADER);
        if (StringUtils.hasText(headerText)) {
            traceID = headerText;
        }
        MDC.put(TRANSACTION_ID, traceID);
        return true;
    }
}
