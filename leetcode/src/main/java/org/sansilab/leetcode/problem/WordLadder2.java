package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ListParser;
import org.sansilab.leetcode.parser.QuotedParser;
import org.sansilab.leetcode.parser.StringParser;
import org.sansilab.leetcode.parser.TwoDimensionListParser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class WordLadder2 extends AbstractProblem {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        return null;
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
        setOutputParser(new TwoDimensionListParser(new StringParser()));

        addInput("hit", "cog", "[\"hot\",\"dot\",\"dog\",\"lot\",\"log\",\"cog\"]");
        addOutput("[\n" +
                "  [\"hit\",\"hot\",\"dot\",\"dog\",\"cog\"],\n" +
                "  [\"hit\",\"hot\",\"lot\",\"log\",\"cog\"]\n" +
                "]");

        addInput("hit", "cog", "[\"hot\",\"dot\",\"dog\",\"lot\",\"log\"]");
        addOutput("[]");
    }

    @Override
    Object execute(Object[] input) {
        return findLadders((String)input[0], (String)input[1], (List<String>) input[2]);
    }

    public static void main(String[] args) {
//        new WordLadder2().runTest();
    }
}
