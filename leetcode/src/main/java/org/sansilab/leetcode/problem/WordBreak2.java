package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ListParser;
import org.sansilab.leetcode.parser.QuotedParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordBreak2 extends AbstractProblem {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict.size()*2);
        int max = 0;
        int min = 0;
        for (String w:wordDict) {
            max = Math.max(max, w.length());
            min = Math.min(min, w.length());
            set.add(w);
        }

        List<String> ret = new LinkedList<>();

        if (!wordBreak1(s, wordDict)) {
            return ret;
        }

        List<String> c = new LinkedList<>();
        dfs(ret, s, 0, set, min, max, c);
        return ret;
    }

    void dfs(List<String> ret, String s, int ps, Set<String> set, int min, int max, List<String> c) {
        if (ps == s.length()) {
            String sentence = String.join(" ", c);
            ret.add(sentence);
            return;
        }

        int start = ps + min;
        int end = Math.min(ps + max, s.length());
        for (int i = start; i <= end; i++) {
            String sub = s.substring(ps, i);
            if (set.contains(sub)) {
                c.add(sub);
                int len = sub.length();
                dfs(ret, s, ps+len, set, min, max, c);
                c.remove(c.size()-1);
            }
        }
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        // 可以类比于背包问题
        int n = s.length();
        // dp[i] 表示 s 中以 i - 1 结尾的字符串是否可被 wordDict 拆分
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    @Override
    void init() {
        parserList.add(new StringParser());
        parserList.add(new ListParser(new StringParser()));
        setOutputParser(new ListParser(new StringParser()));

        addInput("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                ,"[\"aa\",\"aaa\",\"aaaa\",\"aaaaa\",\"aaaaaa\",\"aaaaaaa\",\"aaaaaaaa\",\"aaaaaaaaa\",\"aaaaaaaaaa\",\"ba\"]");
        addOutput("[]");

        addInput("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                , "[\"a\",\"aa\",\"aaa\",\"aaaa\",\"aaaaa\",\"aaaaaa\",\"aaaaaaa\",\"aaaaaaaa\",\"aaaaaaaaa\",\"aaaaaaaaaa\"]");
        addOutput("[]");

        addInput("catsanddog", "[\"cat\", \"cats\", \"and\", \"sand\", \"dog\"]");
        addOutput("[\n" +
                "  \"cat sand dog\",\n" +
                "  \"cats and dog\"\n" +
                "]");

        addInput("pineapplepenapple", "[\"apple\", \"pen\", \"applepen\", \"pine\", \"pineapple\"]");
        addOutput("[\n" +
                "  \"pine apple pen apple\",\n" +
                "  \"pine applepen apple\",\n" +
                "  \"pineapple pen apple\"\n" +
                "]");

        addInput("catsandog", "[\"cats\", \"dog\", \"sand\", \"and\", \"cat\"]");
        addOutput("[]");

        addInput("a", "[]");
        addOutput("[]");
    }

    @Override
    Object execute(Object[] input) {
        return wordBreak((String) input[0], (List<String>) input[1]);
    }

    public static void main(String[] args) {
        new WordBreak2().runTest();
    }
}
