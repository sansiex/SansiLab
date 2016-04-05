package org.sansilab.leetcode.problem;

import com.google.common.collect.Lists;
import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;


//https://leetcode.com/problems/counting-bits/
public class CountingBits {
	public static void main(String[] args){
		CountingBits sln=new CountingBits();
		List<Integer> list = Lists.newArrayList(
				0
				,5
				,100
		);
		for (int input:list) {
			long s=System.currentTimeMillis();
			Object output=sln.countBits(input);
			long e=System.currentTimeMillis();
			System.out.println(e-s+" ms");
			System.out.println(JsonUtils.toJson(output));
		}
	}

	public int[] countBits(int num) {
		int[] ret = new int[num+1];
		ret[0]=0;
		for (int i=1;i<=num;i++){
			if (i%2 == 1) {
				int c = ret[i-1];
				ret[i]=c+1;
			} else {
				int c = ret[i/2];
				ret[i]=c;
			}
		}
		return ret;
	}
}

