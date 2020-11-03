package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.ListParser;

import java.lang.reflect.Array;
import java.util.*;

// https://leetcode-cn.com/problems/sliding-window-maximum/submissions/
public class CountOfSmallerNumbersAfterSelf extends AbstractProblem{
	public static void main(String[] args) {
		new CountOfSmallerNumbersAfterSelf().runTest();
	}

	int[] c;

	int lowBit(int i){
		return i&(-i);
	}

	void update(int i, int k){
		while(i<c.length){
			c[i]+=k;
			i+=lowBit(i);
		}
	}

	int sum(int i){
		int sum=0;
		while(i>0){
			sum+=c[i];
			i-=lowBit(i);
		}
		return sum;
	}

	@Answer
	public List<Integer> countSmaller(int[] nums) {
		Map<Integer, Integer> numMap = new HashMap<>();
		HashSet<Integer> set = new HashSet<>();
		for (int n:nums){
			set.add(n);
		}
		c=new int[set.size()+1];
		int[] a = new int[set.size()];
		int i=0;
		for (int n:set) {
			a[i++] = n;
		}
		Arrays.sort(a);
		for (int j = 0; j < a.length; j++) {
			numMap.put(a[j], j);
		}

		List<Integer> ret = new ArrayList<>(nums.length);
		for (int j = nums.length-1; j >= 0 ; j--) {
			int n = nums[j];
			int id = numMap.get(n);
			if (id==0){
				ret.add(0);
			} else {
				ret.add(sum(id));
			}
			update(id+1, 1);
		}
		Collections.reverse(ret);
		return ret;
	}

	@Override
	void init() {
		addInputParsers(
				new ArrayParser(new IntParser())
		);
		setOutputParser(new ListParser(new IntParser()));

		addInput("[5,2,6,1]");
		addOutput("[2,1,1,0]");


		addInput("[5,2,6,1,4,7,2,2,4]");
		addOutput("[6,1,5,0,2,3,0,0,0]");
	}
}

