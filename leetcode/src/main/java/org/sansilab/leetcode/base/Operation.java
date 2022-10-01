package org.sansilab.leetcode.base;

import org.sansilab.leetcode.parser.ParamParser;
import org.sansilab.leetcode.parser.VoidParser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Operation {
    String value();
}
