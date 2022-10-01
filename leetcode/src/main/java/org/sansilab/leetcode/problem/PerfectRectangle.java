package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.BooleanParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.*;

// https://leetcode.cn/problems/perfect-rectangle/
public class PerfectRectangle extends AbstractProblem{
	public static void main(String[] args) {
		new PerfectRectangle().runTest();
	}
	@Answer
	public boolean isRectangleCover(int[][] rectangles) {
		int l = Integer.MAX_VALUE;
		int b = Integer.MAX_VALUE;
		int t = Integer.MIN_VALUE;
		int r = Integer.MIN_VALUE;

		Map<String, List<int[]>>  pmap = new HashMap<>();

		int ssum = 0;
		for (int i = 0; i < rectangles.length; i++) {
			int[] rect = rectangles[i];
			l = Math.min(l, rect[0]);
			b = Math.min(b, rect[1]);
			r = Math.max(r, rect[2]);
			t = Math.max(t, rect[3]);
			ssum += (rect[2] - rect[0]) * (rect[3] - rect[1]);
			String[] pts = new String[]{
					rect[0]+"_"+rect[1],
					rect[0]+"_"+rect[3],
					rect[2]+"_"+rect[1],
					rect[2]+"_"+rect[3]
			};
			for (String pt : pts) {
				pmap.computeIfAbsent(pt, k->new ArrayList<>()).add(rect);
			}
		}
		Set<String> angles = new HashSet<>();
		angles.add(l+"_"+t);
		angles.add(l+"_"+b);
		angles.add(r+"_"+t);
		angles.add(r+"_"+b);

		int ts = (t-b) * (r-l);
		if (ssum != ts) {
			return false;
		}

		int[] index = {0,1,0,3,2,1,2,3};
		for (Map.Entry<String, List<int[]>> ent : pmap.entrySet()) {
			List<int[]>  list = ent.getValue();
			String pt = ent.getKey();
			if (angles.contains(pt)) {
				if (list.size() > 1) {
					return false;
				}
				continue;
			}
			if (list.size()%2 == 1) {
				return false;
			}
		}

		return true;
	}


	@Override
	void init() {
		addInputParsers(
				new TwoDimensionArrayParser(new IntParser())
		);
		setOutputParser(new BooleanParser());

		addInput("[[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]");
		addOutput("true");

		addInput("[[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]");
		addOutput("false");

		addInput("[[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]");
		addOutput("false");

		addInput("[[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]");
		addOutput("false");
	}
}

