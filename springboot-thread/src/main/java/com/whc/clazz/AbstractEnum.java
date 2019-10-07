package com.whc.clazz;

public enum AbstractEnum {
    SqlServer {
        @Override
        void printType() {
            System.out.println("SqlServer");
        }
    },
    Mysql {
        @Override
        void printType() {
            System.out.println("Mysql.");
        }
    },
    Oracle {
        @Override
        void printType() {
            System.out.println("Oracle.");
        }
    },
    Mongodb {
        @Override
        void printType() {
            System.out.println("Mongodb.");
        }
    };

    abstract void printType();
}
