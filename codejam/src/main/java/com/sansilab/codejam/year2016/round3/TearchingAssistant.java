package com.sansilab.codejam.year2016.round3;

import com.sansilab.codejam.SingleLineInputSolution;
import com.sansilab.codejam.Solution;
import java.io.*;

/**
 * Created by zuhai.jiang on 2016/7/7.
 * Round 3-A
 * http://code.google.com/codejam/contest/3224486/dashboard
 */
public final class TearchingAssistant extends SingleLineInputSolution {

    //0:test
    //1:small
    //2:large
    @Override
    protected int getMode() {
        return 0;
    }

    @Override
    protected String getName() {
        return "2016-round3-a";
    }

    @Override
    protected String solveSingleProblem(String line) {
        char[] chars = line.toCharArray();
        int ret = 0;
        boolean f=true;
        while(f){
            f=false;
            boolean act=false;
            for (int i = 0; i < chars.length;) {
                if (chars[i]==0) {
                    i++;
                    continue;
                } else {
                    f=true;
                    int j=i;
                    i++;
                    while(i<chars.length && chars[i]==0) {
                        i++;
                    }
                    if (i<chars.length && chars[i]==chars[j]) {
                        chars[i]=0;
                        chars[j]=0;
                        ret+=10;
                        act=true;
                    } else {
                        continue;
                    }
                }
            }
            if (!act) {
                break;
            }
        }
        int c=0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]!=0) {
                c++;
            }
        }
        ret+=5*(c/2);
        return String.valueOf(ret);
    }

    public static void main(String[] args) throws IOException {
        Solution sln = new TearchingAssistant();
        sln.execute();
    }
}
