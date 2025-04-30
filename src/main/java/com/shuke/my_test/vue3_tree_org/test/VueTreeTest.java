package com.shuke.my_test.vue3_tree_org.test;


import cn.hutool.json.JSONUtil;
import com.shuke.my_test.vue3_tree_org.domain.VueTreeNode;
import com.shuke.my_test.vue3_tree_org.utils.VueTreeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 舒克、舒克
 * @date 2025/4/29 15:41
 * @description
 */
public class VueTreeTest {
    public static void main(String[] args) {
        // 构建节点列表
        List<VueTreeNode> nodes = new ArrayList<>();
        nodes.add(new VueTreeNode(1, "A", 0));
        nodes.add(new VueTreeNode(2, "a", 1));
        nodes.add(new VueTreeNode(3, "b", 1));
        nodes.add(new VueTreeNode(4, "c", 1));
        nodes.add(new VueTreeNode(5, "a0", 2));
        nodes.add(new VueTreeNode(6, "a1", 2));
        nodes.add(new VueTreeNode(7, "a2", 2));
        nodes.add(new VueTreeNode(8, "b0", 3));
        nodes.add(new VueTreeNode(9, "b1", 3));
        nodes.add(new VueTreeNode(10, "c0", 4));
        nodes.add(new VueTreeNode(11, "c1", 4));
        nodes.add(new VueTreeNode(12, "a01", 5));
        nodes.add(new VueTreeNode(13, "a02", 5));
        nodes.add(new VueTreeNode(14, "a0101", 12));

        // 构建树
        VueTreeBuilder builder = new VueTreeBuilder();
        VueTreeNode root = builder.build(nodes);

        // 打印树结构
        printTree(root, 0);

        System.out.println(JSONUtil.toJsonStr(root));
    }

    private static void printTree(VueTreeNode node, int level) {
        // 根据层级缩进
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        System.out.println(indent.toString() + node.getLabel());
        // 递归打印子节点
        for (VueTreeNode child : node.getChildren()) {
            printTree(child, level + 1);
        }
    }
}
