package com.whc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName User
 * @Description TODO
 * @Author Administrator
 * @Date 2019/2/25 22:32
 * @Version 1.0
 */
@Entity
@Table(name = "T_USER_TEST")
public class User implements Serializable {
    private static final long serialVersionUID = 2920068902699085827L;
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private Integer age;
    private BigDecimal money;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
