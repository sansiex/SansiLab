package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Answer;
import org.sansilab.leetcode.parser.ArrayParser;
import org.sansilab.leetcode.parser.BooleanParser;
import org.sansilab.leetcode.parser.IntParser;
import org.sansilab.leetcode.parser.TwoDimensionArrayParser;

import java.util.*;

// https://leetcode.cn/problems/trapping-rain-water-ii/
public class TrappingRainWater2 extends AbstractProblem{
	public static void main(String[] args) {
		new TrappingRainWater2().runTest();
	}
	@Answer
	public int trapRainWater(int[][] heightMap) {
		if (heightMap.length <=2 || heightMap[0].length<=2) {
			return 0;
		}
		int r = heightMap.length;
		int c = heightMap[0].length;
		PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(p -> p[2]));
		int[][] wmap = new int[r][c];
		boolean[][] vmap = new  boolean[r][c];
		int[][] hmap = heightMap;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (i==0  || i==r-1 || j==0 ||j==c-1) {
					vmap[i][j]=true;
					wmap[i][j]=hmap[i][j];
					q.offer(new int[]{i, j, hmap[i][j]});
				}
			}
		}

		int ret = 0;

		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0,1,0,-1};
		while(!q.isEmpty())  {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr <= 0 || nr >= r-1 || nc <= 0 || nc >= c-1 || vmap[nr][nc]) {
					continue;
				}

				wmap[nr][nc] = Math.max(hmap[nr][nc], cur[2]);
				ret  += wmap[nr][nc] - hmap[nr][nc];
				q.offer(new int[]{nr,nc,wmap[nr][nc]});
				vmap[nr][nc]=true;
			}
		}
		return ret;
	}




	@Override
	void init() {
		addInputParsers(
				new TwoDimensionArrayParser(new IntParser())
		);
		setOutputParser(new IntParser());

		addInput(
				"[[5,8,7,7]," +
						  "[5,2,1,5]," +
						  "[7,1,7,1]," +
						  "[8,9,6,9]," +
						  "[9,8,9,9]]");
		addOutput("12");

		addInput(
				"[[5,8,7,7],[5,2,1,5],[7,1,7,1],[8,9,6,9],[9,8,9,9]]");
		addOutput("12");

		addInput("[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]");
		addOutput("4");

		addInput("[[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]");
		addOutput("10");

		addInput("[[12,13,1,12],[13,4,13,12],[13,8,10,12],[12,13,12,12],[13,13,13,13]]");
		addOutput("14");
	}
}

