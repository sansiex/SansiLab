package com.dianping.sansi.sansilab.blackhole.tasks;

import com.dp.blackhole.consumer.ConsumerConnector;
import com.dp.blackhole.consumer.MessageStream;
import com.dp.blackhole.consumer.api.*;
import com.dp.blackhole.consumer.api.decoder.StringDecoder;

import java.util.Properties;

/**
 * Created by zuhai.jiang on 2015/6/25.
 */
public class TestCase {
//    private static final String TOPIC="dpods_log_data-order-etl-service_pay";
    private static final String TOPIC="dpods_hippo_log";
    private static final String GROUP="test";

    public static void main(String[] args) throws InterruptedException {
        Properties prop = new Properties();
        ConsumerConfig config = new ConsumerConfig(prop);
        Consumer consumer = new Consumer(TOPIC, GROUP, config, new CommittedOffsetStrategy());
        consumer.start();
        System.out.println("start");
        Thread.sleep(3000);

        System.out.println("get stream");
        MessageStream stream = consumer.getStream();
        StringDecoder decoder = new StringDecoder();
        System.out.println("start traverse");
        int i=0;
        for (MessagePack entity : stream) {
            System.out.println("entity "+i);
            if (i % 100000L == 0) {
                System.out.println("consumed: " + i);
            }
            String record = decoder.decode(entity).trim();
            System.out.println("Record "+i+":"+record);
            i++;



//            break;
        }
        System.out.println("finish");
    }
}
