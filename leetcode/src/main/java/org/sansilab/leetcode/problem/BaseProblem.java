package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

/**
 * Created by zuhai.jiang on 2016/8/1.
 */
public class BaseProblem {
    protected void printResult(Object result){
        JsonUtils.toJson(result);
    }
}
