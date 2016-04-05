package com.dianping.sansi.sansilab.service;

import com.dianping.analytics.shopuser.entity.FanCount;
import com.dianping.analytics.shopuser.service.ShopUserService;
import com.dianping.dataorderetl.tasks.TransferTask;
import com.dianping.elasticsearch.aggregations.Aggregations;
import com.dianping.elasticsearch.conditions.Conditions;
import com.dianping.elasticsearch.entities.IndexCheckResult;
import com.dianping.elasticsearch.query.ESQuery;
import com.dianping.elasticsearch.query.ESSearch;
import com.dianping.elasticsearch.services.ElasticSearchService;
import com.dianping.pigeon.remoting.ServiceFactory;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import operation.api.CampaignService;
import operation.entity.dto.CampaignDTO;
import org.elasticsearch.common.joda.time.DateTime;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarFile;

/**
 * Created by zuhai.jiang on 2015/6/3.
 */
public class TestService {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        searchTest();
        long end = System.currentTimeMillis();
        System.out.println("Total Cost:"+(end - start));
//        System.exit(0);
    }

    public static void operationTest() {
        CampaignService svc = ServiceFactory.getService("http://service.dianping.com/da/push/campaignService", CampaignService.class, 60000);
        CampaignDTO dto = svc.queryCampaignMetrics("15Q3LDMS010F392");
        print(dto);
    }

    public static void listTest() {
        try {
            int a = Collections.list(new JarFile("D:\\workspaces\\idea\\SansiLab\\sparksql-test\\target\\sparksql-test-1.0-SNAPSHOT.jar").entries()).size();
            System.out.println("size:"+a);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void etlTest(){
        TransferTask task=new TransferTask();
        task.run();
    }

    public static void indexTest(){
        ElasticSearchService svc = ServiceFactory.getService("http://service.dianping.com/dw/elasticsearch/service", ElasticSearchService.class);
        IndexCheckResult res = svc.checkIndices("es_ca_use_details.2015-06-11", "es_ca_use_details.2015-06-12");
        print(res);
    }

    public static void shopUserTest(){
        long start = System.currentTimeMillis();
        ShopUserService svc = ServiceFactory.getService("http://service.dianping.com/da/shopuser/shopUserService", ShopUserService.class);
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
        ElasticSearchService svc = ServiceFactory.getService("http://service.dianping.com/dw/elasticsearch/service", ElasticSearchService.class, 60000);
        ESQuery query = svc.buildQuery("midas_kepler_effect_data")
                .setDateRange(null, null)

//                .addSort("ca_cost", false)
//                .setSize(10)
                .addCondition(Conditions.script("$<=1", new String[]{"click"}))
//                .addCondition(Conditions.range("click").gte(1))
                .groupBy("fdate", 100, null, null)
                .groupBy("ftime", 100, null, null)
//                .groupBy("slot", 100, null, null)

//                .addCondition(Conditions.isNull("dpid_phone_model"))
//                .addCondition(Conditions.range("pop_score").lte(-150))
                .addAggregation(Aggregations.sum("click"));

        printQuery(svc, query);

        List ret = svc.queryAggregation(query);
        System.out.println(new Gson().toJson(ret));
    }

    public static void searchTest(){
        ElasticSearchService svc = ServiceFactory.getService("http://service.dianping.com/dw/elasticsearch/service", ElasticSearchService.class, 60000);
        ESSearch search = svc.buildSearch("es_ca_trade_use_details_ss_summary")
                .addSort("ca_tool_id", true)
//                .setSize(10)
//                .groupBy("ca_tool_title", 100, null, null)
                .setFields(new String[]{"ca_tool_id", "op_cost"});

        printSearch(svc, search);

        List ret = svc.search(search);
        System.out.println(new Gson().toJson(ret));
    }

    public static void printQuery(ElasticSearchService svc, ESQuery query){
        String[] json = svc.getQueryJson(query);
        System.out.println(json[0]);
        System.out.println(json[1]);
    }

    public static void printSearch(ElasticSearchService svc, ESSearch search){
        String[] json = svc.getSearchJson(search);
        System.out.println(json[0]);
        System.out.println(json[1]);
    }

    public static void print(Object obj){
        Gson gson=new Gson();
        System.out.println(gson.toJson(obj));
    }

}
