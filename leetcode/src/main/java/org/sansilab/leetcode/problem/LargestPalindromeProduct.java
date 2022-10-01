package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.cn/problems/largest-palindrome-product/
public class LargestPalindromeProduct extends AbstractProblem{
	public static void main(String[] args) {
		new LargestPalindromeProduct().runTest();
	}
	@Answer
	public int largestPalindrome(int n) {
		if (n == 1) {
			return 9;
		}
		int base = (int) Math.pow(10, n);
		long cnt = 0;
		for (int i = base-1; i >= 0 ; i--) {
			char[] chars = String.valueOf(i).toCharArray();
			for (int j = 0; j < chars.length/2; j++) {
				char tmp = chars[j];
				chars[j] = chars[chars.length-j-1];
				chars[chars.length-j-1] = tmp;
			}
			int right = Integer.parseInt(new String(chars));
			long full = (long)i*base+right;
			int min = base/10;
			for (int j = base - 1; j > min; j--) {
				cnt ++;
				if ((long)j*j < full) {
					break;
				}
				if (full%j > 0) {
					continue;
				}
				long p = full/j;
				if (p>j) {
					break;
				}

				if (p > min ) {
					System.out.println("cnt:  " + cnt);
					return (int) (full%1337);
				}
			}
		}
		return  0;
	}


	@Override
	void init() {
		addInputParsers(
				new IntParser()
		);
		setOutputParser(new IntParser());

		addInput("6");
		addOutput("1218");

		addInput("1");
		addOutput("9");

		addInput("2");
		addOutput("987");

		addInput("7");
		addOutput("1218");
	}
}

