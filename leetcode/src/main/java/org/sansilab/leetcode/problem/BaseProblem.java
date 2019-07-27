package org.sansilab.leetcode.problem;

import com.google.common.collect.Lists;
import org.sansilab.leetcode.parser.ParamParser;
import org.sansilab.leetcode.utils.JsonUtils;

import java.util.List;

/**
 * Created by zuhai.jiang on 2016/8/1.
 */
public class BaseProblem {
    protected void printResult(Object result){

        System.out.println(JsonUtils.toJson(result));
    }
}
