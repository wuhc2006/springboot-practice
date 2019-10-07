package com.whc.model.observer;

/**
 * 具体的观察者，具有名字、观察的对象（主题:不是必须，在观察者中已经进行了关联）
 */
public class ConcreteObserver implements Observer {
    private String name;
    private ConcreteSubject subject;

    public ConcreteObserver(String name, ConcreteSubject subject) {
        this.name = name;
        this.subject = subject;
    }

    @Override
    public void update() {
        String observerState = subject.getSubjectState();
        System.out.println(String.format("观察者%s的状态是%s", name, observerState));
    }
}
