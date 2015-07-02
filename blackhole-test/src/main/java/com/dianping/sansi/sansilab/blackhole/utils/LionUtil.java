package com.dianping.sansi.sansilab.blackhole.utils;

import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.lion.client.ConfigCache;
import com.dianping.lion.client.LionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by lenovo on 2014/11/14.
 */
public class LionUtil {
    private LionUtil(){}
    private static LionUtil instance=null;
    public static LionUtil getInstance(){
        synchronized ( LionUtil.class ) {
            if (instance == null) {
                instance = new LionUtil();
            }
        }
        return instance;
    }

    private Logger log = LoggerFactory.getLogger(LionUtil.class);

    private HashMap<String, String> valueMap=new HashMap<String, String>();

    public String getProperty(String key){
        try {
            String val=ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).getProperty(key);
            return val;
        } catch (LionException e) {
            log.info("Failed to load value of key:"+key+" from lion. message:"+e.getMessage());
            return null;
        }
    }

    public String getCachedProperty(String key){
        if(valueMap.containsKey(key)){
            return valueMap.get(key);
        }
        try {
            String val=ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).getProperty(key);
            valueMap.put(key,val);
            return val;
        } catch (LionException e) {
            log.info("Failed to load value of key:"+key+" from lion. message:"+e.getMessage());
            return null;
        }
    }
}
