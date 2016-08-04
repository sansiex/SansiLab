package com.sansilab.codejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by zuhai.jiang on 2016/7/8.
 */
public abstract class MultipleLineInputSolution extends Solution {

    @Override
    protected void solve(BufferedReader reader, BufferedWriter writer, int t) throws IOException {
        String line = null;
        for (int i = 1; i <= t; i++) {
            String ret = solveSingleProblem(reader);
            String res = "Case #"+i+": "+ret;
            System.out.println(res);
            writer.write(res+"\n");
        }
    }

    abstract protected String solveSingleProblem(BufferedReader reader) throws IOException;
}
