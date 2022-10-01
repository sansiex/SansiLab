package org.sansilab.leetcode.parser;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.sansilab.leetcode.utils.JsonUtils;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ArrayParser implements ParamParser {

    private ParamParser innerParser;

    private Class innerClass;

    public ArrayParser(ParamParser parser){
        innerParser = parser;
        innerClass = parser.getType();
    }

    @Override
    public Object parse(String str) {
//        String s = str.trim();
//        s = s.substring(1, s.length()-1);
//        if (s.length() == 0) {
//            return Array.newInstance(innerClass, 0);
//        }
//        String[] arr = s.split(",\\s*");
//        Object values = Array.newInstance(innerClass, arr.length);
//
//        for (int i = 0; i < arr.length; i++) {
//            Array.set(values, i, innerParser.parse(arr[i]));
//        }
//
//        return values;

        JsonArray arr = JsonUtils.parseArray(str);
        Object values = Array.newInstance(innerClass, arr.size());

        for (int i = 0; i < arr.size(); i++) {
            JsonElement ele = arr.get(i);
            String s = ele.getAsString();
            Array.set(values, i, innerParser.parse(s));
        }

        return values;
    }

    @Override
    public Class getType() {
        return Array.class;
    }
}
