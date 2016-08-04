package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.*;

// https://leetcode.com/problems/combination-sum/
public final class CombinationSum2 {
    public static void main(String[] args) {
        CombinationSum2 sln = new CombinationSum2();
        int t = 8;
        int[] c = {10, 1, 2, 7, 6, 1, 5};
        //3
        long s = System.currentTimeMillis();
        Object output = sln.combinationSum2(c, t);
        long e = System.currentTimeMillis();
        System.out.println(e - s + " ms");
        System.out.println(JsonUtils.toJson(output));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        LinkedList<Integer> list = new LinkedList<>();
        Arrays.sort(candidates);
        List<int[]> arr = new ArrayList<>();
        int[] last = new int[]{candidates[0], 1};
        for (int i = 1; i < candidates.length; i++) {
            if (last[0]==candidates[i]) {
                last[1]++;
            } else {
                arr.add(last);
                last=new int[]{candidates[i], 1};
            }
        }
        arr.add(last);
        return solve(arr, 0, target, new LinkedList<Integer>());
    }

    private List<List<Integer>> solve(List<int[]> arr, int start, int target, LinkedList<Integer> list){
        if (start >= arr.size()) {
            return null;
        }
        int[] v = arr.get(start);
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        LinkedList<Integer> tmp = (LinkedList<Integer>) list.clone();
        int cur = target;
        for (int i = 0; i <= v[1] && cur>=0; i++) {
            if (cur == 0) {
                ret.add(tmp);
                return ret;
            }
            List<List<Integer>> tails = solve(arr, start+1, cur, (LinkedList<Integer>) tmp.clone());
            if (tails != null && !tails.isEmpty()) {
                ret.addAll(tails);
            }
            cur-=v[0];
            tmp.add(v[0]);
        }
        return ret;
    }
}

