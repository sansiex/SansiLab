package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.*;

// https://leetcode-cn.com/problems/basic-calculator/
public class BasicCalculator extends AbstractProblem{
	public static void main(String[] args) {
		new BasicCalculator().runTest();
	}

	@Answer
	public int calculate(String s) {
		Stack<Integer> numStack = new Stack<>();
		Stack<Character> opStack = new Stack<>();

		boolean num = false;
		int val = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' '){
				continue;
			}
			if (c >= '0' && c <= '9') {
				int cv = c-'0';
				if (num) {
					val = val*10+cv;
				} else {
					num = true;
					val = cv;
				}
			} else {
				if (num) {
					num = false;
					addNum(val, numStack, opStack);
					val = 0;
				}

				if (c == ')') {
					int opr = numStack.pop();
					opStack.pop();
					addNum(opr, numStack, opStack);
				} else {
					opStack.push(c);
				}
			}
		}

		if (num) {
			addNum(val, numStack, opStack);
		}

		return numStack.pop();
	}

	private void addNum(int val, Stack<Integer> numStack, Stack<Character> opStack){
		if (opStack.isEmpty()) {
			numStack.push(val);
		} else {
			char op = opStack.peek();
			if (op == '(') {
				numStack.push(val);
			} else {
				opStack.pop();
				int opr = numStack.pop();
				if (op == '+') {
					addNum(opr + val, numStack, opStack);
				} else if (op == '-') {
					addNum(opr - val, numStack, opStack);
				}
			}
		}
	}

	@Override
	void init() {
		addInputParsers(new StringParser());
		setOutputParser(new IntParser());

		addInput("1 + 1");
		addOutput("2");

		addInput(" 2-1 + 2 ");
		addOutput("3");

		addInput("(1+(4+5+2)-3)+(6+8)");
		addOutput("23");
	}
}

