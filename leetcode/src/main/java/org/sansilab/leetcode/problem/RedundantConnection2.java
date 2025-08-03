package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.BooleanParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.*;
import java.util.function.BiFunction;

// https://leetcode.cn/problems/redundant-connection-ii/
public class RedundantConnection2 extends AbstractProblem{
	public static void main(String[] args) {
		new RedundantConnection2().runTest();
	}

	@Answer
	public int[] findRedundantDirectedConnection(int[][] edges) {
		Map<Integer, Integer> circle = findCircle(edges);
		Map<Integer, Integer> pmap = new HashMap<>();
		int[] ex = null;
		for (int[] e : edges) {
			if (pmap.containsKey(e[1])) {
				ex = e;
			} else {
				pmap.put(e[1], e[0]);
			}
		}
		if (circle.isEmpty()) {
			return ex;
		}
		int n = edges.length;
		if (ex == null) {
			for (int i = n - 1; i >= 0; i--) {
				int[] e = edges[i];
				Integer cv = circle.get(e[1]);
				if (cv != null && cv == e[0]) {
					return e;
				}
			}
		} else {
			if (circle.get(ex[1]) == ex[0]) {
				return ex;
			} else {
				return new int[]{circle.get(ex[1]), ex[1]};
			}
		}

		return null;
	}

	Map<Integer, Integer> findCircle(int[][] edges) {
		List<int[]> list = new LinkedList<>();
		Map<Integer, int[]> dmap = new HashMap<>();
		for (int[] e : edges) {
			list.add(e);
			int[] d = dmap.computeIfAbsent(e[0], k -> new int[]{0, 0});
			d[0]++;
			d = dmap.computeIfAbsent(e[1], k -> new int[]{0, 0});
			d[1]++;
		}
		boolean find = true;
		while (find) {
			find = list.removeIf(e -> {
				int[] d0 = dmap.get(e[0]);
				boolean ret = false;
				if (d0[1] == 0) {
					ret = true;
				}
				int[] d1 = dmap.get(e[1]);
				if (!ret && d1[0] == 0) {
					ret = true;
				}
				if (ret) {
					d0[0]--;
					d1[1]--;
				}
				return ret;
			});
		}
		Map<Integer, Integer> ret = new HashMap<>();
		for (int[] e : list) {
			ret.put(e[1], e[0]);
		}
		return ret;
	}

	@Override
	void init() {
		addInputParsers(
				new TwoDimensionArrayParser(new IntParser())
		);
		setOutputParser(new ArrayParser(new IntParser()));

		addInput("[[1,2],[2,3],[3,4],[4,1],[1,5]]");
		addOutput("[4,1]");

		addInput("[[1,2],[2,3],[3,4],[4,1],[1,5]]");
		addOutput("[4,1]");

		addInput("[[2,1],[3,1],[4,2],[1,4]]");
		addOutput("[2,1]");

		addInput("[[1,2],[1,3],[2,3]]");
		addOutput("[2,3]");
	}
}

