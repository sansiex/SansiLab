package org.sansilab.leetcode.parser;

public class BooleanParser implements ParamParser {
    @Override
    public Object parse(String str) {
        return Boolean.parseBoolean(str);
    }

    @Override
    public Class getType() {
        return boolean.class;
    }
}
