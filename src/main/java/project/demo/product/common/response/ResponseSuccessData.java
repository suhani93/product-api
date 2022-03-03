package project.demo.product.common.response;

import lombok.Getter;

@Getter
public class ResponseSuccessData<T> {
    private String code;
    private T data;

    public ResponseSuccessData(T data) {
        this.code = "SUCCESS";
        this.data = data;
    }
}
