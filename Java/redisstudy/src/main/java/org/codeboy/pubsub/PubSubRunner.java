package org.codeboy.pubsub;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class PubSubRunner {
    public static void main(String[] args) {
        int port = 6379;
        String host = "39.105.189.141";
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisPool pool = new JedisPool(poolConfig, host, port);
        String channelName = "Channel01";
        PublishThread publishThread = new PublishThread(channelName, pool);
        SubscribeThread subscribeThread1 = new SubscribeThread(channelName, pool);
        SubscribeThread subscribeThread2 = new SubscribeThread(channelName, pool);

        subscribeThread1.start();
        subscribeThread2.start();
        publishThread.start();
    }
}
