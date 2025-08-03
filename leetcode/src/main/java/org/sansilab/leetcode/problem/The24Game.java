package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.BooleanParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.function.BiFunction;

// https://leetcode.cn/problems/24-game/
public class The24Game extends AbstractProblem{
	public static void main(String[] args) {
		new The24Game().runTest();
	}

	@Answer
	public boolean judgePoint24(int[] cards) {
		return recur(1, cards);
	}

	char[] ops = {'+', '-', '*', '/'};

	boolean recur(int base, int[] cards) {
		if (base == 0) {
			return false;
		}
		if (cards.length == 1) {
			if (cards[0] / base == 24 && cards[0] % base == 0) {
				return true;
			} else {
				return false;
			}
		}

		for (char op : ops) {
			boolean suc = false;
			if (op == '+' || op == '*') {
				suc = select2c(cards, (i, j) -> {
					int[] res = calc(op, cards[i], cards[j], base);
					int[] nextCards = new int[cards.length - 1];
					nextCards[0] = res[0];
					int ni = 1;
					for (int k = 0; k < cards.length; k++) {
						if (k == i || k == j) {
							continue;
						}
						nextCards[ni] = cards[k] * res[1];
						ni++;
					}
					return recur(base * res[1], nextCards);
				});
			} else {
				suc = select2(cards, (i, j) -> {
					int[] res = calc(op, cards[i], cards[j], base);
					int[] nextCards = new int[cards.length - 1];
					nextCards[0] = res[0];
					int ni = 1;
					for (int k = 0; k < cards.length; k++) {
						if (k == i || k == j) {
							continue;
						}
						nextCards[ni] = cards[k] * res[1];
						ni++;
					}
					return recur(base * res[1], nextCards);
				});
			}
			if (suc) {
				System.out.println(op + ", base: " + base);
				return true;
			}
		}
		return false;
	}

	boolean select2(int[] cards, BiFunction<Integer, Integer, Boolean> func) {
		for (int i = 0; i < cards.length; i++) {
			for (int j = 0; j < cards.length; j++) {
				if (i == j) {
					continue;
				}
				if(func.apply(i, j)) {
					System.out.println(i + ", " + j);
					return true;
				}
			}
		}
		return false;
	}

	boolean select2c(int[] cards, BiFunction<Integer, Integer, Boolean> func) {
		for (int i = 0; i < cards.length - 1; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if(func.apply(i, j)) {
					System.out.println(i + ", " + j);
					return true;
				}
			}
		}
		return false;
	}

	int[] calc(char op, int a, int b, int base) {
		if (op == '+') {
			return new int[]{a + b, 1};
		} else if (op == '-') {
			return new int[]{a - b, 1};
		} else if (op == '*') {
			return new int[]{a * b, base};
		} else {
			return new int[]{a*base, b};
		}
	}

	@Override
	void init() {
		addInputParsers(
				new ArrayParser(new IntParser())
		);
		setOutputParser(new BooleanParser());

		addInput("[3,9,7,7]");
		addOutput("true");

		addInput("[1,3,4,6]");
		addOutput("true");

		addInput("[4,4,4,4]");
		addOutput("true");

		addInput("[4,1,8,7]");
		addOutput("true");

		addInput("[1,2,1,2]");
		addOutput("false");
	}
}

