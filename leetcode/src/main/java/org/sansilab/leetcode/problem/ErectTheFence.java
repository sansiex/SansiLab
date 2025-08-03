package org.sansilab.leetcode.problem;

import com.google.common.collect.Lists;
import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.*;

// https://leetcode.cn/problems/erect-the-fence/
public class ErectTheFence extends AbstractProblem{
	public static void main(String[] args) {
		new ErectTheFence().runTest();
	}
	@Answer
	public int[][] outerTrees(int[][] trees) {
		int l = trees.length;
		if (l < 4) {
			return trees;
		}
		Arrays.sort(trees, Comparator.comparing((int[] a) -> a[1]).thenComparing((int[] a) -> a[0]));
		int[] bottom = trees[0];
		Arrays.sort(trees, 1, l, (int[] a, int[] b) -> {
			int x = x(bottom, a, b);
			if (x == 0) {
				int da = Math.abs(a[0] - bottom[0]) + Math.abs(a[1] - bottom[1]);
				int db = Math.abs(b[0] - bottom[0]) + Math.abs(b[1] - bottom[1]);
				return da - db;
			} else {
				return -x;
			}
		});

		int ts = l-1;
		for (int i = l-2; i > 0 ; i--) {
			if (x(bottom, trees[l-1], trees[i]) == 0) {
				ts = i;
				continue;
			}
			break;
		}
		if (ts > 1) {
			Arrays.sort(trees, ts, l, (int[] a, int[] b) -> {
				int da = Math.abs(a[0] - bottom[0]) + Math.abs(a[1] - bottom[1]);
				int db = Math.abs(b[0] - bottom[0]) + Math.abs(b[1] - bottom[1]);
				return db - da;
			});
		}

		Stack<int[]> fence = new Stack<>();
		int[] a = bottom;
		int[] b = trees[1];
		int[] p = trees[2];
		for (int i = 2; i < l; i++) {
			p = trees[i];
			while (x(a, b, p) < 0) {
				b = a;
				a = fence.pop();
			}
			fence.push(a);
			a = b;
			b = p;
		}
		fence.push(a);
		fence.push(p);
		return fence.toArray(new int[][]{});
	}

	int x(int[] p, int[] q, int[] r) {
		int[] d1 = {q[0] - p[0], q[1] - p[1]};
		int[] d2 = {r[0] - q[0], r[1] - q[1]};
		int res = d1[0] * d2[1] - d1[1] * d2[0];
		return res;
	}

	@Override
	void init() {
		addInputParsers(
				new TwoDimensionArrayParser(new IntParser())
		);
		setOutputParser(new TwoDimensionArrayParser(new IntParser()));

		addInput("[[3,0],[4,0],[5,0],[6,1],[7,2],[7,3],[7,4],[6,5],[5,5],[4,5],[3,5],[2,5],[1,4],[1,3],[1,2],[2,1],[4,2],[0,3]]");
		addOutput("[[4,5],[2,5],[6,1],[3,5],[2,1],[1,4],[1,2],[7,4],[7,3],[7,2],[3,0],[0,3],[5,0],[5,5],[4,0],[6,5]]");

		addInput("[[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]");
		addOutput("[[1,1],[2,0],[2,4],[3,3],[4,2]]");

		addInput("[[1,2],[2,2],[4,2]]");
		addOutput("[[1,2],[2,2],[4,2]]");
	}

	@Override
	protected void afterExecute(int no, Object[] input, Object output) {
		int[][] res = (int[][]) output;
		Arrays.sort(res, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt((int[] a) -> a[1]));
	}
}
/*
6
5 XXXXX
4X     X
XX     X
2X  X  X
1 X   X
012XXX6789
 */
