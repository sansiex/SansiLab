package com.dianping.sansi.sansilab.blackhole.handlers;

import com.dianping.sansi.sansilab.blackhole.utils.ElasticSearchUtil;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zuhai.jiang on 2015/6/26.
 */
public class ESWriter {

    private static final int BULK_SIZE = 2000;
//    private static final int FLUSH_PERIOD = 5000;
    private static final String TYPE="docs";

//    private BulkRequestBuilder bulkRequest=null;
//    private long bulkTimestamp = 0;
    private ElasticSearchUtil es = ElasticSearchUtil.getInstance();
    private Client client = null;
    private int recordCount=0;
    private String indexName=null;

    public ESWriter(String indexName){
        this.indexName = indexName;
    }

    public void init(){
        client = es.getClient();
//        bulkRequest = client.prepareBulk();
    }

    public boolean write(Map record, String date, String id) throws ElasticsearchException {
        String index = getIndex(date);
        IndexResponse response = client.prepareIndex(index, TYPE, id)
                .setSource(record)
                .execute()
                .actionGet();
        recordCount++;
        if (recordCount >= BULK_SIZE) {
            System.out.println("A bulk( "+recordCount+" records) is wrote to es.");
            return true;
        }
        return false;
    }

//    private boolean triggerBulkRequest() throws IOException {
//        recordCount = 0;
//
//        if (bulkResponse.hasFailures()) {
//            fail(bulkResponse);
//        }else{
//            System.out.println("docs imported: " + bulkRequest.numberOfActions());
//            bulkRequest = client.prepareBulk();
//        }
//        return true;
//    }

    private void fail(BulkResponse bulkResponse) throws IOException {
        System.out.println("bulk request has errors" + bulkResponse.buildFailureMessage());
        throw new IOException("Failed to write bulk of records.");
    }

    private String getIndex(String date){
        if(date==null){
            return indexName;
        }
        return indexName+"."+date;
    }

//    private void prepareBulkRequest(){
//        bulkRequest = client.prepareBulk();
//    }

    public void close(){
        client.close();
    }
}
