package com.sansilab.codejam.year2016.round3;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.sansilab.codejam.MultipleLineInputSolution;
import com.sansilab.codejam.Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by zuhai.jiang on 2016/7/8.
 * 2016-round3-b
 * http://code.google.com/codejam/contest/3224486/dashboard#s=p1
 */
public final class ForestUniversity extends MultipleLineInputSolution {
    @Override
    protected int getMode() {
        return 1;
    }

    @Override
    protected String getName() {
        return "2016-round3-b";
    }

    @Override
    protected String solveSingleProblem(BufferedReader reader) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        String[] arr=reader.readLine().split(" ");
        int[] nop = new int[n];
        for (int i = 0; i < n; i++) {
            nop[i]=Integer.parseInt(arr[i]);
        }
        String name = reader.readLine();
        int m = Integer.parseInt(reader.readLine());
        String[] words = reader.readLine().split(" ");

        Node root = new Node('0');
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            Node node = new Node(name.charAt(i));
            nodes[i]=node;
        }
        for (int i = 0; i < n; i++) {
            int p = nop[i];
            if (p==0) {
                root.children.add(nodes[i]);
            } else {
                nodes[p-1].children.add(nodes[i]);
                int cur=i;
                while(nop[cur]!=0){
                    int poc=nop[cur];
                    nodes[poc-1].dcnt+=1;
                    cur=poc-1;
                }
            }
        }

        double[] pos = new double[m];
        int all = 50000;
        for (int i = 0; i < all; i++) {
            String sample = sample(root, n);
            for (int j = 0; j < m; j++) {
                if (sample.contains(words[j])) {
                    pos[j]+=1;
                }
            }
        }

        String[] res = new String[m];
        for (int i = 0; i < m; i++) {
            res[i] = String.valueOf(pos[i]/all);
        }
        return Joiner.on(" ").join(res);
    }

    private String sample(Node root, int n){
        char[] str = new char[n];
        List<Node> candidates = Lists.newArrayListWithCapacity(n);
        int ci = 0;
        candidates.addAll(root.children);
        Random rand=new Random();
        while(!candidates.isEmpty()) {
            int all=0;
            for (Node node:candidates) {
                all+=node.dcnt;
            }
            int r = Math.abs(rand.nextInt())%all;
            int index=0;
            for (int i = 0; i < candidates.size(); i++) {
                index+=candidates.get(i).dcnt;
                if (index>r) {
                    index=i;
                    break;
                }
            }
            Node next = candidates.remove(index);
            candidates.addAll(next.children);
            str[ci]=next.c;
            ci++;
        }
        return new String(str);
    }

    private class Node {
        public char c;
        public int dcnt=1;
        public List<Node> children = Lists.newArrayList();
        public Node(char c){
            this.c=c;
        }
    }

    public static void main(String[] args) throws IOException {
        Solution sln = new ForestUniversity();
        sln.execute();
    }
}
