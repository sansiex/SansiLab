package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

// https://leetcode.com/problems/swap-nodes-in-pairs/
public class SwapNodesInPairs {
	public static void main(String[] args){
		SwapNodesInPairs sln=new SwapNodesInPairs();
		ListNode input=InitUtils.getSinglyList("0,1,9,12");
		long s=System.currentTimeMillis();
		Object output=sln.swapPairs(input);
		long e=System.currentTimeMillis();
		System.out.println(e-s+" ms");
		System.out.println(JsonUtils.toJson(output));
	}

	public ListNode swapPairs(ListNode head) {
		ListNode p1=head;
		if (null==p1 || null==p1.next){
			return p1;
		}
		ListNode ret = p1.next;
		ListNode p0=null;
		while (p1!=null){
			ListNode p2=p1.next;
			if (p2==null){
				return ret;
			}
			swap(p0,p1,p2);
			p0=p1;
			p1=p1.next;
		}
		return ret;
	}

	private void swap(ListNode p0, ListNode p1, ListNode p2){
		ListNode p3=p2.next;
		p2.next=p1;
		p1.next=p3;
		if (p0!=null){
			p0.next=p2;
		}
	}
}

