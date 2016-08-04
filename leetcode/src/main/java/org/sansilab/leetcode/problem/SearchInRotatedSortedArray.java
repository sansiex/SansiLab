package org.sansilab.leetcode.problem;

// https://leetcode.com/problems/search-in-rotated-sorted-array/
public final class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        SearchInRotatedSortedArray sln = new SearchInRotatedSortedArray();

//        int[] p1 = {0,1,2, 4,5,6,7};
//        int p2=2;
        //2

//        int[] p1 = {4,5,6,7, 0,1,2};
//        int p2=2;
        //6

        int[] p1 = {5, 1,3};
        int p2=4;
        //1
        long s = System.currentTimeMillis();
        Object output = sln.search(p1, p2);
        long e = System.currentTimeMillis();
        System.out.println(e - s + " ms");
        System.out.println(output.toString());
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            if (target == nums[0]) {
                return 0;
            } else {
                return -1;
            }
        }

        return rbs(nums, 0, nums.length-1, target);
    }

    private int rbs(int[] arr, int s, int e, int t){
        if (arr[s]<arr[e]) {
            return bs(arr, s, e, t);
        }
        int m = (s+e)/2;
        if (arr[m] == t) {
            return m;
        } else if (arr[m] < t) {
            if (m==e) {
                if (arr[e]<arr[s]) {
                    return rbs(arr, s, m-1, t);
                } else {
                    return -1;
                }
            }

            if (arr[m]<arr[e]) {
                if (arr[s]<=t) {
                    return rbs(arr, s, m-1, t);
                } else {
                    return bs(arr, m+1, e, t);
                }
            } else {
                return rbs(arr, m+1, e, t);
            }

        } else {
            if (m==s) {
                if (arr[e]<arr[s]) {
                    return rbs(arr, m+1, e, t);
                } else {
                    return -1;
                }
            }

            if (arr[m]>arr[s]) {
                if (arr[s]<=t) {
                    return bs(arr, s, m-1, t);
                } else {
                    return rbs(arr, m+1, e, t);
                }
            } else {
                return rbs(arr, s, m-1, t);
            }
        }
    }

    private int bs(int[] arr, int s, int e, int t){
        int m = (s+e)/2;
        if (arr[m] == t) {
            return m;
        } else if (arr[m] < t) {
            if (m==e) {
                return -1;
            }
            return bs(arr, m+1, e, t);
        } else {
            if (m==s) {
                return -1;
            }
            return bs(arr, s, m-1, t);
        }
    }
}

