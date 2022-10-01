package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.Arrays;
import java.util.TreeSet;

// https://leetcode.cn/problems/max-sum-of-rectangle-no-larger-than-k/
public class MaxSumOfRectangleNoLargerThanK extends AbstractProblem{
	public static void main(String[] args) {
		new MaxSumOfRectangleNoLargerThanK().runTest();
	}
	@Answer
	public int maxSumSubmatrix(int[][] matrix, int k) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			int[] row = new int[matrix[0].length];
			for (int j = i; j >= 0; j--) {
				for (int l = 0; l < row.length; l++) {
						row[l] = row[l] + matrix[j][l];
				}
				int v = searchArray(row, k);
				if (v > max) {
					max = v;
				}
				if (max== k) {
					return k;
				}
			}
		}
		return max;
	}

	public int searchArray(int[] arr, int k) {
		int[] s = new int[arr.length];
		s[0] = arr[0];
		int max = s[0] <= k ? s[0] : Integer.MIN_VALUE;
		for (int i = 1; i < arr.length; i++) {
			s[i] = arr[i] + s[i-1];
			max = s[i] <= k && s[i] > max ? s[i] : max;
			if (max == k) {
				return k;
			}
			for (int j = 0; j < i; j++) {
				int d = s[i] - s[j];
				if (d > max && d <= k) {
					max = d;
				}
				if (max == k) {
					return k;
				}
			}
		}

		return max;
	}


	@Override
	void init() {
		addInputParsers(
				new TwoDimensionArrayParser(new IntParser())
				, new IntParser()
		);
		setOutputParser(new IntParser());

		addInput("[[1,0,1],[0,-2,3]]", "2");
		addOutput("2");

		addInput("[[2,2,-1]]", "3");
		addOutput("3");

		addInput("[[5,-4,-3,4],[-3,-4,4,5],[5,1,5,-4]]", "10");
		addOutput("10");


	}
}

