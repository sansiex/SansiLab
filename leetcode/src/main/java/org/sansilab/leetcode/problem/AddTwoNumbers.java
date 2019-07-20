package org.sansilab.leetcode.problem;

public class AddTwoNumbers extends BaseProblem {
    public static void main(String[] args) {
        AddTwoNumbers problem = new AddTwoNumbers();
        ListNode input11 = InitUtils.getSinglyList("2,4,3");
        ListNode input12 = InitUtils.getSinglyList("5,6,4");
        ListNode result1 = problem.addTwoNumbers(input11, input12);
        System.out.println(result1.toString());


        ListNode input21 = InitUtils.getSinglyList("2");
        ListNode input22 = InitUtils.getSinglyList("5,6,4");
        ListNode result2 = problem.addTwoNumbers(input21, input22);
        System.out.println(result2.toString());


        ListNode input31 = InitUtils.getSinglyList("0,1,1,3");
        ListNode input32 = InitUtils.getSinglyList("0,6,4");
        ListNode result3 = problem.addTwoNumbers(input31, input32);
        System.out.println(result3.toString());


        ListNode input41 = InitUtils.getSinglyList("0");
        ListNode input42 = InitUtils.getSinglyList("0");
        ListNode result4 = problem.addTwoNumbers(input41, input42);
        System.out.println(result4.toString());


        ListNode input51 = InitUtils.getSinglyList("5");
        ListNode input52 = InitUtils.getSinglyList("5");
        ListNode result5 = problem.addTwoNumbers(input51, input52);
        System.out.println(result5.toString());
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode pr = null;
        boolean up = false;
        ListNode ret = null;
        while (true) {
            int val = 0;
            if (up) {
                val++;
                up = false;
            }
            if (p1 != null) {
                val += p1.val;
            }

            if (p2 != null) {
                val += p2.val;
            }
            if (val > 9) {
                val -= 10;
                up = true;
            }
            ListNode next = new ListNode(val);
            if (ret == null) {
                ret = next;
            } else if(p1 == null && p2 == null && val == 0) {
                break;
            } else {
                pr.next = next;
            }
            pr = next;

            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }

        }

        return ret;
    }
}
