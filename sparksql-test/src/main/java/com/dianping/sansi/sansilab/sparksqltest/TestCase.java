package com.dianping.sansi.sansilab.sparksqltest;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zuhai.jiang on 2015/6/2.
 */
public class TestCase {

    public static final String MASTER="spark://192.168.224.146:7077";

    public static void main(String[] args) {
////        SparkConf conf = new SparkConf().setAppName("simpledemo").setMaster(MASTER);
//        SparkConf conf = new SparkConf().setAppName("simpledemo").setMaster("local");
//        JavaSparkContext sc = new JavaSparkContext(conf);
//        SQLContext sqlCtx = new SQLContext(sc);
////        TestCase.testQueryJson(sqlCtx);
//        TestCase.testQueryEs(sqlCtx);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = df.parse("2015-01-01 00");
            System.out.println(d.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //测试spark sql直接查询JSON格式的数据
    public static void testQueryJson(SQLContext sqlCtx) {
        DataFrame df = sqlCtx.jsonFile("C:/Users/zuhai.jiang/Desktop/temp");
        df.printSchema();

        // Register the input schema RDD
        df.registerTempTable("account");

        DataFrame accs = sqlCtx.sql("SELECT address, email,id,name FROM account ORDER BY id LIMIT 10");
        List<Row> result = accs.collectAsList();
        for (Row row : result) {
            System.out.println(row.getString(0) + "," + row.getString(1) + "," + row.getLong(2) + ","
                    + row.getString(3));
        }



//        DataFrame names = accs.map(new Function<Row, String>() {
//            @Override
//            public String call(Row row) throws Exception {
//                return row.getString(3);
//            }
//        });
//        System.out.println(names.collect());
    }

    public static void testQueryEs(SQLContext sqlContext){
        long s1 = System.currentTimeMillis();
        Map<String, String> options = Maps.newHashMap();
        options.put("path", "sparksql/docs");
        options.put("pushdown", "true");
        options.put("es.nodes", "hadoop-b01.beta");
        options.put("es.port", "9200");
        DataFrame df = sqlContext.load("org.elasticsearch.spark.sql", options);
        df.registerTempTable("sparksql");

        Map<String, String> options2 = Maps.newHashMap();
        options2.put("path", "orderitem/docs");
        options2.put("pushdown", "true");
        options2.put("es.nodes", "hadoop-b01.beta");
        options2.put("es.port", "9200");
        DataFrame df2 = sqlContext.load("org.elasticsearch.spark.sql", options2);
        df2.registerTempTable("orderitem");
        long s11 = System.currentTimeMillis();
//        DataFrame results = sqlContext.sql("select count(distinct id) from sparksql");
        DataFrame results = sqlContext.sql("select * from orderitem right outer join sparksql on orderitem.userId = sparksql.id");
        printArray(results.columns());
        long s2 = System.currentTimeMillis();
        Row[] rows = results.collect();
        long s3 = System.currentTimeMillis();
        printRows(rows);
        System.out.println(s11-s1);
        System.out.println(s2-s11);
        System.out.println(s3-s2);
    }

    public static void printRows(Row[] rows){
        for (Row row:rows) {
            System.out.println(row.toString()+row.get(0));
        }
    }

    public static void printArray(Object[] arr){
        for (Object item:arr) {
            System.out.println(item.toString());
        }
    }
}
