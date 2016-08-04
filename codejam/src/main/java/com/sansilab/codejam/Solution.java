package com.sansilab.codejam;

import java.io.*;

/**
 * Created by zuhai.jiang on 2016/7/8.
 */
public abstract class Solution {


    public void execute() throws IOException {
        FileReader fr = new FileReader(getInputFile());
        BufferedReader reader = new BufferedReader(fr);

        FileWriter fw = new FileWriter(getOutputFile());
        BufferedWriter writer = new BufferedWriter(fw);

        String line = reader.readLine();
        int t = Integer.parseInt(line);
        long start = System.currentTimeMillis();
        solve(reader, writer, t);
        long end = System.currentTimeMillis();
        writer.flush();
        writer.close();
        fw.close();
        System.out.println("took "+(end-start)+" ms");

        reader.close();
        fr.close();
    }

    abstract protected int getMode();

    abstract protected String getName();

    abstract protected void solve(BufferedReader reader, BufferedWriter writer, int t) throws IOException ;

    private String getDir(){
        String ANCHOR =this.getClass().getClassLoader().getResource("input/anchor.txt").getPath();
        String DIR = ANCHOR.substring(0, ANCHOR.length()-10)+getName()+"/";
        return DIR;
    }

    private File getInputFile(){
        String DIR = getDir();
        File T_INPUT = new File(DIR+"test.in");
        File S_INPUT = new File(DIR+"small-practice.in");
        File L_INPUT = new File(DIR+"large-practice.in");
        int mode=getMode();
        if (mode == 0) {
            return T_INPUT;
        } else if (mode == 1){
            return S_INPUT;
        } else {
            return L_INPUT;
        }
    }

    private File getOutputFile(){
        String DIR = getDir();
        File T = new File(DIR+"test.out");
        File S = new File(DIR+"small-practice.out");
        File L = new File(DIR+"large-practice.out");
        int mode=getMode();
        if (mode == 0) {
            return T;
        } else if (mode == 1){
            return S;
        } else {
            return L;
        }
    }

    protected String[] getStringArray(BufferedReader reader) throws IOException {
        return reader.readLine().split(" ");
    }

    protected int[] getIntArray(BufferedReader reader) throws IOException {
        String[] arr = getStringArray(reader);
        int[] ret = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ret[i] = Integer.parseInt(arr[i]);
        }
        return ret;
    }

    protected double[] getDoubleArray(BufferedReader reader) throws IOException {
        String[] arr = getStringArray(reader);
        double[] ret = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ret[i] = Double.parseDouble(arr[i]);
        }
        return ret;
    }

    protected int getInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
