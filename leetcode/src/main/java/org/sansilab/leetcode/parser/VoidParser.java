package org.sansilab.leetcode.parser;

/**
 * @author sansi
 * @date 2022-09-14
 */
public class VoidParser implements ParamParser{
    @Override
    public Object parse(String str) {
        return null;
    }

    @Override
    public Class getType() {
        return null;
    }
}
