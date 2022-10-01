package org.sansilab.leetcode.problem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.cn/problems/all-oone-data-structure/
public class AllOOneDataStructure {
    Node head;
    Node tail;
    Map<String, Node> nmap;
    public AllOOneDataStructure() {
        head = new Node("");
        head.cnt = 0;
        tail = new Node("");
        tail.cnt = Integer.MAX_VALUE;
        head.next = tail;
        tail.pre = head;
        nmap = new HashMap<>();
    }

    public void inc(String key) {
        Node n = nmap.get(key);
        Node tn = null;
        if (n == null) {
            Node first = head.next;
            if (first.cnt == 1) {
                tn = first;
                tn.set.add(key);
                nmap.put(key, tn);
            } else {
                tn = new Node(key);
                tn.cnt = 1;
                head.next = tn;
                tn.pre = head;
                first.pre = tn;
                tn.next = first;
                nmap.put(key, tn);
            }
        } else {
            Node next = n.next;
            Node pre = n;
            if (n.set.size() == 1) {
                pre = n.pre;
            } else {
                n.set.remove(key);
            }
            if (next.cnt == n.cnt + 1) {
                tn = next;
                tn.set.add(key);
            } else {
                tn = new Node(key);
                tn.cnt =  n.cnt+1;
                tn.next = next;
                next.pre = tn;
            }

            nmap.put(key, tn);
            tn.pre = pre;
            pre.next = tn;
        }
    }

    public void dec(String key) {
        Node n = nmap.get(key);
        if (n.set.size()>1) {
            n.set.remove(key);
            Node pre = n.pre;
            if (pre.cnt+1==n.cnt) {
                if (pre == head) {
                    nmap.remove(key);
                } else {
                    nmap.put(key, pre);
                    pre.set.add(key);
                }
            } else {
                Node tn = new Node(key);
                tn.cnt = n.cnt-1;
                nmap.put(key, tn);
                pre.next  = tn;
                tn.pre = pre;
                n.pre = tn;
                tn.next = n;
            }
        } else {
            Node pre = n.pre;
            Node next = n.next;
            if (pre.cnt+1 == n.cnt) {
                if (pre== head) {
                    head.next = next;
                    next.pre = head;
                    nmap.remove(key);
                } else {
                    pre.set.add(key);
                    nmap.put(key, pre);
                    pre.next = next;
                    next.pre = pre;
                }
            } else {
                n.cnt = n.cnt-1;
            }
        }

    }

    public String getMaxKey() {
        if (head.next == tail) {
            return "";
        }
        return tail.pre.set.iterator().next();
    }

    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }
        return head.next.set.iterator().next();
    }

    static class Node  {
        int cnt;
        Set<String> set = new HashSet<>();
        Node next = null;
        Node pre = null;
        public Node(String key){
            set.add(key);
        }
    }

    public static void main(String[] args) {
        AllOOneDataStructure allOne = new AllOOneDataStructure();
        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey()); // return "hello"
        System.out.println(allOne.getMinKey()); // return "hello"
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey()); // return "hello"
        System.out.println(allOne.getMinKey()); // return "leet"


        AllOOneDataStructure t2 = new AllOOneDataStructure();
        t2.inc("a");
        t2.inc("b");
        t2.inc("b");
        t2.inc("c");
        t2.inc("c");
        t2.inc("c");
        t2.dec("b");
        t2.dec("b");
        System.out.println(t2.getMinKey()); // return "hello"
        t2.dec("a");
        System.out.println(t2.getMaxKey()); // return "hello"
        System.out.println(t2.getMinKey()); // return "leet"

        //["AllOne","inc","inc","inc","inc","inc","inc","dec", "dec","getMinKey","dec","getMaxKey","getMinKey"]
        //[[],["a"],["b"],["b"],["c"],["c"],["c"],["b"],["b"],[],["a"],[],[]]
        //[null,null,null,null,null,null,null,null,null,"a",null,"c","c"]


        /*
        ["AllOne","inc","inc","inc","dec","inc","inc","getMaxKey","dec","dec","dec","getMaxKey"]
        [[],["hello"],["world"],["hello"],["world"],["hello"],["leet"],[],["hello"],["hello"],["hello"],[]]
         */
        AllOOneDataStructure t3 = new AllOOneDataStructure();
        t3.inc("h");
        t3.inc("w");
        t3.inc("h");
        t3.dec("w");
        t3.inc("h");
        t3.inc("l");
        System.out.println(t3.getMaxKey()); // return "hello"
        t3.dec("h");
        t3.dec("h");
        t3.dec("h");
        System.out.println(t3.getMaxKey()); // return "leet"
    }
}
