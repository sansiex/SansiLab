package org.sansilab.leetcode.parser;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.sansilab.leetcode.structure.TreeNode;
import org.sansilab.leetcode.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Map;

public class BinaryTreeParser implements ParamParser {


    public BinaryTreeParser() {
    }

    @Override
    public Object parse(String str) {
        JsonArray arr = JsonUtils.parseArray(str);
        Map<Integer, TreeNode> map = Maps.newHashMap();
        for (int i = 0; i < arr.size(); i++) {
            JsonElement ele = arr.get(i);
            if (ele.isJsonNull()) {
                continue;
            }
            JsonPrimitive prim = (JsonPrimitive) ele;
            if (prim.isJsonNull()) {
                continue;
            }
            int val = prim.getAsInt();
            TreeNode node = new TreeNode(val);
            map.put(i, node);

            if (i!=0) {
                int parent = (i-1)/2;
                TreeNode n = map.get(parent);

                if (i%2 == 1) {
                    n.left = node;
                } else {
                    n.right = node;
                }
            }
        }
        return map.get(0);
    }

    @Override
    public Class getType() {
        return TreeNode.class;
    }
}
