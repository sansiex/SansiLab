package org.sansilab.leetcode.parser;

public interface ParamParser {

    Object parse(String str);

    Class getType();

}
