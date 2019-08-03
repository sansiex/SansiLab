package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionListParser;

import java.util.*;

public class Permutations2 extends AbstractProblem {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        int cnt = 1;
        for (int i = 2; i <= nums.length; i++) {
            cnt *= i;
        }
        List<List<Integer>> ret = new ArrayList(cnt);
        recursive(nums, 0, ret);
        if (ret.isEmpty()) {
            return ret;
        }

        Comparator cmp = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                List<Integer> l1 = (List<Integer>) o1;
                List<Integer> l2 = (List<Integer>) o2;
                for (int i = 0; i < l1.size(); i++) {
                    if (l1.get(i).equals(l2.get(i))) {
                        continue;
                    }
                    return l1.get(i).compareTo(l2.get(i));
                }
                return 0;
            }
        };
        ret.sort(cmp);
        List uniq = new LinkedList();
        List<Integer> last = ret.get(0);
        uniq.add(last);
        for (int i = 1; i < ret.size(); i++) {
            List<Integer> l = ret.get(i);
            if (cmp.compare(last, l) == 0) {
                continue;
            }
            last = l;
            uniq.add(last);
        }

        return uniq;
    }

    public void recursive(int[] nums, int start, List ret){
        for (int i = start; i < nums.length; i++) {
            if (i == start) {
                if (start == nums.length - 1) {
                    List arr = new ArrayList(nums.length);
                    for (int j = 0; j < nums.length; j++) {
                        arr.add(nums[j]);
                    }
                    ret.add(arr);
                }
                recursive(nums, start+1, ret);
            } else {
                if (nums[i] == nums[start]) {
                    continue;
                }
                swap(nums, start, i);
                recursive(nums, start+1, ret);
                swap(nums, start, i);
            }
        }
    }

    public void swap(int[] arr, int a, int b){
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));
        setOutputParser(new TwoDimensionListParser(new IntParser()));


        addInput("[1,2,2,3,3,3]");
        addOutput("[[1,2,2,3,3,3],[1,2,3,2,3,3],[1,2,3,3,2,3],[1,2,3,3,3,2],[1,3,2,2,3,3],[1,3,2,3,2,3],[1,3,2,3,3,2],[1,3,3,2,2,3],[1,3,3,2,3,2],[1,3,3,3,2,2],[2,1,2,3,3,3],[2,1,3,2,3,3],[2,1,3,3,2,3],[2,1,3,3,3,2],[2,2,1,3,3,3],[2,2,3,1,3,3],[2,2,3,3,1,3],[2,2,3,3,3,1],[2,3,1,2,3,3],[2,3,1,3,2,3],[2,3,1,3,3,2],[2,3,2,1,3,3],[2,3,2,3,1,3],[2,3,2,3,3,1],[2,3,3,1,2,3],[2,3,3,1,3,2],[2,3,3,2,1,3],[2,3,3,2,3,1],[2,3,3,3,1,2],[2,3,3,3,2,1],[3,1,2,2,3,3],[3,1,2,3,2,3],[3,1,2,3,3,2],[3,1,3,2,2,3],[3,1,3,2,3,2],[3,1,3,3,2,2],[3,2,1,2,3,3],[3,2,1,3,2,3],[3,2,1,3,3,2],[3,2,2,1,3,3],[3,2,2,3,1,3],[3,2,2,3,3,1],[3,2,3,1,2,3],[3,2,3,1,3,2],[3,2,3,2,1,3],[3,2,3,2,3,1],[3,2,3,3,1,2],[3,2,3,3,2,1],[3,3,1,2,2,3],[3,3,1,2,3,2],[3,3,1,3,2,2],[3,3,2,1,2,3],[3,3,2,1,3,2],[3,3,2,2,1,3],[3,3,2,2,3,1],[3,3,2,3,1,2],[3,3,2,3,2,1],[3,3,3,1,2,2],[3,3,3,2,1,2],[3,3,3,2,2,1]]");


        addInput("[1,1,2,2]");
        addOutput("[[1,1,2,2],[1,2,1,2],[1,2,2,1],[2,1,1,2],[2,1,2,1],[2,2,1,1]]");

        addInput("[1,1,2]");
        addOutput("[\n" +
                "  [1,1,2],\n" +
                "  [1,2,1],\n" +
                "  [2,1,1]\n" +
                "]");

        addInput("[]");
        addOutput("[]");

        addInput("[1]");
        addOutput("[\n" +
                "  [1]\n" +
                "]");

        addInput("[1,2]");
        addOutput("[\n" +
                "  [1,2],\n" +
                "  [2,1]\n" +
                "]");

        addInput("[1,2,3]");
        addOutput("[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]");
    }

    @Override
    Object execute(Object[] input) {
        return permuteUnique((int[]) input[0]);
    }

    public static void main(String[] args) {
        new Permutations2().runTest();
    }
}
