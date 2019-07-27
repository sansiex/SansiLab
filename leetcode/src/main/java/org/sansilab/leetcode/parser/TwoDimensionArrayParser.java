package org.sansilab.leetcode.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.sansilab.leetcode.utils.JsonUtils;

import java.lang.reflect.Array;

public class TwoDimensionArrayParser implements ParamParser {

    private ParamParser innerParser;

    public TwoDimensionArrayParser(ParamParser innerParser) {
        this.innerParser = innerParser;
    }

    @Override
    public Object parse(String str) {
        JsonArray arr = JsonUtils.parseArray(str);
        int size1 = arr.size();
        int size2 = -1;
        Object ret = null;
        for (int i = 0; i < size1; i++) {
            JsonArray arr2 = (JsonArray) arr.get(i);
            if (size2 == -1) {
                size2 = arr2.size();
                ret = Array.newInstance(innerParser.getType(), size1, size2);
            }
            Object carr = Array.get(ret, i);
            for (int j = 0; j < size2; j++) {
                JsonPrimitive prim = (JsonPrimitive) arr2.get(j);
                Object val = innerParser.parse(prim.getAsString());
                Array.set(carr, j, val);
            }
        }
        return ret;
    }

    @Override
    public Class getType() {
        return Array.class;
    }
}
