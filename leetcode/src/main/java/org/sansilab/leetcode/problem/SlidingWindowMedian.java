package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.DoubleParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;

// https://leetcode.cn/problems/sliding-window-median/
public class SlidingWindowMedian extends AbstractProblem{
	public static void main(String[] args) {
		new SlidingWindowMedian().runTest();
	}
	@Answer
	public double[] medianSlidingWindow(int[] nums, int k) {
		PriorityQueue<Long> lowSet = new PriorityQueue<>(Comparator.comparingLong(a -> (long) a).reversed());
		PriorityQueue<Long> highSet = new PriorityQueue<>();
		lowSet.add((long) nums[0]);

		for (int i = 1; i < k; i++) {
			add(lowSet, highSet, nums[i]);
		}

		double[] ret = new double[nums.length-k+1];
		ret[0]  = getMedian(lowSet, highSet);

		for (int i = k; i < nums.length; i++) {
			int val = nums[i];
			add(lowSet, highSet, val);
			int del = nums[i-k];
			if (del <= lowSet.peek()) {
				lowSet.remove((long)del);
				if (lowSet.size() < highSet.size()) {
					long minHighSet = highSet.peek();
					highSet.remove(minHighSet);
					lowSet.add(minHighSet);
				}
			} else {
				highSet.remove((long)del);
				if (lowSet.size() > highSet.size()+1) {
					long maxLowSet = lowSet.peek();
					lowSet.remove(maxLowSet);
					highSet.add(maxLowSet);
				}
			}

			ret[i-k+1]=getMedian(lowSet, highSet);
		}
		return ret;
	}

	public void add(
			PriorityQueue<Long> lowSet
			,PriorityQueue<Long> highSet
			,int val
	) {
		long maxLowSet = lowSet.peek();
		if (val <= maxLowSet) {
			if (lowSet.size() > highSet.size()) {
				lowSet.remove(maxLowSet);
				highSet.add(maxLowSet);
			}
			lowSet.add((long) val);
		} else {
			highSet.add((long) val);
			if (lowSet.size() < highSet.size()) {
				long minHighSet = highSet.peek();
				highSet.remove(minHighSet);
				lowSet.add(minHighSet);
			}
		}
	}

	public double getMedian(PriorityQueue<Long> lowSet
			,PriorityQueue<Long> highSet) {
		if (lowSet.size()==highSet.size()) {
			return (lowSet.peek() + highSet.peek())/2d;
		} else {
			return lowSet.peek();
		}
	}


	@Override
	void init() {
		addInputParsers(
				new ArrayParser(new IntParser())
				, new IntParser()
		);
		setOutputParser(new ArrayParser(new DoubleParser()));

		addInput("[2147483647,2147483647]", "2");
		addOutput("[2147483647.0]");

		addInput("[2147483647,2147483647]", "2");
		addOutput("[2147483647.0]");

		addInput("[1,2,3,4,2,3,1,4,2]", "3");
		addOutput("[2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]");

		addInput("[1,3,-1,-3,5,3,6,7]", "3");
		addOutput("[1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]");
	}
}

