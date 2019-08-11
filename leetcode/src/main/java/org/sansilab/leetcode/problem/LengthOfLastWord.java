package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.StringParser;

public class LengthOfLastWord extends AbstractProblem {
    public int lengthOfLastWord(String s) {
        int e = -1;
        for (int i = s.length()-1; i >=0 ; i--) {
            char c = s.charAt(i);
            if (c!=' ') {
                e = i;
                break;
            }
        }
        if (e == -1) {
            return 0;
        }
        int l=1;
        for (int i = e-1; i > -1 ; i--) {
            char c = s.charAt(i);
            if (c!=' ') {
                l++;
            } else {
                break;
            }
        }
        return l;
    }

    @Override
    void init() {
        parserList.add(new StringParser());

        addInput("Hello World");
        outputList.add(5);


        addInput("Hello World    ");
        outputList.add(5);
    }

    @Override
    Object execute(Object[] input) {
        return lengthOfLastWord((String) input[0]);
    }

    public static void main(String[] args) {
        new LengthOfLastWord().runTest();
    }
}
