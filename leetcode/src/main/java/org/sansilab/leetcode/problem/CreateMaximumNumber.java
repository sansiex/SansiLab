package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

// https://leetcode-cn.com/problems/basic-calculator/
public class CreateMaximumNumber extends AbstractProblem{
	public static void main(String[] args) {
		new CreateMaximumNumber().runTest();
	}

	@Answer
	public int[] maxNumber(int[] nums1, int[] nums2, int k){
		int[] ret = null;
		Comparator<int[]> c = new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1==null && o2==null){
					return 0;
				}
				if (o1==null) {
					return -1;
				}
				if (o2==null) {
					return 1;
				}
				for (int i = 0; i < k; i++) {
					int cmp = Integer.compare(o1[i], o2[i]);
					if(cmp!=0){
						return cmp;
					}
					continue;
				}
				return 0;
			}
		};
		for (int i = 0; i <= k; i++) {
			int[] arr = find(nums1, i, nums2, k-i);
			int cmp = c.compare(ret, arr);
			if (cmp<0) {
				ret = arr;
			}
		}
		return ret;
	}

	public int[] find(int[] nums1, int k1, int[] nums2, int k2) {
		if(k1>nums1.length || k2>nums2.length){
			return null;
		}
		int[] arr1 = find(nums1, k1);
		int[] arr2 = find(nums2, k2);
		int k=k1+k2;
		int[] ret = new int[k];
		int p1=0;
		int p2=0;
		int i=0;
		while (p1<k1 || p2<k2) {
			if (p1==k1) {
				ret[i++] = arr2[p2++];
				continue;
			}
			if (p2==k2) {
				ret[i++] = arr1[p1++];
				continue;
			}

			if (arr1[p1] > arr2[p2]) {
				ret[i++] = arr1[p1++];
			}else if(arr1[p1] == arr2[p2]){
				int c1=p1;
				int c2=p2;
				while(c1<arr1.length && c2<arr2.length && arr1[c1] == arr2[c2]){
					c1++;
					c2++;
				}
				if (c2==arr2.length) {
					ret[i++] = arr1[p1++];
				} else {
					if (c1==arr1.length || arr2[c2]>arr1[c1]) {
						ret[i++] = arr2[p2++];
					} else {
						ret[i++] = arr1[p1++];
					}
				}
			}else{
				ret[i++] = arr2[p2++];
			}
		}
		return ret;
	}

	public int[] find(int[] nums, int k){
		int[] ret = new int[k];
		Arrays.fill(ret, Integer.MIN_VALUE);
		int s=0;
		for (int i = 0; i < k; i++) {
			for (int j = s; j <= nums.length-k+i; j++) {
				int v=nums[j];
				if (v>ret[i]){
					ret[i] = v;
					s=j+1;
				}
			}
		}
		return ret;
	}


	@Override
	void init() {
		addInputParsers(
				new ArrayParser(new IntParser())
				,new ArrayParser(new IntParser())
				,new IntParser()
		);
		setOutputParser(new ArrayParser(new IntParser()));

		addInput("[2,5,6,4,4,0]","[7,3,8,0,6,5,7,6,2]", "15");
		addOutput("[7,3,8,2,5,6,4,4,0,6,5,7,6,2,0]");

		addInput("[6, 7]", "[6, 0, 4]", "5");
		addOutput("[6, 7, 6, 0, 4]");

		addInput("[3, 4, 6, 5]", "[9, 1, 2, 5, 8, 3]", "5");
		addOutput("[9, 8, 6, 5, 3]");

		addInput("[3, 9]", "[8, 9]", "3");
		addOutput("[9, 8, 9]");
	}
}

