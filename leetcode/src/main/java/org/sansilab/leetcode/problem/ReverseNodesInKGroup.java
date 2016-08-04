package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/reverse-nodes-in-k-group/
public final class ReverseNodesInKGroup {
    public static void main(String[] args) {
        ReverseNodesInKGroup sln = new ReverseNodesInKGroup();

//        String[] p1 = {"This", "is", "an", "example", "of", "text", "justification."};
//        int p2=16;

        ListNode p1 = InitUtils.getSinglyList("1");
        int p2=2;
        //3
        long s = System.currentTimeMillis();
        Object output = sln.reverseKGroup(p1, p2);
        long e = System.currentTimeMillis();
        System.out.println(e - s + " ms");
        System.out.println(output.toString());
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k<=1) {
            return head;
        }
        ListNode ph = head;
        ListNode pt = head;
        ListNode pp = null;
        ListNode pn = null;
        ListNode nh = head;
        while (ph!=null) {
            for (int i = 0; i < k-1; i++) {
                pt=pt.next;
                if (pt == null){
                    return nh;
                }
            }
            if (nh==head) {
                nh = pt;
            }
            pn = pt.next;
            reverse(ph, pt, pp, pn);
            pp = ph;
            ph = pn;
            pt = pn;
        }
        return nh;
    }

    private void reverse(ListNode h, ListNode t, ListNode pre, ListNode next){
        ListNode f = next;
        ListNode c = h;
        ListNode n = c;
        while (n!=next) {
            n=c.next;
            c.next = f;
            f = c;
            c=n;
        }
        if (pre!=null) {
            pre.next = t;
        }
    }
}

