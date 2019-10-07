package com.whc.clazz;

public class EnumTest {
    public static void main(String[] args) {
        WeekEnum weekEnum = WeekEnum.FRI;
        switch (weekEnum){
            case MON:
                System.out.println(weekEnum);
                break;
            case FRI:
                System.out.println(weekEnum);
                break;
        }
    }
}
