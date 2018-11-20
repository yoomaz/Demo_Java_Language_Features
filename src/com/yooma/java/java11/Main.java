package com.yooma.java.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
//        feature01();

        feature02();
    }

    /**
     * HttpClient 使用
     */
    public static void feature01() throws IOException, InterruptedException {
        var uri = "http://www.baidu.com";
        var httpClient = HttpClient.newHttpClient();
        var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        var response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    /**
     * 字符串增强
     */
    public static void feature02() {
        var str = "   hello   ";
        // 长度为 0 或者只包含空格
        System.out.println(str.isBlank());
        // 长度为 0
        System.out.println(str.isEmpty());
        // 去除首尾空格
        System.out.println(str.strip());
        // 去掉尾部空格
        System.out.println(str.stripTrailing());
        // 去掉首部空格
        System.out.println(str.stripLeading());
        // 重复
        System.out.println(str.repeat(3));
        // 字符串转化为流，统计行数
        System.out.println("a\nb\nc".lines().count());
    }
}
