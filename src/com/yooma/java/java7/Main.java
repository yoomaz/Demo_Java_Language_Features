package com.yooma.java.java7;

public class Main {

    public static void main(String[] args) {
        feature01();

        feature04();
    }

    /**
     * 1. switch可以接受string类型而不像以前仅仅是int
     */
    public static void feature01() {
        String type = "one";

        switch (type) {
            case "one":
                System.out.println("one");
                break;
        }
    }

    /**
     * 2. 异常catch可以一次处理完
     */
    public static void feature02() {
        try {
            System.out.println("one");
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    /**
     * 4. 文件读写 会自动关闭流而不像以前那样需要在finally中显式close
     * <p>
     * 最好还是手动 close 掉
     */
    public static void feature03() {

    }

    /**
     * 5.
     */
    public static void feature04() {
        int million  =  1_000_000;

        System.out.println(million);
    }
}
