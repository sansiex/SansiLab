package org.sansilab.leetcode.utils;

public class PalindromeUtils {

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

    public static int[] manacher(String str) {
        if (str.length()==0) {
            return null;
        }

        char[] s = insertDevider(str.toCharArray(), '#');
        int len = s.length;
        int id=0;
        int mx=0;
        int[] p = new int[len];
        for (int i = 0; i < len; i++) {
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
        }

        int l = id - p[id] + 1;
        int r = id + p[id] - 1;

        l = l/2;
        r = r/2 - 1;
        return new int[]{l, r};
    }

    public static void main(String[] args) {
        System.out.println(JsonUtils.toJson(PalindromeUtils.manacher("aaaabcaaaaaaaa")));
    }
}
