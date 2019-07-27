package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.StringParser;

public class LongestPalindromicSubstring extends AbstractProblem {

    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int max=0;
        int[] p={0,0};
        boolean[][] dp=new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i]=true;
        }

        for (int i = s.length()-2; i >= 0; i--) {
            for (int j = i+1; j < s.length(); j++) {
                if(s.charAt(i)==s.charAt(j) && (j-i==1 || dp[i+1][j-1])){
                    dp[i][j]=true;
                    if(j-i+1>max){
                        max=j-i+1;
                        p[0]=i;
                        p[1]=j;
                    }
                }else{
                    dp[i][j]=false;
                }
            }
        }

        return s.substring(p[0],p[1]+1);
    }

    @Override
    void init() {
        parserList.add(new StringParser());

        inputList.add(new String[]{"babad"});
        outputList.add("aba");
        inputList.add(new String[]{"cbbd"});
        outputList.add("bb");
    }

    @Override
    Object execute(Object[] input) {
        return longestPalindrome((String) input[0]);
    }

    public static void main(String[] args) {
        new LongestPalindromicSubstring().runTest();
    }
}
