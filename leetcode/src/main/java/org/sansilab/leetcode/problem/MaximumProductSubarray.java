package org.sansilab.leetcode.problem;

import com.google.common.collect.Lists;
import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;


//https://leetcode.com/problems/maximum-product-subarray/
public class MaximumProductSubarray {
	public static void main(String[] args){
		MaximumProductSubarray sln=new MaximumProductSubarray();
		List<int[]> list = Lists.newArrayList(
				new int[]{-3,-1,0,0,1,-2}
				,new int[]{-2}
				,new int[]{-4,-3,-2}
		);
		for (int[] input:list) {
			long s=System.currentTimeMillis();
			Object output=sln.maxProduct(input);
			long e=System.currentTimeMillis();
			System.out.println(e-s+" ms");
			System.out.println(JsonUtils.toJson(output));
		}
	}

	public int maxProduct(int[] nums) {
		int mp = Integer.MIN_VALUE;
		int s=-1;
		for (int i = 0;i<nums.length;i++) {
			int v = nums[i];
			if (v == 0) {
				mp = Math.max(0, mp);
				if (i==s+1) {
					s++;
					continue;
				} else if (i>s+1) {
					mp = Math.max(sub(nums, s+1, i-1), mp);
					s=i;
				}
			}
		}
		if (s < nums.length-1) {
			mp = Math.max(sub(nums, s+1, nums.length-1), mp);
		}
		return mp;
	}

	private int sub(int[] nums, int s, int e){
		if (s==e) {
			return nums[s];
		}
		int p = 1;
		int nc = 0;
		int firstNV = s;
		int lastNV = s;
		for (int i=s;i<=e;i++) {
			int v = nums[i];
			p*=v;
			if (v<0) {
				if (nc==0) {
					firstNV = i;
				}
				nc++;
				lastNV = i;
			}
		}
		if (nc%2 == 0) {
			return p;
		} else {
			int a=time(nums, s, Math.min(lastNV-1, e));
			int b=time(nums, Math.max(s, firstNV+1), e);
			return Math.max(a,b);
		}
	}

	private int time(int[] nums, int s, int e){

		int p =1;
		for(;s<=e;s++){
			p*=nums[s];
		}
		return p;
	}
}

