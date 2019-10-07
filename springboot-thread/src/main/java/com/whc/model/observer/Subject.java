package com.whc.model.observer;

public interface Subject {
    /**
     * 添加观察者
     * @param observer
     */
    void attach(Observer observer);

    /**
     * 移除观察者
     * @param observer
     */
    void detach(Observer observer);

    /**
     * 通知观察者
     */
    void notifyObserver();
}
