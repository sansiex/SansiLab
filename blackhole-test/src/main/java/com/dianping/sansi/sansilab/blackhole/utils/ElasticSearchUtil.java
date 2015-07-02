package com.dianping.sansi.sansilab.blackhole.utils;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.sansi.sansilab.blackhole.constants.LionKeyConsts;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import org.elasticsearch.action.admin.indices.optimize.OptimizeRequestBuilder;
import org.elasticsearch.action.admin.indices.optimize.OptimizeResponse;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequestBuilder;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.indices.IndexMissingException;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by zuhai.jiang on 2014/12/5.
 */

public class ElasticSearchUtil {
    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchUtil.class);

    private static ElasticSearchUtil instance;
    public static final String ES_HOST = LionUtil.getInstance().getProperty(LionKeyConsts.ES_HOST);
    public static final String ES_PORT = LionUtil.getInstance().getProperty(LionKeyConsts.ES_PORT);
    public static final String ES_CLUSTER_NAME = LionUtil.getInstance().getProperty(LionKeyConsts.ES_CLUSTER_NAME);
    public static final String ES_PORT_JAVA = LionUtil.getInstance().getProperty(LionKeyConsts.ES_PORT_JAVA);

    private ElasticSearchUtil() {
    }

    public static synchronized ElasticSearchUtil getInstance() {
        if (instance == null) {
            instance = new ElasticSearchUtil();
        }
        return instance;
    }

    public TransportClient getClient() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("client.transport.ignore_cluster_name", true)
                .put("client.transport.ping_timeout", "5s")
                .put("cluster.name", ES_CLUSTER_NAME)
                .build();
        TransportClient client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(ES_HOST, Integer.parseInt(ES_PORT_JAVA)));

        return client;
    }
}
