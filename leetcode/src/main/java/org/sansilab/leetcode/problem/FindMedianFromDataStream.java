package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.DoubleParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.PriorityQueue;
import java.util.Stack;

// https://leetcode-cn.com/problems/basic-calculator/
public class FindMedianFromDataStream extends AbstractProblem{
	public static void main(String[] args) {
		new FindMedianFromDataStream().runTest();
	}

	private PriorityQueue<Integer> low;
	private PriorityQueue<Integer> high;

	/** initialize your data structure here. */
	public FindMedianFromDataStream() {
		low = new PriorityQueue<Integer>((v1, v2) -> v2.compareTo(v1));
		high = new PriorityQueue<Integer>((v1, v2) -> v1.compareTo(v2));
	}

	public void addNum(int num) {
		if (low.isEmpty()) {
			low.add(num);
			return;
		}

		if (low.size()>high.size()) {
			if (num >= low.peek()) {
				high.add(num);
			} else {
				high.add(low.poll());
				low.add(num);
			}
		} else {
			if (num <= high.peek()) {
				low.add(num);
			} else {
				low.add(high.poll());
				high.add(num);
			}
		}
	}

	@Answer
	public double findMedian() {
		if (low.size()>high.size()) {
			return low.peek().doubleValue();
		} else {
			return (low.peek().doubleValue() + high.peek().doubleValue())/2;
		}
	}

	@Override
	protected void beforeExecute(int no, Object[] input) {
		switch (no) {
			case 0:
				addNum(1);
				addNum(2);
				break;
			case 1:
				addNum(3);
				break;
			default:
				break;
		}
	}

	@Override
	Object execute(Object[] input) {
		return findMedian();
	}

	@Override
	void init() {

		setOutputParser(new DoubleParser());
		addInput();
		addOutput("1.5");
		addInput();
		addOutput("2");
	}
}

