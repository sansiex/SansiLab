package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.*;

// https://leetcode.cn/problems/course-schedule-iii/
public class CourseSchedule3 extends AbstractProblem{
	public static void main(String[] args) {
		new CourseSchedule3().runTest();
	}
	@Answer
	public int scheduleCourse(int[][] courses) {
		Arrays.sort(courses, Comparator.comparing((int[] a) -> a[0]).thenComparing((int[] a) -> a[1]));
		int acc = 0;
		int last = 0;
		LinkedList<int[]> q = new LinkedList<>();

		for (int i = 0; i < courses.length; i++) {
			int[] c = courses[i];
			if (c[0] > c[1]) {
				continue;
			}
			if (c[1] < last) {
				int tacc = 0;
				int tlast = 0;
				boolean ok = true;
				for (int[] ele : q) {
					if (ele[1] <= c[1]) {
						tacc += ele[0];
						if (tacc > ele[1]) {
							ok = false;
							break;
						}
					} else {
						if (c[1] >= tlast) {
							tacc += c[0];
							if (tacc > c[1]) {
								ok = false;
								break;
							}
						}
						tacc += ele[0];
						if (tacc > ele[1]) {
							ok = false;
							break;
						}
					}
					tlast = ele[1];
				}
				if (!ok) {
					continue;
				}
			}
			int tlast = Math.max(last, c[1]);
			int tacc = acc + c[0];
			if (tacc > tlast) {
				continue;
			}
			acc = tacc;
			last = tlast;
			if (q.isEmpty()) {
				q.add(c);
			} else {
				ListIterator<int[]> itr = q.listIterator();
				boolean find = false;
				while (itr.hasNext()) {
					int[] ele = itr.next();
					if (ele[1] > c[1]) {
						if (itr.hasPrevious()) {
							itr.previous();
							itr.add(c);
						} else {
							q.push(c);
						}
						find = true;
						break;
					}
				}
				if (!find) {
					q.add(c);
				}
			}
		}
		return q.size();
	}



	@Override
	void init() {
		addInputParsers(
				new TwoDimensionArrayParser(new IntParser())
		);
		setOutputParser(new IntParser());

		addTestCaseFromFile("CourseSchedule3.txt");

		addInput("[[860,4825],[13,1389],[746,8823],[455,2778],[233,2069],[106,5648],[802,2969],[958,2636],[567,2439],[623,1360],[700,4206],[9,3725],[241,7381]]");
		addOutput("12");

		addInput("[[7,11],[1,11],[1,3],[2,6],[5,6],[7,7],[4,8],[2,20],[1,17],[8,11]]");
		addOutput("6");

		addInput("[[100,200],[200,1300],[1000,1250],[2000,3200]]");
		addOutput("3");

		addInput("[[1,2]]");
		addOutput("1");

		addInput("[[1,2],[1,2]]");
		addOutput("2");

		addInput("[[3,2],[4,3]]");
		addOutput("0");
	}
}

