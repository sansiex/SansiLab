package com.dianping.sansi.sansilab.blackhole.handlers;

import com.dianping.sansi.sansilab.blackhole.tasks.RecordParser;
import com.google.common.base.Joiner;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by zuhai.jiang on 2015/6/26.
 */
public class ESHandler implements DataHandler {

    private ESWriter writer;
    private String topic;
    private String timeField;
    private String[] idFields;
    private Map<String, String> schema;

    public ESHandler(String topic, String timeField, String[] idFields, Map<String, String> schema){
        this.topic=topic;
        this.timeField=timeField;
        this.idFields=idFields;
        this.schema = schema;
    }

    @Override
    public void init() {
        writer = new ESWriter(topic);
        writer.init();
    }

    @Override
    public boolean handle(String record) throws ElasticsearchException {
        Map<String, Object> doc = RecordParser.toDoc(record, schema);
        String date=getDate(doc);
        String id=getId(doc);
        return writer.write(doc, date, id);
    }

    @Override
    public void destroy() {
        writer.close();
    }

    private String getDate(Map<String, Object> record){
        Object obj = record.get(timeField);
        if (obj==null) {
            return null;
        }
        Long ms = Long.parseLong(obj.toString());
        Date date=new Date(ms);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String ret = df.format(date);
        return ret;
    }

    private String getId(Map<String, Object> record){
        String[] values=new String[idFields.length];
        for (int i=0;i<idFields.length;i++){
            Object obj = record.get(idFields[i]);
            if (obj==null) {
                values[i]="null";
            } else {
                values[i]=obj.toString();
            }
        }
        String id= Joiner.on("-").join(values);
        return id;
    }
}
