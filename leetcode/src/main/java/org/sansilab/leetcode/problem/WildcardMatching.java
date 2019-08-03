package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.StringParser;

public class WildcardMatching extends AbstractProblem {

    public boolean isMatch(String s, String p) {
        StringBuilder sb = new StringBuilder();
        boolean ls = false;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '*') {
                if (ls) {
                    continue;
                } else {
                    ls = true;
                    sb.append(c);
                }
            } else {
                ls = false;
                sb.append(c);
            }
        }
        String mp = sb.toString();
        boolean[][] cache = new boolean[s.length()+1][mp.length()+1];
        return isMatch(s, 0, mp, 0, cache);
    }

    public boolean isMatch(String s, int ss, String p, int ps, boolean[][] cache) {
        if (cache[ss][ps]) {
            return false;
        }
        int sp = ss;
        int pp = ps;

        while (true) {
            if (sp == s.length()) {
                if (pp == p.length()) {
                    return true;
                } else {
                    char pc = p.charAt(pp);
                    if (pc == '*') {
                        pp++;
                        continue;
                    }
                    cache[ss][ps]=true;
                    return false;
                }
            } else {
                if (pp == p.length()) {
                    cache[ss][ps]=true;
                    return false;
                }
            }

            char sc = s.charAt(sp);
            char pc = p.charAt(pp);
            if (pc == '?') {
                sp++;
                pp++;
            } else if (pc == '*') {
                if (pp == p.length() - 1) {
                    return true;
                }
                int cnt = 0;
                for (int i = pp+1; i < p.length(); i++) {
                    char cc = p.charAt(i);
                    if (cc != '*') {
                        cnt ++;
                    }
                }

                for (int i = sp; i < s.length()-cnt+1; i++) {
                    if (isMatch(s, i, p, pp+1,cache)) {
                        return true;
                    }
                }
                cache[ss][ps]=true;
                return false;
            } else {
                if (sc == pc) {
                    sp++;
                    pp++;
                } else {
                    cache[ss][ps]=true;
                    return false;
                }
            }
        }
    }

    @Override
    void init() {
        parserList.add(new StringParser());
        parserList.add(new StringParser());

        addInput("aa", "a");
        outputList.add(false);
        addInput("aa", "*");
        outputList.add(true);
        addInput("", "*");
        outputList.add(true);
        addInput("", "?");
        outputList.add(false);
        addInput("a", "");
        outputList.add(false);
        addInput("", "");
        outputList.add(true);
        addInput("cb", "?a");
        outputList.add(false);
        addInput("adceb", "*a*b");
        outputList.add(true);
        addInput("acdcb", "a*c?b");
        outputList.add(false);
        addInput("acdcb", "a*c??b");
        outputList.add(true);
        addInput("awretreetwertresfgsdfgerwtcdcb", "a*c??b");
        outputList.add(true);
        addInput("awretreetwertresfgsdfgerwtcdcb", "a*c*b");
        outputList.add(true);
        addInput("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba","a*******b");
        outputList.add(false);
        addInput("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba","a*******b*******a*******b********b**a");
        outputList.add(true);
        addInput("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb"
                ,"**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb");
        outputList.add(false);
    }

    @Override
    Object execute(Object[] input) {
        return isMatch((String) input[0], (String) input[1]);
    }

    public static void main(String[] args) {
        new WildcardMatching().runTest();
    }
}
