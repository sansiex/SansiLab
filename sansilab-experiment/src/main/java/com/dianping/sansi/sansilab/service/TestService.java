package com.dianping.sansi.sansilab.service;

import com.dianping.analytics.shopuser.entity.FanCount;
import com.dianping.analytics.shopuser.service.ShopUserService;
import com.dianping.elasticsearch.aggregations.Aggregations;
import com.dianping.elasticsearch.conditions.Conditions;
import com.dianping.elasticsearch.query.ESQuery;
import com.dianping.elasticsearch.services.ElasticSearchService;
import com.dianping.pigeon.remoting.ServiceFactory;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.elasticsearch.common.joda.time.DateTime;
import sun.misc.Unsafe;

import java.util.List;

/**
 * Created by zuhai.jiang on 2015/6/3.
 */
public class TestService {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        esTest();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.exit(0);
    }

    public static void shopUserTest(){
        long start = System.currentTimeMillis();
        ShopUserService svc = ServiceFactory.getService("http://service.dianping.com/da/shopuser/shopUserService",ShopUserService.class);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        start = System.currentTimeMillis();
        FanCount fc = svc.getFanCount(Lists.newArrayList(8683319, 4664943));
        end = System.currentTimeMillis();
        System.out.println(end - start);
        start = System.currentTimeMillis();
        print(fc);
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void codeTest(){
        String rebuyEnd = DateTime.parse("2015-05-03").minusWeeks(1).toString("yyyy-MM-dd");
        System.out.println(rebuyEnd);
    }

    public static void esTest(){
        ElasticSearchService svc = ServiceFactory.getService("http://service.dianping.com/dw/elasticsearch/service", ElasticSearchService.class);
        ESQuery query = svc.buildQuery("es_ca_use_details")
                .setDate("2015-04-15")
                .groupBy("ca_tool_title", 100, null, null)
                .addCondition(Conditions.regex("ca_tool_title", "4.*"))
                .addCondition(Conditions.or(
                        Conditions.range("ca_cost").lte(20.0)
                        , Conditions.range("ca_cost").gte(50.0)
                ))
                .addAggregation(Aggregations.max("c").field("ca_cost"));

        printQuery(svc, query);

        List ret = svc.queryAggregation(query);
        System.out.println(new Gson().toJson(ret));
    }

    public static void printQuery(ElasticSearchService svc, ESQuery query){
        String[] json = svc.getQueryJson(query);
        System.out.println(json[0]);
        System.out.println(json[1]);
    }

    public static void print(Object obj){
        Gson gson=new Gson();
        System.out.println(gson.toJson(obj));
    }
}