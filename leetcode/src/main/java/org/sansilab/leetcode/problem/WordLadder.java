package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ListParser;
import org.sansilab.leetcode.parser.QuotedParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder extends AbstractProblem {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> all = new HashSet<>();
        Set<String> cs = new HashSet<>();
        Set<String> ns = new HashSet<>();
        all.addAll(wordList);
        if (!all.contains(endWord)) {
            return 0;
        };
        all.remove(endWord);
        cs.add(beginWord);

        int cnt = 1;
        while (!cs.isEmpty()) {
            for (String s:cs) {
                int cmp = compare(s, endWord);
                if (cmp == 1) {
                    return cnt+1;
                }
                for (String w:all) {
                    cmp = compare(s, w);
                    if (cmp == 1) {
                        ns.add(w);
                    }
                }
            }
            cnt++;
            all.removeAll(ns);
            cs = ns;
            ns = new HashSet<>();

        }
        return 0;
    }

    int compare(String a, String b){
        int dif = 0;
        int len = a.length();
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                dif++;
            }
            if (dif == 2) {
                return dif;
            }
        }

        return dif;
    }

    @Override
    void init() {
        parserList.add(new StringParser());
        parserList.add(new StringParser());
        parserList.add(new ListParser(new QuotedParser(new StringParser())));

        addInput("hit", "cog", "[\"hot\",\"dot\",\"dog\",\"lot\",\"log\"]");
        outputList.add(0);

        addInput("hit", "cog", "[\"hot\",\"dot\",\"dog\",\"lot\",\"log\",\"cog\"]");
        outputList.add(5);
    }

    @Override
    Object execute(Object[] input) {
        return ladderLength((String)input[0], (String)input[1], (List<String>) input[2]);
    }

    public static void main(String[] args) {
        new WordLadder().runTest();
    }
}
