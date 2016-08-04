package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.*;

// https://leetcode.com/problems/merge-intervals/
public final class MergeIntervals {
    public static void main(String[] args) {
        MergeIntervals sln = new MergeIntervals();

        List<Interval> c = new ArrayList<>();
        c.add(new Interval(1,3));
        c.add(new Interval(2,6));
        c.add(new Interval(8,10));
        c.add(new Interval(15,18));
        //3
        long s = System.currentTimeMillis();
        Object output = sln.merge(c);
        long e = System.currentTimeMillis();
        System.out.println(e - s + " ms");
        System.out.println(JsonUtils.toJson(output));
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ret = new ArrayList<>(intervals.size());
        if (intervals.size()==0) {
            return ret;
        }
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start-o2.start;
            }
        });
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval itv = intervals.get(i);
            if (last.end>=itv.start) {
                last.end = Math.max(last.end, itv.end);
            } else {
                ret.add(last);
                last = itv;
            }
        }
        ret.add(last);
        return ret;
    }
}

