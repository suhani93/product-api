package project.demo.product.common.response;

import lombok.Builder;
import lombok.Getter;
import org.slf4j.MDC;
import project.demo.product.common.config.RequestTransactionIdFilter;

@Getter
public class ResponseErrorData {
    private String code;
    private String message;
    private String transactionId;

    @Builder
    public ResponseErrorData(String code, String message) {
        this.code = code;
        this.message = message;
        this.transactionId = MDC.get(RequestTransactionIdFilter.TRANSACTION_ID);
    }
}
