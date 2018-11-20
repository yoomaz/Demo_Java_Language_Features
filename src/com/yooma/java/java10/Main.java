package com.yooma.java.java10;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        feature01();
    }

    /**
     * var 类型推断
     *
     * 建议用在下面几处：
     * 1. 局部变量初始化
     * 2. for循环内部索引变量
     * 3. 传统的for循环声明变量
     */
    public static void feature01() {
        var list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.forEach(System.out::println);

    }
}
