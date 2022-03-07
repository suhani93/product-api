package project.demo.product.common.enums;

import lombok.Getter;

@Getter
public enum CommonErrorCode {

    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR","An unknown error has occurred. Please try again in a little while"),
    MISSING_PARAMETER("MISSING_PARAMETER","'${paramterName}' is required"),
    INVALID_PARAMETER("INVALID_PARAMETER","'${paramterName}' ${validationErrorMessage}"),
    NOT_FOUND("NOT_FOUND","'${requestUri}' is not found");

    private String code;
    private String message;

    CommonErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
