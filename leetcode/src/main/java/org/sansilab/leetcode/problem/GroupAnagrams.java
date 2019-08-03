package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.QuotedParser;
import org.sansilab.leetcode.parser.StringParser;
import org.sansilab.leetcode.parser.TwoDimensionListParser;

import java.util.*;

public class GroupAnagrams extends AbstractProblem {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new LinkedList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String tmp = new String(chars);
            List<String> list = map.get(tmp);
            if (list == null) {
                list = new LinkedList<>();
                map.put(tmp, list);
                ret.add(list);
            }
            list.add(str);
        }

        return ret;
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new QuotedParser(new StringParser())));
        setOutputParser(new TwoDimensionListParser(new StringParser()));

        addInput("[\"eat\", \"tea\", \"tan\", \"ate\", \"nat\", \"bat\"]");
        addOutput("[[\"eat\",\"tea\",\"ate\"],[\"tan\",\"nat\"],[\"bat\"]]");
    }

    @Override
    Object execute(Object[] input) {
        return groupAnagrams((String[]) input[0]);
    }

    public static void main(String[] args) {
        new GroupAnagrams().runTest();
    }
}
