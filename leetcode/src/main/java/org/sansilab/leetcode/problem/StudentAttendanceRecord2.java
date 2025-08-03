package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;

import java.util.Arrays;

// https://leetcode.cn/problems/student-attendance-record-ii/
public class StudentAttendanceRecord2 extends AbstractProblem{
	public static void main(String[] args) {
		new StudentAttendanceRecord2().runTest();
	}
	@Answer
	public int checkRecord(int n) {
		if (n == 1) {
			return 3;
		}
		if (n == 2) {
			return 8;
		}
		if (n == 3) {
			return 19;
		}
		long[] dp1 = new long[n];
		long[] dp2 = new long[n];
		dp1[0] = 2l;
		dp1[1] = 4l;
		dp1[2] = 7l;
		dp2[0] = 3l;
		dp2[1] = 8l;
		dp2[2] = 19l;
		long MOD = 1000000007L;
		for (int i = 3; i < n; i++) {
			dp1[i] = (dp1[i-1] + dp1[i-2] + dp1[i-3]) % MOD;
			dp2[i] = (dp2[i-1] + dp1[i-1] + dp2[i-2] + dp1[i-2] + dp1[i-3] + dp2[i-3]) % MOD;
		}
		long res = dp2[n-1];
		return (int)(res % MOD);
	}

	@Override
	void init() {
		addInputParsers(
				new IntParser()
		);
		setOutputParser(new IntParser());

		addInput("2");
		addOutput("8");

		addInput("1");
		addOutput("3");

		addInput("10101");
		addOutput("183236316");
	}
}

