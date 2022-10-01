package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.StringParser;

import java.util.*;

// https://leetcode.cn/problems/strong-password-checker/
public class StringPasswordChecker extends AbstractProblem{
	public static void main(String[] args) {
		new StringPasswordChecker().runTest();
	}
	@Answer
	public int strongPasswordChecker(String password) {
		char lc = password.charAt(0);
		Set<Character> cset = new HashSet<>();
		cset.add(type(lc));
		int over=0;
		int len=1;
		List<Integer> segs = new ArrayList<>();
		for (int i = 1; i < password.length(); i++) {
			char c = password.charAt(i);
			char type = type(c);
			if (type !=  '.')  {
				cset.add(type);
			}
			if (c==lc) {
				len++;
				if (len >= 3) {
					over++;
				}
			} else {
				if (len >= 3) {
					segs.add(len);
				}
				len=1;
			}
			lc=c;
		}
		if (len >=3) {
			segs.add(len);
		}

		int typeDiff = 3-cset.size();
		if (password.length()<6) {
			int diff = 6-password.length();
			return Math.max(Math.max(diff, typeDiff), segs.size());
		} else if (password.length()>20) {
			int diff = password.length() - 20;
			if (over > diff) {
				int change = 0;
				Collections.sort(segs, Comparator.comparing(seg -> (seg-2)%3));
				int tmp = diff;
				for (int i = 0; i < segs.size(); i++) {
					int so = (segs.get(i) - 2)%3;
					if (so == 0 || tmp == 0) {
						continue;
					}
					int rem = Math.max(0, tmp - so);
					int d = tmp - rem;
					tmp-=d;
					segs.set(i, segs.get(i)-d);
				}

				for (Integer seg : segs) {
					if (seg == 0) {
						continue;
					}
					if (tmp > 0) {
						int rem = Math.max(0, tmp - (seg - 2));
						int d = tmp - rem;
						tmp -= d;
						seg-=d;
					}
					change += seg/3;
				}

				return diff + Math.max(change, typeDiff);
			} else {
				return diff + typeDiff;
			}
		}  else {
			int change = 0;
			for (int i = 0; i < segs.size(); i++) {
				change+=segs.get(i)/3;
			}
			return Math.max(typeDiff, change);
		}
	}

	char type(char c) {
		if (c>='0' && c<='9') {
			return '0';
		}
		if (c>='a' && c<='z') {
			return 'a';
		}
		if (c>='A'&&c<='Z') {
			return 'A';
		}
		return  '.';
	}

	@Override
	void init() {
		addInputParsers(
				new StringParser()
		);
		setOutputParser(new IntParser());

		addInput(
				"bbaaaaaaaaaaaaaaacccccc");
		addOutput("8");

		addInput(
				"aaaabbaaabbaaa123456A");
		addOutput("3");

		addInput(
				"ABABABABABABABABABAB1");
		addOutput("2");

		addInput(
				"aaa111");
		addOutput("2");

		addInput(
				"a");
		addOutput("5");

		addInput(
				"aA1");
		addOutput("3");

		addInput(
				"1337C0d3");
		addOutput("0");
	}
}

