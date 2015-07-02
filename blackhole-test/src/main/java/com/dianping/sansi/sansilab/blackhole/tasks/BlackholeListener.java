package com.dianping.sansi.sansilab.blackhole.tasks;

import com.dianping.sansi.sansilab.blackhole.handlers.DataHandler;
import com.dp.blackhole.consumer.MessageStream;
import com.dp.blackhole.consumer.api.*;
import com.dp.blackhole.consumer.api.decoder.StringDecoder;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Created by zuhai.jiang on 2015/6/26.
 */
public class BlackholeListener {

    private String topic;
    private String group;
    private static Consumer consumer=null;
    private DataHandler handler;
    private OffsetStrategy offsetStrategy;


    public BlackholeListener(String topic, String group, DataHandler handler){
        this.topic=topic;
        this.group=group;
        this.handler=handler;
        this.offsetStrategy=new CommittedOffsetStrategy();
    }

    public BlackholeListener(String topic, String group, DataHandler handler, OffsetStrategy offsetStrategy){
        this.topic=topic;
        this.group=group;
        this.handler=handler;
        this.offsetStrategy=offsetStrategy;
    }

    public void listen(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                startListener();
            }
        }).start();
    }

    private void startListener(){
        init();
        StringDecoder decoder = new StringDecoder();
        MessageStream stream = consumer.getStream();
        int i=0;
        System.out.println(topic+" >> Start receiving and transferring data.");
        for (MessagePack entity : stream) {
            if (i % 10000L == 0) {
                System.out.println(topic+" >> consumed: " + i);
            }
            String record = decoder.decode(entity).trim();
            long offset = entity.getOffset();
            System.out.println(topic+" >> Received: "+record);
            try {
                boolean committed = handler.handle(record);
                if (committed){
                    storeOffset(offset);
                }
            } catch (Exception e) {
                fail();
            }
            i++;
        }
        System.out.println("finish");
    }

    private void fail(){
        System.out.println(topic+" >> Encountered error during data transferring, try to recover...");
        handler.destroy();
        try {
            consumer.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startListener();
    }

    private void init(){
        System.out.println(topic+" >> Init listener.");
        handler.init();

        Properties prop = new Properties();
        ConsumerConfig config = new ConsumerConfig(prop);
        consumer = new Consumer(topic, group, config, offsetStrategy);
        consumer.start();
        System.out.println(topic+" >> start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("offset");
        if (!file.exists()){
            file.createNewFile();
        }
        Files.write("20", file, Charset.forName("utf-8"));
    }

    private void storeOffset(long offset) throws IOException {
        File file = new File("offset");
        if (!file.exists()){
            file.createNewFile();
        }
        Files.write(String.valueOf(offset), file, Charset.forName("utf-8"));
    }
}
