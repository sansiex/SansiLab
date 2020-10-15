package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;
import org.sansilab.leetcode.parser.TwoDimensionListParser;

import java.util.*;

public class TheSkylineProblem extends AbstractProblem {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        Point[] pts = new Point[buildings.length*2];
        for (int i = 0; i < buildings.length; i++) {
            int[] b = buildings[i];
            pts[i*2] = new Point(b[0], b[2], true);
            pts[i*2+1] = new Point(b[1], b[2], false);
        }

        Arrays.sort(pts, (p1, p2) -> {
            if(p1.x==p2.x){
                if (p1.y == p2.y) {
                    if (p2.start) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return p2.y - p1.y;
                }
            } else {
                return p1.x - p2.x;
            }
        });
        PriorityQueue<Integer> stack = new PriorityQueue<>((p1, p2) -> p2 - p1);
        List<List<Integer>> res = new LinkedList<>();
        int ch = 0;
        for (int i = 0; i < pts.length; i++) {
            Point p = pts[i];
            if (p.start) {
                stack.offer(p.y);
                if (p.y>ch) {
                    ch = p.y;
                    ArrayList<Integer> k = new ArrayList<>();
                    k.add(p.x);
                    k.add(p.y);
                    res.add(k);
                }
            } else {
                stack.remove(p.y);
                if (stack.isEmpty()) {
                    ch = 0;

                    List<Integer> last = res.get(res.size()-1);
                    if (last.get(0) == p.x) {
                        last.set(1, 0);
                    } else {
                        ArrayList<Integer> k = new ArrayList<>();
                        k.add(p.x);
                        k.add(0);
                        res.add(k);
                    }
                    continue;
                }
                int head = stack.peek();
                if (head < ch) {
                    ch = head;
                    ArrayList<Integer> k = new ArrayList<>();
                    k.add(p.x);
                    k.add(head);

                    List<Integer> last = res.get(res.size()-1);
                    if (last.get(0) == p.x) {
                        last.set(1, Math.min(last.get(1), head));
                    } else {
                        res.add(k);
                    }
                }
            }
        }

        return res;
    }

    class Point{
        int x;
        int y;
        boolean start;

        public Point(int x, int y, boolean start) {
            this.x = x;
            this.y = y;
            this.start = start;
        }
    }

    @Override
    void init() {
        parserList.add(new TwoDimensionArrayParser(new IntParser()));
        setOutputParser(new TwoDimensionListParser(new IntParser()));

        addInput("[ [2,9,10], [3,7,15], [5,12,12], [15,20,10], [19,24,8] ]");
        addOutput("[ [2,10], [3, 15], [7, 12], [12, 0], [15, 10], [20, 8], [24, 0] ]");

        addInput("[[0,2,3],[2,5,3]]");
        addOutput("[[0,3],[5,0]]");

        addInput("[[1,2,1],[1,2,2],[1,2,3]]");
        addOutput("[[1,3],[2,0]]");
    }

    @Override
    Object execute(Object[] input) {
        return getSkyline((int[][]) input[0]);
    }

    public static void main(String[] args) {
        new TheSkylineProblem().runTest();
    }
}
