package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/merge-intervals/
public final class TextJustification {
    public static void main(String[] args) {
        TextJustification sln = new TextJustification();

//        String[] p1 = {"This", "is", "an", "example", "of", "text", "justification."};
//        int p2=16;

        String[] p1 = {"What","must","be","shall","be."};
        int p2=12;
        //3
        long s = System.currentTimeMillis();
        Object output = sln.fullJustify(p1, p2);
        long e = System.currentTimeMillis();
        System.out.println(e - s + " ms");
        System.out.println(JsonUtils.toJson(output));
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ret = new ArrayList<>();
        if (maxWidth ==0){
            for (String w:words) {
                ret.add(w);
            }
            return ret;
        }
        int p = 0;
        while(p<words.length) {
            List<String> s = new ArrayList<>();
            String w = words[p++];
            s.add(w);
            int rm = maxWidth-w.length();
            if (p<words.length) {
                w = words[p];
                while (rm>w.length() && p<words.length) {
                    s.add(w);
                    p++;
                    rm-=(w.length()+1);
                    if (p>=words.length) {
                        break;
                    }
                    w = words[p];
                }
            }
            StringBuilder sb = new StringBuilder(maxWidth);
            sb.append(s.get(0));
            if (s.size()==1) {
                for (int i = 0; i < rm; i++) {
                    sb.append(' ');
                }
            } else {
                int ml = 1+rm/(s.size()-1);
                int rl = rm%(s.size()-1);
                if (p==words.length) {
                    rl=0;
                    ml=1;
                }
                for (int i = 1; i < s.size(); i++) {
                    w=s.get(i);
                    for (int j = 0; j < ml; j++) {
                        sb.append(' ');
                    }
                    if (i<=rl) {
                        sb.append(' ');
                    }
                    sb.append(w);
                }
                while (sb.length()<maxWidth) {
                    sb.append(' ');
                }
            }
            ret.add(sb.toString());
        }
        return ret;
    }
}

