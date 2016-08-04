package com.sansilab.codejam.year2016.round2;

import com.sansilab.codejam.MultipleLineInputSolution;
import com.sansilab.codejam.SingleLineInputSolution;
import com.sansilab.codejam.Solution;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by zuhai.jiang on 2016/7/7.
 * Round 2-C
 * http://code.google.com/codejam/contest/10224486/dashboard#s=p2
 */
public class TheGardenerOfSeville extends MultipleLineInputSolution {

    //0:test
    //1:small
    //2:large
    @Override
    protected int getMode() {
        return 0;
    }

    @Override
    protected String getName() {
        return "2016-round2-c";
    }

    private final String IP="IMPOSSIBLE";

    private final int[] D={1,0};
    private final int[] U={-1,0};
    private final int[] L={0,-1};
    private final int[] R={0,1};

    @Override
    protected String solveSingleProblem(BufferedReader reader) throws IOException {
        int[] rc = getIntArray(reader);
        int[] pairs = getIntArray(reader);
        int r=rc[0];
        int c=rc[1];
        int l=pairs.length;

        char[][] g = new char[r][c];
        boolean[] done=new boolean[pairs.length];
        for (int i = 1; i <= pairs.length/2; i+=2) {
            boolean finish=true;
            for (int j = 0; j < pairs.length; j+=2) {
                if (!done[j]) {
                    finish = false;
                    int a = Math.min(pairs[j],pairs[j+1]);
                    int b = Math.max(pairs[j], pairs[j + 1]);
                    int distance = Math.min(b-a, a+l-b);
                    if (distance!=i){
                        continue;
                    } else {
                        if (b-a>c+r){
                            for (int k = b; k < l+a-1; k++) {
                                if(!done[k%l]){
                                    return IP;
                                }
                            }
                            int t=a;
                            a=b;
                            b=t;
                        } else if(b-a<c+r) {
                            for (int k = a; k < b-1; k++) {
                                if (!done[k]) {
                                    return IP;
                                }
                            }
                        }
                    }
                    done[a]=true;
                    done[b]=true;
                    if (!findWay(g, r, c, a, b)){
                        return IP;
                    }
                }
            }
            if (finish) {
                break;
            }
        }
        StringBuilder sb=new StringBuilder();
        for (char[] row:g){
            sb.append("\n");
            sb.append(row);
        }
        return sb.toString();
    }

    private boolean findWay(char[][] g, int r, int c, int a, int b){
        int sa = getSide(a, r, c);
        int sb = getSide(b, r, c);
        int[] pa=getPos(a, r, c);
        int[] pb=getPos(b, r, c);
        int dv = getDir(pa[0], pb[0]);
        int dh = getDir(pa[1], pb[1]);
        char base = dv*dh==-1||dh==0?'/':'\\';
        char mirror = getMirror(base);

        if (get(g, pa)==0 || get(g, pa)==base){
            set(g, pa, base);
        } else {
            return false;
        }

        char last=base;
        int[] cp=new int[]{pa[0],pa[1]};
        while (cp[0]!=pb[0] && cp[1]!=pb[1]) {
            int[] checkCell=new int[]{cp[0]+dv, cp[1]+dh};
            int[] left=getLeftDir(dv,dh);
            if (last!=base) {
                checkCell[0]+=left[0];
                checkCell[1]+=left[1];
            }
            char check=get(g, checkCell);
            if (check==0 || check==mirror) {
                set(g, checkCell, mirror);
            } else {
                checkCell[0]-=left[0];
                checkCell[1]-=left[1];
                if (out(r, c, checkCell)) {
                    return false;
                }
                check=get(g, checkCell);
                if (check==mirror) {
                    return false;
                }
                set(g, checkCell, base);
            }
            cp=checkCell;
        }
        return false;
    }

    private boolean out(int r, int c, int[] cell){
        if (cell[0]<0 || cell[1]<0) {
            return true;
        }
        if (cell[0]>=r || cell[1]>=c) {
            return true;
        }
        return false;
    }

    private char getMirror(char c){
        if (c=='/') {
            return '\\';
        }
        return '/';
    }

    private int getDir(int a, int b){
        if (a<b) {
            return 1;
        }
        if (a>b) {
            return -1;
        }
        return 0;
    }

    private int[] getLeftDir(int dv, int dh){
        if (dv==1){
            if (dh<1) {
                return R;
            } else {
                return U;
            }
        } else if(dv==-1) {
            if (dh>-1) {
                return L;
            } else {
                return D;
            }
        } else {
            if (dh==1) {
                return U;
            } else {
                return D;
            }
        }
    }

    private void set(char[][] g, int[] p, char c){
        g[p[0]][p[1]]=c;
    }

    private char get(char[][] g, int[] p){
        return g[p[0]][p[1]];
    }

    private int getSide(int n, int r, int c){
        if (n<=c){
            return 0;
        } else if (n<=r+c){
            return 1;
        } else if (n<=r+c+c){
            return 2;
        } else{
            return 3;
        }
    }

    private int[] getPos(int n, int r, int c){
        if (n<=c){
            return new int[]{0,n-1};
        } else if (n<=r+c){
            return new int[]{n-c-1, c-1};
        } else if (n<=r+c+c){
            return new int[]{r-1, c-(n-r-c)};
        } else{
            return new int[]{r-(n-r-c-c), 0};
        }
    }

    public static void main(String[] args) throws IOException {
        Solution sln = new TheGardenerOfSeville();
        sln.execute();
    }
}
