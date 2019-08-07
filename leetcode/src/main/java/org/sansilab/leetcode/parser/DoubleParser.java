package org.sansilab.leetcode.parser;

public class DoubleParser implements ParamParser {
    @Override
    public Object parse(String str) {
        return Double.parseDouble(str);
    }

    @Override
    public Class getType() {
        return double.class;
    }
}
