package com.dianping.sansi.sansilab.sparksqltest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;

import java.util.Map;

/**
 * Created by zuhai.jiang on 2015/6/2.
 */
public class TestCase {
    public static void main(String[] args) {
        String appName = "testApp";
        String master = "spark://hadoop-b01.beta:7077";

        SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
        conf.set("es.index.auto.create", "true");

        JavaSparkContext jsc = new JavaSparkContext(conf);
        Map<String, ?> numbers = ImmutableMap.of("one", 1, "two", 2);
        Map<String, ?> airports = ImmutableMap.of("OTP", "Otopeni", "SFO", "San Fran");

        JavaRDD<Map<String, ?>> javaRDD = jsc.parallelize(ImmutableList.of(numbers, airports));
        JavaEsSpark.saveToEs(javaRDD, "spark/docs");
    }
}
