package org.sansilab.leetcode.problem;

import org.sansilab.leetcode.base.Operation;
import org.sansilab.leetcode.parser.IntParser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sansi
 * @date 2022-09-11
 * https://leetcode.cn/problems/lfu-cache/
 */
public class LFUCache extends AbstractDataStructure {

    public static void main(String[] args) {
        LFUCache.runTest(LFUCache.class, () -> {
            addTestCase("[\"LFUCache\",\"put\",\"put\",\"put\",\"put\",\"put\",\"get\",\"put\",\"get\",\"get\",\"put\",\"get\",\"put\",\"put\",\"put\",\"get\",\"put\",\"get\",\"get\",\"get\",\"get\",\"put\",\"put\",\"get\",\"get\",\"get\",\"put\",\"put\",\"get\",\"put\",\"get\",\"put\",\"get\",\"get\",\"get\",\"put\",\"put\",\"put\",\"get\",\"put\",\"get\",\"get\",\"put\",\"put\",\"get\",\"put\",\"put\",\"put\",\"put\",\"get\",\"put\",\"put\",\"get\",\"put\",\"put\",\"get\",\"put\",\"put\",\"put\",\"put\",\"put\",\"get\",\"put\",\"put\",\"get\",\"put\",\"get\",\"get\",\"get\",\"put\",\"get\",\"get\",\"put\",\"put\",\"put\",\"put\",\"get\",\"put\",\"put\",\"put\",\"put\",\"get\",\"get\",\"get\",\"put\",\"put\",\"put\",\"get\",\"put\",\"put\",\"put\",\"get\",\"put\",\"put\",\"put\",\"get\",\"get\",\"get\",\"put\",\"put\",\"put\",\"put\",\"get\",\"put\",\"put\",\"put\",\"put\",\"put\",\"put\",\"put\"]",
                            "[[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]",
                    "[null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,-1,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null]");

            addTestCase("[\"LFUCache\", \"put\", \"put\", \"get\", \"put\", \"get\", \"get\", \"put\", \"get\", \"get\", \"get\"]",
                    "[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]",
                    "[null, null, null, 1, null, -1, 3, null, -1, 3, 4]");
        });
    }

    Map<Integer, Node> map;
    int cap;
    Freq head;
    Freq tail;

    @Operation("LFUCache")
    public LFUCache(int capacity) {
        map  = new HashMap<>();
        cap = capacity;
        head = new Freq(0);
        tail = new Freq(Integer.MAX_VALUE);
        head.n = tail;
        tail.p = head;
    }

    @Operation(value = "get")
    public int get(int key) {
        if (map.containsKey(key)) {
            incFreq(key);
            return map.get(key).value;
        }
        return -1;
    }

    @Operation(value = "put")
    public void put(int key, int value) {
        if (cap <= 0) {
            return;
        }

        Node n;
        if (map.containsKey(key)) {
            n = map.get(key);
            n.value = value;
            incFreq(key);
        } else {
            if (map.size() == cap) {
                Freq lfu = head.n;
                if (lfu != tail) {
                    Node firstNode = lfu.fh.n;
                    lfu.remove(firstNode);
                    map.remove(firstNode.key);
                }
            }

            n = new Node(key, value);
            Freq first = head.n;
            if (first.freq > 1) {
                Freq newf = new Freq(1);
                newf.p = head;
                newf.n = first;
                head.n = newf;
                first.p = newf;
                newf.add(n);
            } else {
                first.add(n);
            }
            map.put(key, n);
        }
    }

    private Node incFreq(int key) {
        Node t = map.get(key);
        Freq f = t.freq;
        Node n = new Node(key, t.value);
        if (f.n == tail || f.n.freq > f.freq+1) {
            Freq newf= new Freq(f.freq+1);
            newf.n = f.n;
            newf.p = f;
            f.n = newf;
            newf.n.p = newf;
            newf.add(n);
            f.remove(t);
        } else {
            f.n.add(n);
            f.remove(t);
        }
        map.put(key, n);
        return n;
    }

    static class Node {
        int key;
        int value;
        Node p;
        Node n;
        Freq freq;

        public Node(int k,  int v) {
            key  = k;
            value = v;
        }
    }

    static class Freq {
        Node fh;
        Node ft;
        int freq;
        Freq n;
        Freq p;

        public Freq(int f) {
            freq = f;
            fh = new Node(Integer.MIN_VALUE, 0);
            ft = new Node(Integer.MAX_VALUE, 0);
            fh.n = ft;
            ft.p = fh;
        }

        public void add(Node node) {
            node.n = ft;
            node.p = ft.p;
            ft.p = node;
            node.p.n = node;
            node.freq = this;
        }

        public void remove(Node node) {
            if (node.p == fh && node.n == ft) {
                n.p = p;
                p.n = n;
            } else {
                node.p.n = node.n;
                node.n.p = node.p;
            }
        }
    }
}
