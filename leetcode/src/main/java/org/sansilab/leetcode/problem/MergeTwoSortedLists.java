package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;

// https://leetcode.com/problems/merge-two-sorted-lists/
public class MergeTwoSortedLists {
	public static void main(String[] args){
		MergeTwoSortedLists sln=new MergeTwoSortedLists();
		ListNode input1=InitUtils.getSinglyList("0,1,9,12");
		ListNode input2=InitUtils.getSinglyList("0,10,12,22");
		long s=System.currentTimeMillis();
		Object output=sln.mergeTwoLists(input1, input2);
		long e=System.currentTimeMillis();
		System.out.println(e-s+" ms");
		System.out.println(JsonUtils.toJson(output));
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1==null){
			return l2;
		} else if (l2 == null){
			return l1;
		}
		ListNode ret,cur,p1,p2;
		if (l1.val>l2.val) {
			ret = l2;
			p2 = l1;
			p1 = l2.next;
		} else {
			ret = l1;
			p1 = l1.next;
			p2 = l2;
		}
		cur = ret;
		while (p1!=null && p2!=null) {
			if (p1.val>p2.val) {
				cur.next = p2;
				p2 = p2.next;
				cur = cur.next;
				cur.next=p1;
			} else {
				cur=p1;
				p1 = p1.next;
			}
		}
		if (p1!=null) {
			cur.next = p1;
		} else {
			cur.next = p2;
		}
		return ret;
	}
}

