package org.sansilab.leetcode.parser;

public class QuotedParser implements ParamParser{

    private ParamParser innerParser;

    public QuotedParser(ParamParser innerParser){
        this.innerParser = innerParser;
    }

    @Override
    public Object parse(String str) {
        return innerParser.parse(str.substring(1, str.length()-1));
    }

    @Override
    public Class getType() {
        return innerParser.getType();
    }
}
