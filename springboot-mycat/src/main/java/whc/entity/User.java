package whc.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用于jpa测试专用
 *
 * @Author Administrator
 * @Date 2019/6/23 17:13
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 5469299254625417962L;
    private long id;
    private String name;
    private int age;
    private String email;

    public User(long id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
