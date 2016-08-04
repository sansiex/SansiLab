package com.sansilab.codejam.year2016.round2;

import com.sansilab.codejam.SingleLineInputSolution;
import com.sansilab.codejam.Solution;

import java.io.IOException;

/**
 * Created by zuhai.jiang on 2016/7/7.
 * Round 2-A
 * http://code.google.com/codejam/contest/10224486/dashboard#s=p0
 */
public final class RatherPerplexingShowdown extends SingleLineInputSolution {

    //0:test
    //1:small
    //2:large
    @Override
    protected int getMode() {
        return 2;
    }

    @Override
    protected String getName() {
        return "2016-round2-a";
    }

    private final String IP="IMPOSSIBLE";

    @Override
    protected String solveSingleProblem(String line) {
        int[] arr=getIntArray(line);
        int[] g={arr[1],arr[2],arr[3]};
        int n=arr[0];
        int sum=arr[1]+arr[2]+arr[3];

        boolean flag=false;
        int ei=-1;
        for (int i = 0; i < 3; i++) {
            if (n%2==1){
                if (g[i]==(sum-2)/3 && g[(i+1)%3]==g[(i+2)%3]) {
                    flag=true;
                    ei=i;
                    break;
                }
            } else {
                if (g[i]==(sum+2)/3 && g[(i+1)%3]==g[(i+2)%3]) {
                    flag=true;
                    ei=i;
                    break;
                }
            }
        }
        if (!flag) {
            return IP;
        }

        int win=ei;
        for (int i = 0; i < n; i++) {
            win--;
            if (win<0) {
                win=2;
            }
        }
        return getLineup(win, n);
    }

    private int count(String s, char c){
        int r=0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==c){
                r++;
            }
        }
        return r;
    }

    private String getLineup(int win, int n){
        char[] a={'R','P','S'};
        char w=a[win];
        int width = (int) Math.pow(2,n);
        char[] lineup=new char[width];
        char[] next=new char[width];
        lineup[0]=w;
        for (int i = 0; i < n; i++) {
            int size= (int) Math.pow(2, i);
            for (int j = 0; j < size; j++) {
                char c=lineup[j];
                if (c=='R') {
                    next[j*2]='R';
                    next[j*2+1]='S';
                }else if (c=='P') {
                    next[j*2]='P';
                    next[j*2+1]='R';
                }else if (c=='S') {
                    next[j*2]='P';
                    next[j*2+1]='S';
                }
            }
            char[] tmp=lineup;
            lineup=next;
            next=tmp;
        }
        for (int i = 1; i <= n ; i++) {
            int step = (int) Math.pow(2, i);
            int sn = width/step;
            for (int j = 0; j < sn; j++) {
                int s1=j*step;
                int s2=j*step+step/2;
                for (int k = 0; k < step/2; k++) {
                    if (lineup[s1+k]<lineup[s2+k]){
                        break;
                    }else if (lineup[s1+k]>lineup[s2+k]) {
                        swap(lineup, s1, s2, step/2);
                        break;
                    }
                }
            }
        }

        return new String(lineup);
    }

    private void swap(char[] arr, int a, int b, int l){
        for (int i = 0; i < l; i++) {
            char t=arr[a+i];;
            arr[a+i]=arr[b+i];
            arr[b+i]=t;
        }
    }

    public static void main(String[] args) throws IOException {
        Solution sln = new RatherPerplexingShowdown();
        sln.execute();
    }
}
