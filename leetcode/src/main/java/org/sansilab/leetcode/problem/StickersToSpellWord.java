package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.*;

// https://leetcode.cn/problems/stickers-to-spell-word/
public class StickersToSpellWord extends AbstractProblem{
	public static void main(String[] args) {
		new StickersToSpellWord().runTest();
	}

	@Answer
	public int minStickers(String[] stickers, String target) {
		int mask = (1 << target.length()) - 1;
		int[] dp = new int[mask + 1];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		return recur(stickers, target, mask, dp);
	}

	private int recur(String[] stickers, String target, int mask, int[] dp) {
		if (dp[mask] == Integer.MAX_VALUE) {
			return -1;
		} else if (dp[mask] > -1) {
			return dp[mask];
		}

		int ret = Integer.MAX_VALUE;
		for (int i = 0; i < stickers.length; i++) {
			String s = stickers[i];
			Map<Character, Integer> smap = new HashMap<>();
			for (int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				smap.put(c, smap.getOrDefault(c, 0) + 1);
			}
			int left = mask;
			for (int j = 0; j < target.length(); j++) {
				int flag = 1 << j;
				if ((left & flag) != flag) {
					continue;
				}
				char c = target.charAt(j);
				if (smap.getOrDefault(c, 0) > 0) {
					left -= flag;
					smap.put(c, smap.get(c) - 1);
				}
			}
			if (left == mask) {
				continue;
			}
			int res = recur(stickers, target, left, dp);
			if (res > -1) {
				int val = 1 + res;
				ret = Math.min(val, ret);
			}
		}
		if (ret < Integer.MAX_VALUE) {
			dp[mask] = ret;
			return ret;
		} else {
			dp[mask] = Integer.MAX_VALUE;
			return -1;
		}
	}


	@Override
	void init() {
		addInputParsers(
				new ArrayParser(new StringParser())
				,new StringParser()
		);
		setOutputParser(new IntParser());

		addInput("[\"and\",\"pound\",\"force\",\"human\",\"fair\",\"back\",\"sign\",\"course\",\"sight\",\"world\",\"close\",\"saw\",\"best\",\"fill\",\"late\",\"silent\",\"open\",\"noon\",\"seat\",\"cell\",\"take\",\"between\",\"it\",\"hundred\",\"hat\",\"until\",\"either\",\"play\",\"triangle\",\"stay\",\"separate\",\"season\",\"tool\",\"direct\",\"part\",\"student\",\"path\",\"ear\",\"grow\",\"ago\",\"main\",\"was\",\"rule\",\"element\",\"thing\",\"place\",\"common\",\"led\",\"support\",\"mean\"]",
				"quietchord");
		addOutput("-1");

		addInput("[\"indicate\",\"why\",\"finger\",\"star\",\"unit\",\"board\",\"sister\",\"danger\",\"deal\",\"bit\",\"phrase\",\"caught\",\"if\",\"other\",\"water\",\"huge\",\"general\",\"read\",\"gold\",\"shall\",\"master\",\"men\",\"lay\",\"party\",\"grow\",\"view\",\"if\",\"pull\",\"together\",\"head\",\"thank\",\"street\",\"natural\",\"pull\",\"raise\",\"cost\",\"spoke\",\"race\",\"new\",\"race\",\"liquid\",\"me\",\"please\",\"clear\",\"could\",\"reply\",\"often\",\"huge\",\"old\",\"nor\"]", "fhhfiyfdcwbycma");
		addOutput("9");

		addInput("[\"these\",\"guess\",\"about\",\"garden\",\"him\"]", "atomher");
		addOutput("3");

		addInput("[\"with\",\"example\",\"science\"]", "thehat");
		addOutput("3");

		addInput("[\"notice\",\"possible\"]", "basicbasic");
		addOutput("-1");
	}
}

