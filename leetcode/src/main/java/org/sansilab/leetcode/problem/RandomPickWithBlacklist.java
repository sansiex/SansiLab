package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.AbstractDataStructure;
import org.sansilab.leetcode.base.Operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author sansi
 * @date 2023-04-10
 * https://leetcode.cn/problems/random-pick-with-blacklist/
 * 更优解法，黑名单映射
 */
public class RandomPickWithBlacklist extends AbstractDataStructure {

    public static void main(String[] args) {
        RandomPickWithBlacklist.runTest(RandomPickWithBlacklist.class, () -> {
            addTestCase("[\"Solution\", \"pick\", \"pick\", \"pick\", \"pick\", \"pick\", \"pick\", \"pick\"]",
                    "[[7, [2, 3, 5]], [], [], [], [], [], [], []]",
                    "[null, 0, 4, 1, 6, 1, 0, 4]");
        });
    }

    List<int[]> invList;
    int total;

    @Operation("Solution")
    public RandomPickWithBlacklist(int n, int[] blacklist) {
        invList = new ArrayList<>(blacklist.length + 1);
        Arrays.sort(blacklist);
        int last = 0;
        int cnt = 0;
        for (int i : blacklist) {
            if (i > last) {
                invList.add(new int[]{last, i-1, cnt});
                cnt += i-last;
                last = i+1;
            } else {
                last++;
            }
        }
        if (blacklist.length > 0) {
            last = blacklist[blacklist.length - 1];
            if (last < n - 1) {
                invList.add(new int[]{last + 1, n - 1, cnt});
            }
        } else {
            invList.add(new int[]{0, n - 1, 0});
        }
        total = n - blacklist.length;
    }

    @Operation("pick")
    public int pick() {
        int t = (int) (Math.random() * total) + 1;
        return bs(0, invList.size()-1, t);
    }

    public int bs(int s, int e, int t) {
        int m = (s + e)/2;
        int[] inv = invList.get(m);
        if (t < inv[2] + 1) {
            return bs(s, m - 1, t);
        } else if (t > inv[2] + inv[1] - inv[0] + 1) {
            return bs(m + 1, e, t);
        } else {
            return inv[0] + t - inv[2] - 1;
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */
