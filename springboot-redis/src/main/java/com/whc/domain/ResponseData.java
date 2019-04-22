package com.whc.domain;

import java.io.Serializable;

/**
 * @ClassName ResponseData
 * @Description TODO
 * @Author Administrator
 * @Date 2019/2/27 22:46
 * @Version 1.0
 */
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = 6757870792246920076L;
    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;

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
