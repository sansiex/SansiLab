package org.sansilab.leetcode.parser;

import com.google.common.collect.Lists;

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
        String s = str.trim();
        String[] arr = s.substring(1, s.length()-1).split(",\\s*");
        List list = Lists.newArrayListWithCapacity(arr.length);

        for (int i = 0; i < arr.length; i++) {
            list.add(i, innerParser.parse(arr[i]));
        }


        return list;
    }

    @Override
    public Class getType() {
        return List.class;
    }
}
