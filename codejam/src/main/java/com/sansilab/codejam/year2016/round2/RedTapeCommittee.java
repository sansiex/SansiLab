package com.sansilab.codejam.year2016.round2;

import com.sansilab.codejam.MultipleLineInputSolution;
import com.sansilab.codejam.Solution;
import com.sansilab.codejam.util.CollectionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by zuhai.jiang on 2016/7/8.
 * 2016-round2-b
 * http://code.google.com/codejam/contest/10224486/dashboard#s=p1
 *
 * //TODO
 */
public final class RedTapeCommittee extends MultipleLineInputSolution {

    @Override
    protected int getMode() {
        return 2;
    }

    @Override
    protected String getName() {
        return "2016-round2-b";
    }

    @Override
    protected String solveSingleProblem(BufferedReader reader) throws IOException {
        int[] nk=getIntArray(reader);
        int n=nk[0];
        int k=nk[1];
        double[] parr=getDoubleArray(reader);
        Arrays.sort(parr);
//        double res=find(0, parr, k, 0, 0, new double[k]);

        double[] c = new double[k];
        double res = 0;
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < i; j++) {
                c[j]=parr[j];
            }
            for (int j = i; j < k; j++) {
                c[j]=parr[n-k+j];
            }
            double p = calp(c, k);
            res = Math.max(res, p);
        }


        return String.valueOf(res);
    }

    private double calp(double[] n, int k){
        double [][] dp = new double[k/2+1][k+1];
        dp[0][0]=1;
        for (int i = 1; i < k+1; i++) {
            dp[0][i]=dp[0][i-1]*(1-n[i-1]);
        }
        for (int i = 1; i < k/2+1; i++) {
            dp[i][i]=dp[i-1][i-1]*n[i-1];
        }
        for (int i = 1; i < k/2+1; i++) {
            for (int j = i+1; j < k+1; j++) {
                dp[i][j] = dp[i][j-1]*(1-n[j-1])+dp[i-1][j-1]*n[j-1];
            }
        }
        return dp[k/2][k];
    }

    public static void main(String[] args) throws IOException {
        Solution sln = new RedTapeCommittee();
        sln.execute();
    }
}
