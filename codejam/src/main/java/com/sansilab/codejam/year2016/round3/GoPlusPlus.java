package com.sansilab.codejam.year2016.round3;

import com.sansilab.codejam.MultipleLineInputSolution;
import com.sansilab.codejam.Solution;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by zuhai.jiang on 2016/7/8.
 * 2016-round3-b
 * http://code.google.com/codejam/contest/3224486/dashboard#s=p1
 * only small practice solved
 *
 * //TODO
 */
public final class GoPlusPlus extends MultipleLineInputSolution {
    @Override
    protected int getMode() {
        return 2;
    }

    @Override
    protected String getName() {
        return "2016-round3-d";
    }

    @Override
    protected String solveSingleProblem(BufferedReader reader) throws IOException {
        int[] nl=getIntArray(reader);
        String[] g=getStringArray(reader);
        String b=reader.readLine();
        for (String s:g){
            if (b.equals(s)) {
                return "IMPOSSIBLE";
            }
        }
        char[] p1=new char[b.length()*2];
        char[] p2=new char[b.length()*2-1];
        for (int i = 0; i < b.length(); i++) {
            char c=b.charAt(i);
            if (c=='0'){
                p1[i*2]='1';
                p2[i*2]='1';
            }else{
                p1[i*2]='0';
                p2[i*2]='0';
            }
            p1[i*2+1]='?';
            if (i!=b.length()-1) {
                p2[i*2+1]=c;
            }
        }
        return new String(p1)+" "+new String(p2);
    }

    public static void main(String[] args) throws IOException {
        Solution sln = new GoPlusPlus();
        sln.execute();
    }
}
