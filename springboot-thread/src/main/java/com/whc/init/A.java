package com.whc.init;

public class A {
    private B b;

    public A(B b) {
        this.b = b;
    }

    public A() {
    }

    @Override
    public String toString() {
        return "A{" +
                "b=" + b +
                '}';
    }
}
