package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.StringParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumWindowSubstring extends AbstractProblem {
    public String minWindow(String s, String t) {
        String ret = "";
        Map<Character, Integer> tm = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer n = tm.get(c);
            if (n == null) {
                tm.put(c, 1);
            } else {
                tm.put(c, n+1);
            }
        }
        if ( t.length() == 0 || s.length() < tm.size()) {
            return ret;
        }

        int start = 0;
        int min = Integer.MAX_VALUE;
        int clen = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (tm.containsKey(c)) {
                Integer n = map.get(c);
                if (n == null) {
                    map.put(c, 1);
                } else {
                    map.put(c, n+1);
                }

                int tn = tm.get(c);
                if (n == null || n<tn) {
                    clen++;
                }

                if (clen == t.length()) {
                    while (start <= i) {
                        char c2 = s.charAt(start);
                        if (!tm.containsKey(c2)) {
                            start ++;
                            continue;
                        } else {
                            if (clen<t.length() || start-1 == i) {
                                break;
                            } else {
                                Integer n2 = map.get(c2);
                                if (n2 > 1) {
                                    map.put(c2, n2-1);
                                } else {
                                    map.remove(c2);

                                }

                                tn = tm.get(c2);
                                if (n2 <= tn) {
                                    clen--;
                                    int len = i-start+1;
                                    if (min>len) {
                                        min = len;
                                        ret = s.substring(start, i+1);
                                        if (min == 1) {
                                            return ret;
                                        }
                                    }
                                }

                                start ++;
                            }
                        }
                    }
                }
            } else {
                if (map.isEmpty()) {
                    start ++;
                }
            }
        }

        return ret;
    }

    @Override
    void init() {
        parserList.add(new StringParser());
        parserList.add(new StringParser());

        addInput("ADOBECODEBANC", "ABC");
        outputList.add("BANC");

        addInput("ADOBECODEBANC", "AE");
        outputList.add("EBA");

        addInput("ADOBECODEBANC", "AABC");
        outputList.add("ADOBECODEBA");

        addInput("A", "A");
        outputList.add("A");

        addInput("A", "AA");
        outputList.add("");

        addInput("A", "B");
        outputList.add("");

        addInput("A", "AB");
        outputList.add("");
    }

    @Override
    Object execute(Object[] input) {
        return minWindow((String)input[0], (String) input[1]);
    }

    public static void main(String[] args) {
        new MinimumWindowSubstring().runTest();
    }
}
