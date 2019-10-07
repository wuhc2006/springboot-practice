package com.whc.model.build2;

public class BuilderTest {
    public static void main(String[] args) {
        Builder builder = new Builder();
        Product product = builder.addA().addB().build();
        System.out.println(product);
    }
}
