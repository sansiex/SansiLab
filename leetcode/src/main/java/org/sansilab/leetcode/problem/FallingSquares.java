package org.sansilab.leetcode.problem;

import com.google.common.collect.Lists;
import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.*;

import java.util.*;

// https://leetcode.cn/problems/falling-squares/
public class FallingSquares extends AbstractProblem{
	public static void main(String[] args) {
		new FallingSquares().runTest();
	}

	@Answer
	public List<Integer> fallingSquares(int[][] positions) {
		TreeMap<Integer, int[]> hmap = new TreeMap();
		List<Integer> ret = new ArrayList<>(positions.length);
		int highest = -1;
		for (int[] p : positions) {
			int last = p[0] + p[1];
			int h = p[1];
			while (last > -1) {
				Map.Entry<Integer, int[]> ent = hmap.floorEntry(last - 1);
				if (ent == null) {
					break;
				}
				int[] e = ent.getValue();
				if (e[0] + e[1] <= p[0]) {
					break;
				}
				hmap.remove(e[0]);
				if (e[0] + e[1] > last + 1) {
					hmap.put(last, new int[]{last, e[0] + e[1] - last, e[2]});
				}
				if (e[0] < p[0]) {
					hmap.put(e[0], new int[]{e[0], p[0] - e[0], e[2]});
				}
				last = e[0];
				h = Math.max(h, e[2] + p[1]);
			}
			hmap.put(p[0], new int[]{p[0], p[1], h});
			highest = Math.max(highest, h);
			ret.add(highest);
		}
		return ret;
	}


	@Override
	void init() {
		addInputParsers(
				new TwoDimensionArrayParser(new IntParser())
		);
		setOutputParser(new ListParser(new IntParser()));

		addInput("[[4,9],[8,8],[6,8],[8,2],[1,2]]");
		addOutput("[9,17,25,27,27]");

		addInput("[[4,9],[8,8],[6,8],[8,2],[1,2]]");
		addOutput("[9,17,25,27,27]");

		addInput("[[7,2],[1,7],[9,5],[1,8],[3,4]]");
		addOutput("[2,9,9,17,21]");

		addInput("[[1,2],[1,3]]");
		addOutput("[2,5]");

		addInput("[[100,100],[200,100]]");
		addOutput("[100,100]");

		addInput("[[1,2],[2,3],[6,1]]");
		addOutput("[2,5,5]");
	}
}

