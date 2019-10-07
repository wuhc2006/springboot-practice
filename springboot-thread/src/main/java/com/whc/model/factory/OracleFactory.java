package com.whc.model.factory;

public class OracleFactory implements Factory {
    @Override
    public String getName() {
        return "Oracle";
    }

    @Override
    public void createUser() {
        System.out.println("add user in Oracle.");
    }

    @Override
    public void createDept() {
        System.out.println("add dept in Oracle.");
    }
}
