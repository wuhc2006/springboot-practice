package com.whc;

import java.math.BigDecimal;

/**
 * @ClassName Person
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/3 11:07
 * @Version 1.0
 */
public class Person {

    private String name;
    private Integer age;
    private String email;
    private BigDecimal account;

    public Person(String name, Integer age, String email, BigDecimal account) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.account = account;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", account=" + account +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }
}
