package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode-cn.com/problems/basic-calculator/
public class CountOfRangeSum extends AbstractProblem{
	public static void main(String[] args) {
		new CountOfRangeSum().runTest();
	}
	@Answer
	public int countRangeSum(int[] nums, int lower, int upper) {
		if (nums.length==0) {
			return 0;
		}
		long[] sums=new long[nums.length+1];
		long sum=0;
		for (int i = 0; i < nums.length; i++) {
			sum+=nums[i];
			sums[i+1]=sum;
		}

		int ret = recurse(sums, lower, upper, 0, sums.length-1);
		return ret;
	}

	public int recurse(long[] sums, int lower, int upper, int s, int e){
		if (s==e) {
			return 0;
		}

		int m=(s+e)/2;
		int ret=0;
		int lret=recurse(sums, lower, upper, s, m);
		int rret=recurse(sums, lower, upper, m+1, e);
		ret+=lret+rret;

		int pl=m+1;
		int pr=m+1;
		for (int i = s; i <= m ; i++) {
			long lval=sums[i];
			while(pl<=e && sums[pl] - lval<lower){
				pl++;
			}
			while(pr<=e && sums[pr] - lval<=upper){
				pr++;
			}
			int cnt = pr-pl;
			ret += cnt;
		}

		Arrays.sort(sums, s, e+1);
		return ret;
	}


	@Override
	void init() {
		addInputParsers(
				new ArrayParser(new IntParser())
				,new IntParser()
				,new IntParser()
		);
		setOutputParser(new IntParser());

		addInput("[2147483647,-2147483648,-1,0]","-1","0");
		addOutput("4");

		addInput("[]","0", "0");
		addOutput("0");

		addInput("[-2,5,-1]","-2", "2");
		addOutput("3");
	}
}

