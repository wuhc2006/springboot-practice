package com.test;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @ClassName Test 测试idea自带工具的equals方法
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/1 22:58
 * @Version 1.0
 */
public class Test {
    private String username;
    private String password;
    private Integer age;
    private BigDecimal account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(username, test.username) &&
                Objects.equals(password, test.password) &&
                Objects.equals(age, test.age) &&
                Objects.equals(account, test.account);
    }

    public Test(String username, String password, Integer age, BigDecimal account) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.account = account;
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, password, age, account);
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

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public static void main(String[] args) {
        Test test1 = new Test("admin","admin", 20, new BigDecimal(1000.00));
        Test test2 = new Test("admin","admin", 20, new BigDecimal(1000.00));
        System.out.println(test1.equals(test2));
    }
}
