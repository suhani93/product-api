package project.demo.product.common.response;

import lombok.Getter;
import org.slf4j.MDC;
import project.demo.product.common.config.RequestTransactionIdFilter;
import project.demo.product.common.enums.CommonErrorCode;

public class ResponseUtil {

    public static <T> ResponseSuccessData<T> success(T response) {
        return new ResponseSuccessData<>(response);
    }

    public static ResponseErrorData error(String code, String message) {
        return new ResponseErrorData(code , message);
    }

    public static ResponseErrorData error(CommonErrorCode commonErrorCode) {
        return new ResponseErrorData(commonErrorCode.getCode() , commonErrorCode.getMessage());
    }

    @Getter
    public static class ResponseErrorData {
        private final String code;
        private final String message;
        private final String transactionId;

        private ResponseErrorData(String code, String message) {
            this.code = code;
            this.message = message;
            this.transactionId = MDC.get(RequestTransactionIdFilter.TRANSACTION_ID);
        }
    }

    @Getter
    public static class ResponseSuccessData<T> {
        private final String code;
        private final T data;

        private ResponseSuccessData(T data) {
            this.code = "SUCCESS";
            this.data = data;
        }
    }
}
