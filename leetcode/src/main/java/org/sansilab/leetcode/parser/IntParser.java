package org.sansilab.leetcode.parser;

public class IntParser implements ParamParser {
    @Override
    public Object parse(String str) {
        return Integer.valueOf(str);
    }

    @Override
    public Class getType() {
        return int.class;
    }
}
