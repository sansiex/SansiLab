package com.sansilab.codejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by zuhai.jiang on 2016/7/8.
 */
public abstract class SingleLineInputSolution extends Solution {

    @Override
    protected void solve(BufferedReader reader, BufferedWriter writer, int t) throws IOException {
        String line = null;
        for (int i = 1; i <= t; i++) {
            line = reader.readLine();
            String ret = solveSingleProblem(line);
            String res = "Case #"+i+": "+ret;
            System.out.println(res);
            writer.write(res+"\n");
        }
    }

    abstract protected String solveSingleProblem(String line);

    protected int[] getIntArray(String str){
        String[] arr=str.split(" ");
        int[] ret=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ret[i]=Integer.parseInt(arr[i]);
        }
        return ret;
    }
}
