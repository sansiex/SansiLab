package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/count-the-repetitions/
public class CountTheRepetitions extends AbstractProblem{
	public static void main(String[] args) {
		new CountTheRepetitions().runTest();
	}
	@Answer
	public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
		int len1 = s1.length()*n1;
		Map<Integer, int[]> pmap = new HashMap<>();

		int t1 = 0;
		int t2 = 0;
		int p1 = 0;
		int p2 = 0;
		int cnt = 0;
		while (true) {
			if (p1 >= len1) {
				return t2/n2;
			}
			if ((p1+1) % s1.length() == 0) {
				t1++;
			}

			char c1 = s1.charAt(p1%s1.length());
			char c2 = s2.charAt(p2%s2.length());
			if (c1==c2) {
				if ((p2+1) % s2.length() == 0) {
					t2++;
				}
				if (p2%s2.length() == 0) {
					int pos = p1%s1.length();
					if (pmap.containsKey(pos)) {
						int[] tarr = pmap.get(pos);
						int p1d = p1 - tarr[0];
						int p2d = p2 - tarr[1];
						cnt += tarr[1];
						int loopCnt = (len1 - tarr[0])/p1d;
						cnt += p2d*loopCnt;
						//计算最后一个循环节之后剩余部分，匹配s2个数
						int p1tail = tarr[0] + loopCnt * p1d;
						int p2tail = p2;
						while (p1tail < len1) {
							c1 = s1.charAt(p1tail%s1.length());
							c2 = s2.charAt(p2tail%s2.length());
							if (c1 == c2) {
								if ((p2tail+1)%s2.length()==0)  {
									cnt+=s2.length();
								}
								p1tail++;
								p2tail++;
							}else {
								p1tail++;
							}
						}

						break;
					} else {
						pmap.put(pos, new int[]{p1, p2});
					}
				}

				p1++;
				p2++;
			} else {
				p1++;
			}
		}
		return cnt/(n2*s2.length());
	}


	@Override
	void init() {
		addInputParsers(
				new StringParser()
				, new IntParser()
				, new StringParser()
				, new IntParser()
		);
		setOutputParser(new IntParser());

		addInput("caahumeayllfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikeffmznimkkasvwsrenazkycxaa"
				,"1000000"
				,"aac"
				,"100");
		addOutput("29999");

		addInput("phqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikeffmznimkkasvwsrenzkycxf"
				,"1000000"
				,"xtlsgypsfadpooefxzbcoejuvpvaboygpoeylfpbnpljvrvipyamyehwqnqrqpmxujjloovaowuxwhmsncbxcoksfzkvatxdknly"
				,"100");
		addOutput("303");

		addInput("acb", "4", "ab", "2");
		addOutput("2");

		addInput("acb", "1", "acb", "1");
		addOutput("1");


	}
}

