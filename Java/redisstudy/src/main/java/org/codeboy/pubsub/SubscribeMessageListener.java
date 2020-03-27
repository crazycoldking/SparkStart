package org.codeboy.pubsub;

import lombok.extern.java.Log;
import redis.clients.jedis.JedisPubSub;

@Log
public class SubscribeMessageListener extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        log.info(String.format("在%s上收到了消息%s", channel, message));
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        log.info(String.format("在%s上订阅消息", channel));
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        log.info(String.format("取消订阅通道%s", channel));
    }
}
