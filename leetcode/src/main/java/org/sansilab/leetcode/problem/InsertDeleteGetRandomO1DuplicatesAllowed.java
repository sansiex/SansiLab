package org.sansilab.leetcode.problem;

import java.util.*;

//https://leetcode.cn/problems/insert-delete-getrandom-o1-duplicates-allowed/
public class InsertDeleteGetRandomO1DuplicatesAllowed {

    ArrayList<Integer> list = new ArrayList<>(20000);
    Map<Integer, HashSet<Integer>> map = new HashMap<>();

    public InsertDeleteGetRandomO1DuplicatesAllowed() {

    }

    public boolean insert(int val) {
        list.add(val);
        boolean ret = !map.containsKey(val);
        HashSet<Integer> s = map.getOrDefault(val, new HashSet<>());
        s.add(list.size()-1);
        map.put(val, s);
        return ret;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        HashSet<Integer> l = map.get(val);
        int index = l.iterator().next();
        l.remove(index);
        if (l.size() == 0) {
            map.remove(val);
        }

        if (index == list.size() - 1) {
            list.remove(index);
            return true;
        }

        int last = list.get(list.size()-1);
        l = map.get(last);
        l.remove(list.size()-1);
        l.add(index);
        list.set(index, last);
        list.remove(list.size() - 1);
        return true;
    }

    public int getRandom() {
        int r = (int) (Math.random() * list.size());
        System.out.println(r);
        return list.get(r);
    }

    public static void main(String[] args) {
        InsertDeleteGetRandomO1DuplicatesAllowed obj = new InsertDeleteGetRandomO1DuplicatesAllowed();
        System.out.println(obj.insert(0));
        System.out.println(obj.insert(1));
        System.out.println(obj.remove(0));
        System.out.println(obj.insert(2));
        System.out.println(obj.remove(1));
        System.out.println(obj.getRandom());

        obj = new InsertDeleteGetRandomO1DuplicatesAllowed();
        System.out.println(obj.insert(1));
        System.out.println(obj.remove(1));
        System.out.println(obj.insert(1));
        System.out.println(obj.getRandom());

        obj = new InsertDeleteGetRandomO1DuplicatesAllowed();
        System.out.println(obj.insert(1));
        System.out.println(obj.insert(10));
        System.out.println(obj.insert(100));
        System.out.println(obj.insert(1000));
        Map<Integer, Integer> map1 = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            int v =  obj.getRandom();
            int c = map1.getOrDefault(v, 0);
            map1.put(v, c+1);
        }
        System.out.println(map1);
        System.out.println(obj.map);

    }

}
