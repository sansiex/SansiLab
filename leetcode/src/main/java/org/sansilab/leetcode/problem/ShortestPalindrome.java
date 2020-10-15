package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.StringParser;

public class ShortestPalindrome extends AbstractProblem {

    public String shortestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }

        return manacher(s);
    }

    public static char[] insertDevider(char[] origin, char divider){
        int len = origin.length;
        char[] res = new char[2*len+1];
        res[0] = divider;
        for (int i = 0; i < len; i++) {
            res[i*2+1] = origin[i];
            res[i*2+2] = divider;
        }
        return res;
    }

    public static String manacher(String str) {
        if (str.length()==0) {
            return null;
        }

        char[] s = insertDevider(str.toCharArray(), '#');
        int len = s.length;
        int id=0;
        int mx=0;
        int idl=0;
        int[] p = new int[len];
        for (int i = 0; i < len/2 +1; i++) {
            if (i<mx) {
                p[i] = Math.min(p[2*id - i], mx - i);
            } else {
                p[i] = 1;
            }

            while (i-p[i] > -1 && i+p[i] < s.length && s[i - p[i]] == s[i+ p[i]]) {
                p[i]++;
            }

            if (i + p[i] > mx) {
                mx = i + p[i];
                id = i;
            }

            if (i - p[i] + 1 == 0) {
                idl = i;
            }
        }

        int r = idl + p[idl] - 1;
        r = r/2 - 1;
        int lenPlus = str.length() - r - 1;
        char[] res = new char[str.length() + lenPlus];
        for (int i = 0; i < lenPlus; i++) {
            res[i] = str.charAt(str.length()-1-i);
        }
        for (int i = 0; i < str.length(); i++) {
            res[lenPlus+i] = str.charAt(i);
        }

        return new String(res);
    }

    @Override
    void init() {
        parserList.add(new StringParser());
        setOutputParser(new StringParser());

        addInput("aacecaaa");
        addOutput("aaacecaaa");

        addInput("abcd");
        addOutput("dcbabcd");

        addInput("abbac");
        addOutput("cabbac");

        addInput("abba");
        addOutput("abba");

        addInput("a");
        addOutput("a");

        addInput("");
        addOutput("");

    }

    @Override
    Object execute(Object[] input) {
        return shortestPalindrome((String) input[0]);
    }

    public static void main(String[] args) {
        new ShortestPalindrome().runTest();
    }
}
