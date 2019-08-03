package org.sansilab.leetcode.parser;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class TwoDimensionListParser implements ParamParser {

    private ParamParser innerParser;

    public TwoDimensionListParser(ParamParser innerParser) {
        this.innerParser = innerParser;
    }

    @Override
    public Object parse(String str) {
        JsonArray arr = JsonUtils.parseArray(str);
        int size1 = arr.size();
        List ret = new ArrayList();
        for (int i = 0; i < size1; i++) {
            JsonArray arr2 = (JsonArray) arr.get(i);
            int size2 = arr2.size();
            List clist = new ArrayList();
            for (int j = 0; j < size2; j++) {
                JsonPrimitive prim = (JsonPrimitive) arr2.get(j);
                Object val = innerParser.parse(prim.getAsString());
                clist.add(val);
            }
            ret.add(clist);
        }
        return ret;
    }

    @Override
    public Class getType() {
        return List.class;
    }
}
