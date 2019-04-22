package com.whc.array;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ArrayListTest
 * @Description 查看ArrayList的移除
 * @Author Administrator
 * @Date 2019/4/13 22:30
 * @Version 1.0
 */
public class ArrayListTest {

    private static class User {
        private String username;
        private Integer age;
        private String email;

        public User(String username, Integer age, String email) {
            this.username = username;
            this.age = age;
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", age=" + age +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        User user1 = new User("admin1", 20, "a@qq.com");
        User user2 = new User("admin2", 20, "a@qq.com");
        User user3 = new User("admin3", 20, "a@qq.com");
        User user4 = null;
        User user5 = null;

        List<User> listTest = new ArrayList();
        listTest.add(user1);
        listTest.add(user2);
        listTest.add(user3);
        listTest.add(user4);
        listTest.add(user5);

        // 移除所有的null
        listTest.stream().filter(x -> (x != null)).collect(Collectors.toList()).forEach(System.out::println);

    }


}
