package com.whc.init;

public class B {
    private A a;

    public B(A a) {
        this.a = a;
    }

    public B() {
    }

    @Override
    public String toString() {
        return "B{" +
                "a=" + a +
                '}';
    }
}
