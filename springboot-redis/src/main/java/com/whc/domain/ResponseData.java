package com.whc.domain;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2019/2/27 22:46
 */
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = 6757870792246920076L;
    private static final int SUCCESS_CODE = 200;
    private static final int FAIL_CODE = 500;

    private int code;
    private String message;
    private T data;

    public ResponseData(T data, String message) {
        this.code = SUCCESS_CODE;
        this.message = message;
        this.data = data;
    }

    public ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
