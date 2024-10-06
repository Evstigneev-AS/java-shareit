package ru.practicum.shareit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
<<<<<<<< HEAD:gateway/src/main/java/ru/practicum/shareit/ShareItGateway.java
public class ShareItApp {

    public static void main(String[] args) {
        SpringApplication.run(ShareItApp.class, args);
    }
}
========
public class ShareItServer {
    public static void main(String[] args) {
        SpringApplication.run(ShareItServer.class, args);
    }
}
>>>>>>>> add-item-requests-and-gateway:server/src/main/java/ru/practicum/shareit/ShareItServer.java
