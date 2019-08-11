package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.StringParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SimplifyPath extends AbstractProblem {
    public String simplifyPath(String path) {
        String[] p = path.replaceAll("/+", "/").split("/");
        List<String> s = new ArrayList<>();
        for (int i = 1; i < p.length; i++) {
            String cp = p[i];
            if (".".equals(cp)) {
                continue;
            } else if ("..".equals(cp)) {
                if (s.isEmpty()) {
                    continue;
                }
                s.remove(s.size()-1);
            } else {
                s.add(cp);
            }
        }
        if (s.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (String cp:s) {
            sb.append("/").append(cp);
        }
        return sb.toString();
    }

    @Override
    void init() {
        parserList.add(new StringParser());

        addInput("/home/");
        outputList.add("/home");

        addInput("/../");
        outputList.add("/");

        addInput("/home//foo/");
        outputList.add("/home/foo");

        addInput("/a/./b/../../c/");
        outputList.add("/c");

        addInput("/a/../../b/../c//.//");
        outputList.add("/c");

        addInput("/a//b////c/d//././/..");
        outputList.add("/a/b/c");
    }

    @Override
    Object execute(Object[] input) {
        return simplifyPath((String) input[0]);
    }

    public static void main(String[] args) {
        new SimplifyPath().runTest();
    }
}
