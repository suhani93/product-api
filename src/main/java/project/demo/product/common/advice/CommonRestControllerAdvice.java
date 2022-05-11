package project.demo.product.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import project.demo.product.common.enums.CommonErrorCode;
import project.demo.product.common.exception.NotFoundException;
import project.demo.product.common.response.ResponseUtil;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class CommonRestControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ResponseUtil.ResponseErrorData exception(Exception e) {
        log.error("{}", e);
        return ResponseUtil.error(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }


    /**
     * URL 못찾은 경우 404
     * 해당 리소스가 없는 경우 404
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NoHandlerFoundException.class, NotFoundException.class})
    public ResponseUtil.ResponseErrorData noHandlerFoundException(NoHandlerFoundException e) {
        log.error("{}", e);
        if(e instanceof NoHandlerFoundException){
            return ResponseUtil.error(CommonErrorCode.NOT_FOUND);
        }
        return ResponseUtil.error(CommonErrorCode.NOT_FOUND.getCode(), e.getMessage());
    }

    /**
     * 필수 paramter 값 누락 시 @RequestParam
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseUtil.ResponseErrorData missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("{}", e);

        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("paramterName", e.getParameterName());

        CommonErrorCode commonErrorCode = CommonErrorCode.MISSING_PARAMETER;
        String message = StringSubstitutor.replace(commonErrorCode.getMessage(), valueMap);

        return ResponseUtil.error(commonErrorCode.getCode(), message);
    }


    /**
     * Validtaion 실패 시 @Valid
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseUtil.ResponseErrorData methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("{}", e);

        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        String paramterName = ((FieldError) objectError).getField();
        String validationErrorMessage = objectError.getDefaultMessage();

        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("paramterName", paramterName);
        valueMap.put("validationErrorMessage", validationErrorMessage);

        CommonErrorCode commonErrorCode = CommonErrorCode.INVALID_PARAMETER;
        String message = StringSubstitutor.replace(commonErrorCode.getMessage(), valueMap);

        return ResponseUtil.error(commonErrorCode.getCode(),message);
    }
}
