package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.*;

import java.util.*;

// https://leetcode.cn/problems/reconstruct-itinerary/
public class ReconstructItinerary extends AbstractProblem{
	public static void main(String[] args) {
		new ReconstructItinerary().runTest();
	}
	@Answer
	public List<String> findItinerary(List<List<String>> tickets) {
		Map<String, List<List<String>>> tmap = new HashMap<>();
		Map<String, List<Boolean>> umap = new HashMap<>();
		for (List<String> t : tickets) {
			tmap.computeIfAbsent(t.get(0), k -> new ArrayList<>()).add(t);
			umap.computeIfAbsent(t.get(0), k -> new ArrayList<>()).add(false);
		}
		tmap.forEach((s, list) -> {
			list.sort(Comparator.comparing(t -> t.get(1)));
		});
		LinkedList<String> res = new LinkedList<>();
		res.add("JFK");
		recurse("JFK", tickets.size() + 1, res, tmap, umap);
		return res;
	}

	public boolean recurse(
			String cur, int total, LinkedList<String> res,
			Map<String, List<List<String>>> tmap, Map<String, List<Boolean>> umap
	) {
		if (res.size() == total) {
			return true;
		}

		List<List<String>> tlist = tmap.get(cur);
		if (tlist == null) {
			return false;
		}
		List<Boolean> ulist = umap.get(cur);
		for (int i = 0; i < tlist.size(); i++) {
			if (ulist.get(i)) {
				continue;
			}
			List<String> t = tlist.get(i);
			ulist.set(i, true);
			res.add(t.get(1));
			if (recurse(t.get(1), total, res, tmap, umap)) {
				return true;
			} else {
				ulist.set(i, false);
				res.pollLast();
			}
		}
		return false;
	}


	@Override
	void init() {
		addInputParsers(
				new TwoDimensionListParser(new StringParser())
		);
		setOutputParser(new ListParser(new StringParser()));

		addInput("[[\"JFK\",\"SFO\"],[\"JFK\",\"ATL\"],[\"SFO\",\"ATL\"],[\"ATL\",\"JFK\"],[\"ATL\",\"SFO\"]]");
		addOutput("[\"JFK\",\"ATL\",\"JFK\",\"SFO\",\"ATL\",\"SFO\"]");

		addInput("[[\"MUC\",\"LHR\"],[\"JFK\",\"MUC\"],[\"SFO\",\"SJC\"],[\"LHR\",\"SFO\"]]");
		addOutput("[\"JFK\",\"MUC\",\"LHR\",\"SFO\",\"SJC\"]");

		addInput("[[\"JFK\",\"KUL\"],[\"JFK\",\"NRT\"],[\"NRT\",\"JFK\"]]");
		addOutput("[\"JFK\",\"NRT\",\"JFK\",\"KUL\"]");


	}
}

