package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

public class MedianOfTwoSortedArrays extends AbstractProblem {

    public double findKth(int k, int a[], int as, int ae, int b[], int bs, int be){
        int al=ae-as+1;
        int bl=be-bs+1;
        if(al>bl){
            return findKth(k,b,bs,be,a,as,ae);
        }

        if(al==0){
            return b[bs+k-1];
        }

        if(k==1){
            return Math.min(a[as],b[bs]);
        }

        int pa=Math.min(al,k/2);
        int pb=k-pa;
        if(a[as+pa-1]<b[bs+pb-1]){
            return findKth(k-pa,a,as+pa,ae,b,bs,be);
        }else if(a[as+pa-1]>b[bs+pb-1]){
            return findKth(k-pb,a,as,ae,b,bs+pb,be);
        }else{
            return a[as+pa-1];
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len=nums1.length+nums2.length;
        if(len%2==0){
            double m=findKth(len/2,nums1,0,nums1.length-1,nums2,0,nums2.length-1);
            double n=findKth(len/2+1,nums1,0,nums1.length-1,nums2,0,nums2.length-1);
            return (m+n)/2;
        }else{
            return findKth(len/2+1,nums1,0,nums1.length-1,nums2,0,nums2.length-1);
        }
    }

    @Override
    void init() {
        parserList.add(new ArrayParser(new IntParser()));
        parserList.add(new ArrayParser(new IntParser()));

        inputList.add(new String[]{"[1, 3]", "[2]"});
        outputList.add(2.0d);

        inputList.add(new String[]{"[1, 2]", "[3,4]"});
        outputList.add(2.5d);
    }

    @Override
    Object execute(Object[] input) {
        return findMedianSortedArrays((int[])input[0], (int[])input[1]);
    }

    public static void main(String[] args) {
        new MedianOfTwoSortedArrays().runTest();
    }
}
