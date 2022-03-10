package project.demo.product.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import project.demo.product.common.enums.CommonErrorCode;
import project.demo.product.common.response.ResponseErrorData;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class CommonRestControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ResponseErrorData exception(Exception e) {
        log.error("{}", e);

        ResponseErrorData result = ResponseErrorData.builder()
                .code(CommonErrorCode.INTERNAL_SERVER_ERROR.getCode())
                .message(CommonErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();

        return result;
    }


    /**
     * URL 못찾은 경우 404
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseErrorData noHandlerFoundException(NoHandlerFoundException e) {
        log.error("{}", e);

        CommonErrorCode notFoundError = CommonErrorCode.NOT_FOUND;

        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("requestUri", e.getRequestURL());

        String message = StringSubstitutor.replace(notFoundError.getMessage(), valueMap);

        ResponseErrorData result = ResponseErrorData.builder()
                .code(notFoundError.getCode())
                .message(message)
                .build();

        return result;
    }

    /**
     * 필수 paramter 값 누락 시 @RequestParam
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseErrorData missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("{}", e);


        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("paramterName", e.getParameterName());

        CommonErrorCode commonErrorCode = CommonErrorCode.MISSING_PARAMETER;
        String message = StringSubstitutor.replace(commonErrorCode.getMessage(), valueMap);

        ResponseErrorData result = ResponseErrorData.builder()
                .code(commonErrorCode.getCode())
                .message(message)
                .build();

        return result;
    }


    /**
     * Validtaion 실패 시 @Valid
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseErrorData methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("{}", e);

        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        String paramterName = ((FieldError) objectError).getField();
        String validationErrorMessage = objectError.getDefaultMessage();

        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("paramterName", paramterName);
        valueMap.put("validationErrorMessage", validationErrorMessage);

        CommonErrorCode commonErrorCode = CommonErrorCode.INVALID_PARAMETER;
        String message = StringSubstitutor.replace(commonErrorCode.getMessage(), valueMap);

        ResponseErrorData result = ResponseErrorData.builder()
                .code(commonErrorCode.getCode())
                .message(message)
                .build();

        return result;
    }
}
