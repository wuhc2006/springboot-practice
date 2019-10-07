package com.whc.model.factory;

public class SqlServerFactory implements Factory {
    @Override
    public String getName() {
        return "Sql Server";
    }

    @Override
    public void createUser() {
        System.out.println("add user in sql server.");
    }

    @Override
    public void createDept() {
        System.out.println("add dept in sql server.");
    }
}
