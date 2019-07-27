package org.sansilab.leetcode.parser;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ArrayParser implements ParamParser {

    private ParamParser innerParser;

    private Class innerClass;

    public ArrayParser(ParamParser parser){
        innerParser = parser;
        innerClass = parser.getType();
    }

    @Override
    public Object parse(String str) {
        String[] arr = str.substring(1, str.length()-1).split(",\\s*");
        Object values = Array.newInstance(innerClass, arr.length);

        for (int i = 0; i < arr.length; i++) {
            Array.set(values, i, innerParser.parse(arr[i]));
        }

        
        return values;
    }

    @Override
    public Class getType() {
        return Array.class;
    }
}
