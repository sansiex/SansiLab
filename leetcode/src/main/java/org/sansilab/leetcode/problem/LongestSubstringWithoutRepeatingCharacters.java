package org.sansilab.leetcode.problem;

import com.google.common.collect.Lists;
import org.sansilab.leetcode.parser.ParamParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters extends AbstractProblem {


    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int[] ind = new int[s.length()];

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                ind[i] = map.get(c);
            } else {
                ind[i] = -1;
            }
            map.put(c, i);
        }

        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            int len = 0;
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);

                if (set.contains(c)) {
                    if (len > max) {
                        max = len;
                    }
                    i = ind[j];
                    break;
                } else {
                    len++;
                    set.add(c);
                }
            }
            if (i + len == s.length()) {
                if (len > max) {
                    max = len;
                }
                break;
            }
        }
        return max;
    }

    public int back(String s){
        HashMap<Character,Integer> map=new HashMap<>();
        int len=0;
        int max=0;
        int start=0;
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            Integer ci=map.get(c);
            len++;
            if(ci==null){
                map.put(c,i);
                if(len>max){
                    max=len;
                }
            }else{
                len-=ci-start+1;
                for (int j = start; j <= ci; j++) {
                    map.remove(s.charAt(j));
                }
                map.put(c,i);
                start=ci+1;
            }
        }
        return max;
    }

    @Override
    void init() {
        parserList.add(new StringParser());

        inputList.add(new String[]{"abcda"});
        outputList.add(4);
        inputList.add(new String[]{"abcdabcdefgh"});
        outputList.add(8);
        inputList.add(new String[]{"abcabcbb"});
        outputList.add(3);
        inputList.add(new String[]{"bbbbb"});
        outputList.add(1);
        inputList.add(new String[]{"pwwkew"});
        outputList.add(3);
        inputList.add(new String[]{""});
        outputList.add(0);
    }

    @Override
    Object execute(Object[] input) {
        return lengthOfLongestSubstring((String)input[0]);

    }

    public static void main(String[] args) {
        new LongestSubstringWithoutRepeatingCharacters().runTest();
    }
}
