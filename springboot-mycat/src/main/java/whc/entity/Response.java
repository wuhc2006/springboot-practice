package whc.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2019/6/23 18:09
 */
@Data
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 7812058903192186543L;
    private int code;
    private String message;
    private T data;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
