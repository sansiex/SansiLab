package org.sansilab.leetcode.parser;

public class StringParser implements ParamParser {
    @Override
    public Object parse(String str) {
        return str;
    }

    @Override
    public Class getType() {
        return String.class;
    }
}
