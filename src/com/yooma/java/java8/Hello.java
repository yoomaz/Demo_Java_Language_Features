package com.yooma.java.java8;

@FunctionalInterface
public interface Hello {

    void sayHello();

    default void sayHello2() {
        System.out.println("sayHello2()");
    }

    static void sayHello3() {
        System.out.println("sayHello3()");
    }
 }
