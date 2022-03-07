package project.demo.product.common.config;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class RequestTransactionIdFilter implements Filter {

    public static final String TRACE_HEADER = "X-Trace-Id";
    public static final String TRANSACTION_ID = "transactionId";

    public RequestTransactionIdFilter() {
        MDC.put("transactionId", UUID.randomUUID().toString());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String traceID = UUID.randomUUID().toString();

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String headerText = httpRequest.getHeader(TRACE_HEADER);//servletRequest.getHeader(TRACE_HEADER);

        if (StringUtils.hasText(headerText)) {
            traceID = headerText;
        }

        MDC.put(TRANSACTION_ID, traceID);

        filterChain.doFilter(httpRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
