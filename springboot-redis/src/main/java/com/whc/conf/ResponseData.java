package com.whc.conf;

import java.io.Serializable;

/**
 * @ClassName ResponseData
 * @Description TODO
 * @Author Administrator
 * @Date 2019/2/27 22:46
 * @Version 1.0
 */
public class ResponseData implements Serializable {
    private static final long serialVersionUID = 6757870792246920076L;
    private int code;
    private String message;
    private Object data;

    public ResponseData(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
