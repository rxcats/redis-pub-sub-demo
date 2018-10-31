package io.github.rxcats.redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class RedisDemoApplication {

    private final RedisMessagePublisher publisher;
    private final RedisMessageSubscriber subscriber;

    @Autowired
    public RedisDemoApplication(RedisMessagePublisher publisher, RedisMessageSubscriber subscriber) {
        this.publisher = publisher;
        this.subscriber = subscriber;
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            publisher.publish("hello");
            publisher.publish("java");

            Thread.sleep(1000);

            subscriber.getMessageList().forEach(m -> log.info("m:{}", m));

        };
    }

}
