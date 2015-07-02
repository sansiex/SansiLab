package com.dianping.sansi.sansilab.blackhole.tasks;

import com.dp.blackhole.consumer.api.OffsetStrategy;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by zuhai.jiang on 2015/6/26.
 */
public class ESOffsetStrategy implements OffsetStrategy {
    private static final String OFFSET_FILE_PATH="offset";
    private String tableName;

    public ESOffsetStrategy(String tableName){
        this.tableName = tableName;
    }

    private String getFilePath(){
        return OFFSET_FILE_PATH + "_" + tableName;
    }

    @Override
    public long getOffset(String topic, String partitionId, long endOffset, long committedOffset) {
        long offset = -1;
        try {
            offset = loadOffset();
        } catch (IOException e) {}
        if (-1 == offset) {
            return committedOffset;
        }
        return offset;
    }

    private long loadOffset() throws IOException {
        String line = Files.readFirstLine(new File(OFFSET_FILE_PATH), Charset.forName("utf-8"));
        long offset = Long.parseLong(line);
        return offset;
    }
}
