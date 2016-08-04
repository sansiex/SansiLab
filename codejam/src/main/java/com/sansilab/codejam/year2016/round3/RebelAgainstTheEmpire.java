package com.sansilab.codejam.year2016.round3;

import com.google.common.collect.Sets;
import com.sansilab.codejam.MultipleLineInputSolution;
import com.sansilab.codejam.Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

/**
 * Created by zuhai.jiang on 2016/7/8.
 * 2016-round3-b
 * http://code.google.com/codejam/contest/3224486/dashboard#s=p1
 * only small practice solved
 *
 * //TODO
 */
public class RebelAgainstTheEmpire extends MultipleLineInputSolution {
    @Override
    protected int getMode() {
        return 1;
    }

    @Override
    protected String getName() {
        return "2016-round3-c";
    }

    @Override
    protected String solveSingleProblem(BufferedReader reader) throws IOException {
        int[] ns=getIntArray(reader);
        int[][] arr = new int[ns[0]][6];
        for (int i = 0; i < ns[0]; i++) {
            arr[i] = getIntArray(reader);
        }
        return solveStationary(ns[0], ns[1], arr);
    }

//    private String solveDynamic(int n, int s, int[][] stars){
//        double precision=1e-4;
//        double l=0;
//        double h=Math.sqrt(500*500*3);
//    }
//
//    private boolean feasible(double t){
//        return true;
//    }

    //only works when all asteroids are stationary!!!
    private String solveStationary(int n, int s, int[][] stars){
        Set<Integer> seta = Sets.newHashSet(0);
        Set<Integer> setb = Sets.newHashSet();
        for (int i = 1; i < n; i++) {
            setb.add(i);
        }
        double max=Double.MIN_VALUE;
        while(true){
            double min=Double.MAX_VALUE;
            int star=-1;
            for (int i:seta) {
                for (int j:setb){
                    double d = dist(stars[i],stars[j]);
                    if(d<min){
                        min=d;
                        star = j;
                    }
                }
            }
            if (min>max) {
                max=min;
            }
            if (star==1) {
                break;
            }
            seta.add(star);
            setb.remove(star);
        }
        return String.valueOf(max);

    }

    private double dist(int[] a, int[] b){
        int sum = (a[0]-b[0])*(a[0]-b[0])+(a[1]-b[1])*(a[1]-b[1])+(a[2]-b[2])*(a[2]-b[2]);
        return Math.sqrt(sum);
    }

    public static void main(String[] args) throws IOException {
        Solution sln = new RebelAgainstTheEmpire();
        sln.execute();
    }
}
