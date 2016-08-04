package com.sansilab.codejam.year2016.round2;

import com.sansilab.codejam.MultipleLineInputSolution;
import com.sansilab.codejam.Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by zuhai.jiang on 2016/7/7.
 * 2016-Round 2-A
 * http://code.google.com/codejam/contest/10224486/dashboard#s=p3
 */
public class FreeformFactory extends MultipleLineInputSolution {

    //0:test
    //1:small
    //2:large
    @Override
    protected int getMode() {
        return 0;
    }

    @Override
    protected String getName() {
        return "2016-round2-d";
    }

    @Override
    protected String solveSingleProblem(BufferedReader reader) throws IOException {
        int n = getInt(reader);
        char[][] s = new char[n][n];
        for (int i = 0; i < n; i++) {
            s[i] = reader.readLine().toCharArray();
        }
        int cnt=0;
        Set<Integer> cc = new HashSet<>(n*2);
        Set<Integer> rc = new HashSet<>(n*2);
        List<int[]> pairs = new ArrayList<>();

        for (int i = 0; i <n; i++) {
            for (int j = 0; j < n; j++) {
                if (rc.contains(i) || cc.contains(j)) {
                    continue;
                }
                char c=s[i][j];
                if (c=='1') {
                    int rsize=rc.size();
                    int csize=cc.size();
                    rc.add(i);
                    cc.add(j);
                    int inc = complete(s, cc, rc);
                    cnt+=inc;
                    int h = rc.size()-rsize;
                    int w = cc.size()-csize;
                    if (h!=w) {
                        pairs.add(new int[]{w,h});
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!rc.contains(i) && !cc.contains(j)) {
                    rc.add(i);
                    cc.add(j);
                    s[i][j]='1';
                    cnt++;
                }
            }
        }

        if (pairs.isEmpty()) {
            return String.valueOf(cnt);
        }

        int inc = match(pairs);
        return String.valueOf(cnt+inc);
    }

    private int match(List<int[]> pairs){
        Set<Integer> wset = new HashSet<>();
        Set<Integer> hset = new HashSet<>();
        for (int i=0;i<pairs.size();i++) {
            int[] p=pairs.get(i);
            if (p[1]>p[0]) {
                wset.add(i);
            } else {
                hset.add(i);
            }
        }
        return match(pairs, wset, hset);
    }

    private int match(List<int[]> pairs, Set<Integer> wl, Set<Integer> hl) {
        return -1;
    }

//    private int cal(List<int[]> pairs, Set<Integer> wl, Set<Integer> hl){
//
//    }

    private int complete(char[][] s, Set<Integer> cc, Set<Integer> rc){
        int cnt=0;
        int n=s.length;
        boolean f = false;
        for (int c:cc) {
            for (int i = 0; i < n; i++) {
                if (rc.contains(i)) {
                    continue;
                }

                char ch = s[i][c];
                if (ch == '1') {
                    f=true;
                    rc.add(i);
                }
            }
        }

        for (int r:rc) {
            for (int i = 0; i < n; i++) {
                if (cc.contains(i)) {
                    continue;
                }

                char ch = s[r][i];
                if (ch == '1') {
                    f=true;
                    cc.add(i);
                }
            }
        }

        for (int r:rc) {
            for (int c:cc){
                if (s[r][c]=='0') {
                    cnt++;
                    s[r][c]='1';
                }
            }
        }

        if (!f) {
            return cnt;
        } else {
            return cnt + complete(s, cc, rc);
        }
    }

    public static void main(String[] args) throws IOException {
        Solution sln = new FreeformFactory();
        sln.execute();
    }
}
