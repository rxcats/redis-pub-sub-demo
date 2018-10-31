package io.github.rxcats.redisdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisMessageSubscriber implements MessageListener {

    private List<String> messageList = new ArrayList<>();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        var body = new String(message.getBody());
        log.info("message:{}", message);
        messageList.add(body);
    }

    List<String> getMessageList() {
        return messageList;
    }

}
