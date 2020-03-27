package org.codeboy.pubsub;

import lombok.extern.java.Log;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Log
public class SubscribeThread extends Thread {

    private String channelName;
    private JedisPool pool;
    private SubscribeMessageListener subscribeMessageListener;

    public SubscribeThread(String channelName, JedisPool pool) {
        this.channelName = channelName;
        this.pool = pool;
        this.subscribeMessageListener = new SubscribeMessageListener();
    }

    @Override
    public void run() {
        log.info(String.format("Subscriber is ready..."));
        Jedis jedis = pool.getResource();
        jedis.auth("qfjava");
        jedis.subscribe(subscribeMessageListener, channelName);
    }
}
