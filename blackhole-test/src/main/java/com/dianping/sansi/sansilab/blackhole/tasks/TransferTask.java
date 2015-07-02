package com.dianping.sansi.sansilab.blackhole.tasks;

import com.dianping.sansi.sansilab.blackhole.handlers.ESHandler;
import com.dianping.sansi.sansilab.blackhole.handlers.TestHandler;

/**
 * Created by zuhai.jiang on 2015/6/26.
 */
public class TransferTask {

    private static final String topic1="dpods_log_data-order-etl-service_pay";
    private static final String topic2="dpods_log_data-order-etl-service_refund";
    private static final String TOPIC_TEST="dpods_hippo_log"; //for test
    private static final String group="es";

    public static void main(String[] args) {
//        String timeField1="addTime";
//        String[] idFields1={"orderId","orderItemId"};
//        ESHandler payHandler = new ESHandler(topic1, timeField1, idFields1, RecordParser.paySchema);
//        BlackholeListener listener1 = new BlackholeListener(topic1, group, payHandler, new ESOffsetStrategy(topic1));
//        listener1.listen();
//
//        String timeField2="addTime";
//        String[] idFields2={"orderItemId","originalOrderId"};
//        ESHandler refundHandler = new ESHandler(topic1, timeField2, idFields2, RecordParser.refundSchema);
//        BlackholeListener listener2 = new BlackholeListener(topic2, group, refundHandler, new ESOffsetStrategy(topic2));
//        listener2.listen();

        BlackholeListener listener3 = new BlackholeListener(topic1, group, new TestHandler());
        listener3.listen();
    }
}
