package org.sansilab.leetcode.parser;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.sansilab.leetcode.utils.JsonUtils;

import java.util.List;

public class ListParser implements ParamParser {
    private ParamParser innerParser;

    private Class innerClass;

    public ListParser(ParamParser parser){
        innerParser = parser;
        innerClass = parser.getType();
    }

    @Override
    public Object parse(String str) {
        JsonArray arr = JsonUtils.parseArray(str);
        List list = Lists.newArrayListWithCapacity(arr.size());

        for (int i = 0; i < arr.size(); i++) {
            JsonElement ele = arr.get(i);
            String s = ele.getAsString();
            list.add(i, innerParser.parse(s));
        }

        return list;
    }

    @Override
    public Class getType() {
        return List.class;
    }
}
