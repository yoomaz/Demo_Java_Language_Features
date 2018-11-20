package com.yooma.java.java8;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

//        feature01();

//        feature02();

//        feature03();

//        feature04();

//        feature05();

//        feature06();

//        feature07();

        feature08();
    }

    /**
     * lambda 表达式
     */
    public static void feature01() {
        MathOperation addition = (int a, int b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;

        System.out.println("10 + 5 = " + addition.operation(10, 5));
        System.out.println("10 - 5 = " + subtraction.operation(10, 5));

        // 只有一行可以把方法体的大括号省略掉
        GreetingService greetingService1 = message -> System.out.println("Hello " + message);

        GreetingService greetingService2 = message -> {
            System.out.println("Hello1 " + message);
            System.out.println("Hello2 " + message);
        };
        greetingService1.sayMessage("111");
        greetingService2.sayMessage("222");


        Arrays.asList("a", "b", "c")
                .forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(s);
                    }
                });
        // 接口只有一个方法时候可以省略匿名类
        Arrays.asList("a", "b", "c")
                .forEach(s -> {
                    System.out.println(s);
                });
        // 方法只有一行的时候，可以省略大括号
        Arrays.asList("a", "b", "c")
                .forEach(s -> System.out.println(s));
        // 方法只有一行、参数只有一个的时候,可以省略形参，用 :: 操作符
        Arrays.asList("a", "b", "c")
                .forEach(System.out::println);
    }

    /**
     * 函数接口
     * <p>
     * 函数式接口就是一个具有一个方法的普通接口
     * <p>
     * Java 8增加了一种特殊的注解 @FunctionalInterface，用在函数接口上面，用来约束函数接口的行为
     * <p>
     * java.util.Function包下都是函数式接口，我们可以直接拿来使用
     */
    public static void feature02() {
        // 测试 BiFunction
        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a * b;
        System.out.println(biFunction.apply(10, 2));
        // 测试 Predicate
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        eval(list, integer -> integer % 2 == 0);


    }

    /**
     * 接口增加：默认方法, 静态方法
     * <p>
     * java 8 允许接口内实现方法，方法前需要加上 default 关键字
     * <p>
     * 注意：
     * 1. 静态方法不可以被继承
     * 2. 默认方法可以被继承
     * 3. 默认方法可以被重写
     * 4. 默认方法重新声明，则变成了普通方法
     */
    public static void feature03() {
        Hello hello = () -> System.out.println("hello");
        hello.sayHello();
        hello.sayHello2();
        Hello.sayHello3();
    }


    /**
     * 方法引用
     * <p>
     * 可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
     */
    public static void feature04() {
        // 遍历
        List<String> names = new ArrayList<>();
        names.add("Mahesh");
        names.add("Suresh");
        names.add("Ramesh");
        names.add("Naresh");
        names.add("Kalpesh");
        names.forEach(System.out::println);

        // 排序
        String[] array = {"gjyg", "ads", "jk"};
        Arrays.sort(array, String::compareToIgnoreCase);
        Arrays.asList(array)
                .forEach(System.out::println);
    }

    /**
     * Optional 使用
     * <p>
     * Optional的引入是为了解决臭名昭著的空指针异常问题
     * <p>
     * Optional实际上是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测
     */
    public static void feature05() {
        Integer value1 = null;
        Integer value2 = 10;

        Optional<Integer> a = Optional.ofNullable(value1);
        Optional<Integer> b = Optional.of(value2);
        Optional<Integer> c = Optional.empty();

        System.out.println("First parameter is present: " + a.isPresent());
        System.out.println("Second parameter is present: " + b.isPresent());

        // NoSuchElementException
//        Integer v1 = a.get();

        Integer v2 = b.get();
        System.out.println(v2);

        Integer v3 = c.orElse(101);
        System.out.println(v3);

    }

    /**
     * Stream 流式编程
     * <p>
     * stream 返回顺序流，集合作为其源
     * filter 方法用于消除基于标准元素
     * map 方法用于映射每个元素对应的结果
     * forEach 方法遍历该流中的每个元素
     * limit 方法用于减少流的大小
     * sorted 方法用来流排序
     * collect 方法是终端操作，表示以某种方式收集返回，这是通常出现在管道传输操作结束标记流的结束。
     */
    public static void feature06() {
        // 把空指针过滤掉，返回前三个
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> result1 = strings.stream()
                .filter(s -> !s.equals(""))
                .limit(3)
                .collect(Collectors.toList());
        Set<String> result2 = strings.stream()
                .filter(s -> !s.equals(""))
                .limit(3)
                .collect(Collectors.toSet());

        result1.forEach(System.out::println);


        // 计算集合每个元素的平方，并且去重，然后将值为81的去掉，输出排序后的数据
        List<Integer> ints = Arrays.asList(1, 5, 9, 6, 5, 4, 2, 5, 9);
        ints.stream().map(n -> n * n)
                .distinct()
                .filter(n -> n != 81)
                .sorted()
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // 统计
        System.out.println("--统计--");
        IntSummaryStatistics statistics = ints.stream()
                .mapToInt(n -> n)
                .summaryStatistics();
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getCount());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getSum());
    }

    /**
     * LocalDateTime, LocalDate, LocalTime
     */
    public static void feature07() {
        Clock clock = Clock.system(ZoneId.systemDefault());
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        // Get the current date and time
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);
        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int hour = currentTime.getHour();
        int seconds = currentTime.getSecond();
        System.out.println("Month: " + month
                + "  day: " + day
                + "  hour:" + hour
                + "  seconds: " + seconds
        );

        //指定时间
        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        //12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        //22 hour 15 minutes
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        //parse a string
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);


    }

    /**
     * Period 和 Duration
     */
    public static void feature08() {
        // Period
        //Get the current date
        LocalDate date1 = LocalDate.now();
        System.out.println("Current date: " + date1);

        //add 3 day to the current date
        LocalDate date2 = date1.plus(3, ChronoUnit.DAYS);
        System.out.println("Next month: " + date2);

        Period period = Period.between(date1, date2);
        System.out.println("Period: " + period);
        System.out.println("Period.getYears: " + period.getYears());
        System.out.println("Period.getMonths: " + period.getMonths());
        System.out.println("Period.getDays: " + period.getDays());


        // Duration
        LocalTime time1 = LocalTime.now();
        Duration twoHours = Duration.ofHours(2);

        System.out.println("twoHours.getSeconds(): " + twoHours.getSeconds());

        LocalTime time2 = time1.plus(twoHours);
        System.out.println("time2: " + time2);

        Duration duration = Duration.between(time1, time2);
        System.out.println("Duration: " + duration);
        System.out.println("Duration.getSeconds: " + duration.getSeconds());
    }


    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        list.forEach(integer -> {
            if (predicate.test(integer)) {
                System.out.println(integer);
            }
        });
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }
}
