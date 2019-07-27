package org.sansilab.leetcode.parser;

public class CharParser implements ParamParser {
    @Override
    public Object parse(String str) {
        return str.charAt(0);
    }

    @Override
    public Class getType() {
        return char.class;
    }
}
