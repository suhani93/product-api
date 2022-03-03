package project.demo.product.common.response;

import lombok.Getter;
import org.slf4j.MDC;
import project.demo.product.common.config.RequestTransactionIdInterceptor;

@Getter
public class ResponseErrorData {
    private String code;
    private String message;
    private String transactionId;

    public ResponseErrorData(String code, String message) {
        this.code = code;
        this.message = message;
        this.transactionId = MDC.get(RequestTransactionIdInterceptor.TRANSACTION_ID);
    }
}
