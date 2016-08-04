package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.*;

// https://leetcode.com/problems/combination-sum/
public final class CombinationSum {
    public static void main(String[] args) {
        CombinationSum sln = new CombinationSum();
        int t = 7;
        int[] c = {2, 3, 6, 7};
        //3
        long s = System.currentTimeMillis();
        Object output = sln.combinationSum(c, t);
        long e = System.currentTimeMillis();
        System.out.println(e - s + " ms");
        System.out.println(JsonUtils.toJson(output));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        LinkedList<Integer> list = new LinkedList<>();
        Set<Integer> set = new HashSet<>(candidates.length*2);
        for (int v:candidates) {
            set.add(v);
        }
        int[] arr = new int[set.size()];
        int i=0;
        for (int v:set) {
            arr[i++]=v;
        }
        Arrays.sort(arr);
        return solve(arr, 0, target, new LinkedList<Integer>());
    }

    private List<List<Integer>> solve(int[] arr, int start, int target, LinkedList<Integer> list){
        if (start >= arr.length) {
            return null;
        }
        int v = arr[start];
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        LinkedList<Integer> tmp = (LinkedList<Integer>) list.clone();
        int cur = target;
        for (int i = 0; i <= target/v; i++) {
            if (cur == 0) {
                ret.add(tmp);
                return ret;
            }
            List<List<Integer>> tails = solve(arr, start+1, cur, (LinkedList<Integer>) tmp.clone());
            if (tails != null && !tails.isEmpty()) {
                ret.addAll(tails);
            }
            cur-=v;
            tmp.add(v);
        }
        return ret;
    }
}

