package com.dianping.sansi.sansilab.storm.topologies;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import com.dianping.sansi.sansilab.storm.bolts.NumberBolt;
import com.dianping.sansi.sansilab.storm.spouts.NumberSpout;

/**
 * Created by zuhai.jiang on 2015/6/17.
 */
public class TestTopology  {

    public static void main(String[] args) {
        System.out.println("stormdemo starts");
        long startTime=System.currentTimeMillis();
        Config conf = new Config();
        conf.setDebug(true);
        conf.setNumWorkers(2);
        conf.setMaxSpoutPending(1);
        LocalCluster cluster = new LocalCluster();

        cluster.submitTopology("demo", conf, buildTopology());
        long executeTime=System.currentTimeMillis();
        Utils.sleep(30000);
        System.out.println("stormdemo结束");
        long stopTime=System.currentTimeMillis();
        System.out.println("共消耗时间：运行=" + (executeTime - startTime) + ",总时间：" + (stopTime - startTime) + "");

        cluster.killTopology("demo");
        cluster.shutdown();
    }

    public static StormTopology buildTopology(){
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("s1", new NumberSpout());
        builder.setBolt("b1", new NumberBolt()).globalGrouping("s1");
        builder.setBolt("b2", new NumberBolt()).globalGrouping("b1");
        return builder.createTopology();
    }



}
