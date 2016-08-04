package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.*;

// https://leetcode.com/problems/data-stream-as-disjoint-intervals/
public final class DataStreamAsDisjointIntervals {
    public static void main(String[] args) {
        DataStreamAsDisjointIntervals sln = new DataStreamAsDisjointIntervals();
        List<Object[]> parr = new ArrayList<>();
        parr.add(new Object[]{
                new int[]{1,3,7,2,6}
        });
//        parr.add(new Object[]{
//                new int[]{1,3,4,2,2}
//        });
        //3
        for (Object[] arr:parr) {
            int[] p1 = (int[]) arr[0];
            long s = System.currentTimeMillis();
            SummaryRanges instance = new SummaryRanges();
            for (int i = 0; i < p1.length; i++) {
                int p=p1[i];
                instance.addNum(p);
                Object output = instance.getIntervals();
                System.out.println(JsonUtils.toJson(output));
            }
            long e = System.currentTimeMillis();
            System.out.println(e - s + " ms");
            System.out.println("=================================================");
        }
    }
}

class SummaryRanges {

    private List<Interval> list;

    /** Initialize your data structure here. */
    public SummaryRanges() {
        list = new LinkedList<>();

    }

    public void addNum(int val) {
        Interval last = null;
        int i=0;
        for (Interval itv:list) {
            if (val<itv.start) {
                if (last == null) {
                    if (val == itv.start-1) {
                        itv.start=val;
                        return;
                    } else {
                        list.add(i, new Interval(val, val));
                        return;
                    }
                } else {
                    if (val <= last.end) {
                        return;
                    } else if (val == last.end+1) {
                        last.end=val;
                        if (itv.start == val+1) {
                            last.end = itv.end;
                            list.remove(i);
                            return;
                        }
                    } else if (itv.start == val+1){
                        itv.start = val;
                        return;
                    } else {
                        list.add(i, new Interval(val, val));
                        return;
                    }
                }
            }
            last = itv;
            i++;
        }
        if (last == null || val>last.end+1) {
            list.add(new Interval(val, val));
        } else {
            last.end = Math.max(val, last.end);
        }
    }

    public List<Interval> getIntervals() {
        return list;
    }
}

