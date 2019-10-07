package com.whc.model.observer;

/**
 * 观察者模式测试
 */
public class ObserverTest {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(new ConcreteObserver("X", subject));
        subject.attach(new ConcreteObserver("Y", subject));
        subject.attach(new ConcreteObserver("Z", subject));

        subject.setSubjectState("ABC");
        subject.notifyObserver();
    }
}
