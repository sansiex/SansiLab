package org.sansilab.leetcode.parser;

public abstract class ContainerParser<T extends ParamParser> implements ParamParser {

    abstract String[] extractContent(String s);

    @Override
    public Object parse(String str) {
        return null;
    }
}
