package com.whc.model.observer;

/**
 * 具体的被观察者，具有添加观察者、移除观察者以及通知观察者的方法
 * 除此之外，还有一个主题的状态
 */
public class ConcreteSubject extends AbstractSubject {
    private String subjectState;

    public String getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
    }
}
