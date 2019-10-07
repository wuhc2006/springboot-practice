package com.whc.model.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题或抽象通知者
 */
public abstract class AbstractSubject implements Subject {
    /**
     * 观察者列表
     */
    private List<Observer> observers = new ArrayList<>();
    /**
     * 添加观察者
     * @param observer
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * 移除观察者
     * @param observer
     */
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 通知观察者
     */
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
