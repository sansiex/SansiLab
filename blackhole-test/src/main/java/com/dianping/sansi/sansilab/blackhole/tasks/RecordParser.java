package com.dianping.sansi.sansilab.blackhole.tasks;

import com.google.common.collect.Maps;
import jodd.util.StringUtil;
import org.elasticsearch.common.xcontent.XContentBuilder;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zuhai.jiang on 2015/6/25.
 */
public class RecordParser {

    public static Map<String, String> paySchema = Maps.newHashMap();
    public static Map<String, String> refundSchema = Maps.newHashMap();

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        init();
    }

    public static Map<String, Object> toDoc(String rec, Map<String, String> schema) {
        if (!isRecordValid(rec)) {
            throw new IllegalArgumentException("Can't parse record: "+rec);
        }

        Map<String, Object> doc = Maps.newHashMap();
        String content = rec.trim();
        content = content.substring(1,content.length()-1);
        String[] pairs = content.split(",");
        for (String pair : pairs) {
            String[] kv = pair.trim().split("=");
            if (kv.length!=2){
                throw new IllegalArgumentException("Illegal kv pair: ["+pair+"] in record: "+rec);
            }
            String type = schema.get(kv[0]);
            Object val = convertValue(kv[1], type);
            doc.put(kv[0], val);
        }
        return doc;
    }

    private static Object convertValue(String raw, String type){
        if(StringUtil.isBlank(raw) || "null".equals(raw)){
            return null;
        }
        if ("int".equals(raw)) {
            return Integer.parseInt(raw);
        } else if("BigDecimal".equals(raw)){
            return Double.parseDouble(raw);
        } else if("String".equals(raw)){
            return raw;
        } else if("Date".equals(raw)){
            Long ms = Long.parseLong(raw);
            Date d = new Date(ms);
            return timeFormat.format(d);
        }
        return raw;
    }

    private static boolean isRecordValid(String rec){
        if (StringUtil.isBlank(rec)) {
            return false;
        }

        String content = rec.trim();
        if (content.length()<2){
            return false;
        }

        return true;
    }

    private static void init(){
        paySchema.put("notifyId","int");
        paySchema.put("orderId","int");
        paySchema.put("status","int");
        paySchema.put("quantity","int");
        paySchema.put("amount","BigDecimal");
        paySchema.put("accountAmount","BigDecimal");
        paySchema.put("thirdpartyAmount","BigDecimal");
        paySchema.put("couponAmount","BigDecimal");
        paySchema.put("discountAmount","BigDecimal");
        paySchema.put("redEnvelopeAmount","BigDecimal");
        paySchema.put("giftCardAmount","BigDecimal");
        paySchema.put("eventAmount","BigDecimal");
        paySchema.put("pointAmount","BigDecimal");
        paySchema.put("userId","int");
        paySchema.put("cityId","int");
        paySchema.put("productGroupId","int");
        paySchema.put("productId","int");
        paySchema.put("mobileNo","String");
        paySchema.put("ip","String");
        paySchema.put("addTime","Date");
        paySchema.put("actualTime","Date");
        paySchema.put("couponType","int");
        paySchema.put("couponId","int");
        paySchema.put("couponGroupId","int");
        paySchema.put("orderGroupId","int");
        paySchema.put("source","int");
        paySchema.put("orderItemId","int");
        paySchema.put("paymentBizTypeValue","int");
        paySchema.put("payPlatformValue","int");
        paySchema.put("paymentChannelValue","int");
        paySchema.put("payMethodValue","int");
        paySchema.put("productCodeValue","int");
        paySchema.put("advanceOrderId","String");
        paySchema.put("lockStockId","String");
        paySchema.put("orderString","String");
        paySchema.put("thirdpartySerialNo","String");
        paySchema.put("thirdPaymentAccount","String");
        paySchema.put("thirdpartyPartnerId","int");
        paySchema.put("thirdpartyOrderId","String");
        paySchema.put("dpId","String");
        paySchema.put("productVersion","String");
        paySchema.put("orderPersistendExtend","String");

        refundSchema.put("orderStatusAction","String");
        refundSchema.put("actionDescription","String");
        refundSchema.put("originalOrderId","int");
        refundSchema.put("orderItemId","int");
        refundSchema.put("productType","int");
        refundSchema.put("advanceOrderId","String");
        refundSchema.put("stockStatus","int");
        refundSchema.put("productId","int");
        refundSchema.put("productGroupId","int");
        refundSchema.put("mobileNo","String");
        refundSchema.put("cityId","int");
        refundSchema.put("addTime","Date");
        refundSchema.put("successTime","Date");
        refundSchema.put("expiredTime","Date");
        refundSchema.put("actionTime","Date");
        refundSchema.put("quantity","int");
        refundSchema.put("userId","int");
        refundSchema.put("platform","int");
        refundSchema.put("totalAmount","BigDecimal");
        refundSchema.put("status","int");
        refundSchema.put("refundStatus","int");
        refundSchema.put("thirdpartyPartnerId","int");
        refundSchema.put("thirdpartyOrderId","String");
        refundSchema.put("userIp","String");
    }
}
