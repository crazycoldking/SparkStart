package org.codeboy.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PublishThread extends Thread {

    public static final String EXIT = "exit";
    private String channelName;
    private JedisPool pool;

    public PublishThread(String channelName, JedisPool pool) {
        this.channelName = channelName;
        this.pool = pool;
    }

    @Override
    public void run() {
        Jedis jedis = pool.getResource();
        jedis.auth("qfjava");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String msg = null;
        try {
            while (!EXIT.equalsIgnoreCase(msg)) {
                msg = bufferedReader.readLine();
                jedis.publish(channelName, msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
