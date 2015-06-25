package com.dianping.sansi.sansilab.storm.spouts;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.*;

/**
 * Created by zuhai.jiang on 2015/6/17.
 */
public class NumberSpout implements IRichSpout{

    private SpoutOutputCollector collector;
    private Map conf;
    private TopologyContext context;
    private Queue<Double> queue = new LinkedList<Double>();;

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        System.out.println("declareOutputFields----> 设置输出字段");
        outputFieldsDeclarer.declare(new Fields("number"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        // TODO Auto-generated method stub
        System.out.println("spout-----> 发射随机数");
        this.collector = spoutOutputCollector;
        this.conf = map;
        this.context = topologyContext;

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<600;i++) {
                    queue.add(Math.random());
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void close() {
        System.out.println("close---------->");
    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void nextTuple() {
        Double num = queue.poll();
        if (num != null) {
            System.out.println("send number:"+num);
            collector.emit(new Values(num));
        }
    }

    @Override
    public void ack(Object o) {
        System.out.println("ack---------->"+o);
    }

    @Override
    public void fail(Object o) {
        System.out.println("fail----------->"+o);
    }
}
