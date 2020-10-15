package org.sansilab.leetcode.problem;

import java.util.ArrayList;

import org.sansilab.leetcode.structure.TreeNode;
import org.sansilab.leetcode.utils.JsonUtils;

public class BinaryTreeLevelOrderTraversal {
	public static void main(String[] args){
		BinaryTreeLevelOrderTraversal sln=new BinaryTreeLevelOrderTraversal();
		TreeNode input=InitUtils.getTree("0_1,1_2");
		long s=System.currentTimeMillis();
		Object output=sln.levelOrder(input);
		long e=System.currentTimeMillis();
		System.out.println(e-s+" ms");
		System.out.println(JsonUtils.toJson(output));
	}
	
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> list=new ArrayList<>();
		if(root==null){
			return list;
		}
		
		ArrayList<TreeNode> cur=new ArrayList<>();
		ArrayList<TreeNode> next=new ArrayList<>();
		cur.add(root);
		
		
		while(cur.size()>0){
			ArrayList<Integer> vlist=new ArrayList<>();
			
			for(TreeNode n:cur){
				if(n.left!=null){
					next.add(n.left);
				}
				if(n.right!=null){
					next.add(n.right);
				}
				vlist.add(n.val);
			}			
			
			list.add(vlist);
			cur.clear();
			ArrayList<TreeNode> temp=cur;
			cur=next;
			next=temp;
		}
		
		return list;
    }
}

