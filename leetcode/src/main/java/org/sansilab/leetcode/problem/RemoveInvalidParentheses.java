package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ListParser;
import org.sansilab.leetcode.parser.StringParser;
import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/remove-invalid-parentheses/
public final class RemoveInvalidParentheses extends AbstractProblem{

    public static void main(String[] args) {
        new RemoveInvalidParentheses().runTest();
    }

    @Answer
    public List<String> removeInvalidParentheses(String s) {
        int start = 0;
        StringBuilder sb = new StringBuilder();
        while(start<s.length() && s.charAt(start) != '('){
            char c = s.charAt(start);
            if (c != ')') {
                sb.append(c);
            }
            start++;
        }
        int end = s.length()-1;
        while(s.charAt(end) != ')'){
            end--;
        }
        sb.append(s.substring(start, end+1));
        while(end<s.length() && s.charAt(end)!='('){
            sb.append(s.charAt(end));
        }

        return null;
    }

    public void recurse(String s, int start, int lCnt, int rCnt, StringBuilder sb, List<String> ret){

    }

    @Override
    void init() {
        addInputParsers(new StringParser());
        setOutputParser(new ListParser(new StringParser()));

        addInput("()())()");
        addOutput("[\"()()()\", \"(())()\"]");
        addInput("(a)())()");
        addOutput("[\"(a)()()\"]");
        addInput(")(");
        addOutput("[]");
    }
}
