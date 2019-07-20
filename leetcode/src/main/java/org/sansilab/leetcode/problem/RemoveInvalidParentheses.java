package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
public final class RemoveInvalidParentheses {
    public static void main(String[] args) {
        RemoveInvalidParentheses sln = new RemoveInvalidParentheses();
        List<Object[]> parr = new ArrayList<>();
        parr.add(new Object[]{
                "()())()"
        });
        parr.add(new Object[]{
                "(a)())()"
        });
        parr.add(new Object[]{
                ")("
        });
        for (Object[] arr : parr) {
            String p1 = (String) arr[0];

            long s = System.currentTimeMillis();
            Object output = sln.removeInvalidParentheses(p1);
            long e = System.currentTimeMillis();
            System.out.println(e - s + " ms");
            System.out.println(JsonUtils.toJson(output));
            System.out.println("=================================================");
        }
    }

    public List<String> removeInvalidParentheses(String s) {
        StringBuilder sb=new StringBuilder();
        int l=0;
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if (c == ')') {
                continue;
            } else if (c=='(') {
                l=i;
                break;
            } else {
                sb.append(c);
            }
        }

        int r=0;
        for (int i = s.length()-1; i > -1; i--) {
            char c=s.charAt(i);
            if (c == '(') {
                continue;
            } else if (c==')') {
                r=i;
                break;
            } else {
                sb.append(c);
            }
        }
        sb.append(s.substring(l, r+1));

        int lp=0;
        int ls=0;
        for (int i = 0; i < sb.length(); i++) {
            char c=sb.charAt(i);
            if (c=='(') {
                lp++;
            } else if (c==')') {
                lp--;
                if (lp<0) {
                    ls=i;
                }
            }
        }
        int rp=0;
        int rs=0;
        for (int i = sb.length()-1; i >= 0; i--) {
            char c=sb.charAt(i);
            if (c==')') {
                rp++;
            } else if (c=='(') {
                rp--;
                if (rp<0) {
                    rs=i;
                }
            }
        }

        return null;
    }
}
