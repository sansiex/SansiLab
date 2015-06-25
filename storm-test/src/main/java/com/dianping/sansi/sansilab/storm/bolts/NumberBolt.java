package com.dianping.sansi.sansilab.storm.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 * Created by zuhai.jiang on 2015/6/17.
 */
public class NumberBolt implements IRichBolt {

    private OutputCollector collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        try {
            System.out.println("execute----->处理数据");
            Double num= (Double)input.getValueByField("number");
            System.out.println("处理数据  Number=" + num);
            collector.emit(new Values(num+7));
            collector.ack(input);
        }catch (Exception e) {
            collector.fail(input);
            System.out.println("处理数据出现异常");
            e.printStackTrace();
        }
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("number"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
