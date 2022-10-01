package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;

// https://leetcode-cn.com/problems/min-stack/
public class MinStack {
	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin()); // return -3
		minStack.pop();
		System.out.println(minStack.top());    // return 0
		System.out.println(minStack.getMin()); // return -2
	}

	TreeMap<Integer, Integer> map;
	Stack<Integer> stack;

	public MinStack() {
		map = new TreeMap<>();
		stack = new Stack<>();
	}

	public void push(int val) {
		stack.push(val);
		Integer cnt = map.get(val);
		if (cnt != null) {
			map.put(val, cnt + 1);
		} else {
			map.put(val, 1);
		}
	}

	public void pop() {
		int val = stack.pop();
		int cnt = map.get(val);
		if (cnt == 1) {
			map.remove(val);
		} else {
			map.put(val, cnt - 1);
		}
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		return map.firstKey();
	}

}

