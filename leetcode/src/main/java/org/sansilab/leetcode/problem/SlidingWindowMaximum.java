package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.*;

// https://leetcode-cn.com/problems/sliding-window-maximum/submissions/
public class SlidingWindowMaximum extends AbstractProblem{
	public static void main(String[] args) {
		new SlidingWindowMaximum().runTest();
	}

	@Answer
	public int[] maxSlidingWindow(int[] nums, int k) {
		LinkedList<Integer> q = new LinkedList<>();
		Integer[] tmp = new Integer[k];
		int[] ret = new int[nums.length - k + 1];
		for (int i = 0; i < k; i++) {
			tmp[i] = i;
		}
		Arrays.sort(tmp, (o1, o2) -> {
			return nums[o1] - nums[o2];
		});
		for (int i = 0; i < k; i++) {
			q.push(tmp[i]);
		}
		ret[0] = nums[tmp[k-1]];
		for (int i = k; i < nums.length; i++) {
			int cv = nums[i];
			int val = nums[q.peekLast()];
			while(cv >= val){
				q.pollLast();
				if (q.isEmpty()) {
					break;
				}
				val = nums[q.peekLast()];
			}
			q.addLast(i);
			while(q.peekFirst() <= i - k){
				q.poll();
			}
			ret[i-k+1] = nums[q.peekFirst()];
		}
		return ret;
	}

	@Override
	void init() {
		addInputParsers(
				new ArrayParser(new IntParser())
				,new IntParser()
		);
		setOutputParser(new ArrayParser(new IntParser()));

		addInput("[1,3,1,2,0,5]", "3");
		addOutput("[3,3,2,5]");

		addInput("[1,3,-1,-3,5,3,6,7]", "3");
		addOutput("[3,3,5,5,6,7]");

		addInput("[1]", "1");
		addOutput("[1]");

		addInput("[1,-1]", "1");
		addOutput("[1,-1]");

		addInput("[9,11]", "2");
		addOutput("[11]");

		addInput("[4,-2]", "2");
		addOutput("[4]");
	}
}

