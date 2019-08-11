package org.sansilab.leetcode.parser;

import com.google.gson.JsonArray;
import org.sansilab.leetcode.utils.JsonUtils;

import java.lang.reflect.Array;

public class TwoDimensionCharArrayParser implements ParamParser {
    @Override
    public Object parse(String str) {
        JsonArray arr = JsonUtils.parseArray(str);
        int h = arr.size();
        if (h == 0) {
            return new char[0][0];
        }
        int w = arr.get(0).getAsString().length();
        char[][] ret = new char[h][w];
        for (int i = 0; i < h; i++) {
            String s = arr.get(i).getAsString();
            for (int j = 0; j < w; j++) {
                char c = s.charAt(j);
                ret[i][j] = c;
            }
        }

        return ret;
    }

    @Override
    public Class getType() {
        return Array.class;

    }
}
