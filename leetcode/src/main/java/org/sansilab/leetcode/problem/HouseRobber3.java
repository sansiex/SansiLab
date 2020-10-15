package org.sansilab.leetcode.problem;

import com.google.common.collect.Lists;
import org.sansilab.leetcode.structure.TreeNode;
import org.sansilab.leetcode.utils.JsonUtils;

import java.util.List;


//https://leetcode.com/problems/counting-bits/
public class HouseRobber3 {
	public static void main(String[] args){
		HouseRobber3 sln=new HouseRobber3();
		List<TreeNode> list = Lists.newArrayList(
				t(3,t(2, null, t(3)), t(3, null, t(1)))
				, t(3, t(4, t(2), t(3)), t(5, null, t(1)))
		);
		for (TreeNode input:list) {
			long s=System.currentTimeMillis();
			Object output=sln.rob(input);
			long e=System.currentTimeMillis();
			System.out.println(e-s+" ms");
			System.out.println(JsonUtils.toJson(output));
		}
	}

	private static TreeNode t(int v, TreeNode l, TreeNode r){
		return new TreeNode(v, l, r);
	}

	private static TreeNode t(int v){
		return new TreeNode(v, null, null);
	}

	public int rob(TreeNode root) {
		return rob(root, true);
	}

	public int rob(TreeNode root, boolean a) {
		if (root==null) {
			return 0;
		}
		if (a) {
			int v1 = root.val+rob(root.left, false)+rob(root.right, false);
			int v2 = rob(root.left, true)+rob(root.right, true);
			return Math.max(v1, v2);
		} else {
			return rob(root.left, true)+rob(root.right, true);
		}
	}
}

