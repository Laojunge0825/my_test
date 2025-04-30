package com.shuke.my_test.vue3_tree_org.utils;


import com.shuke.my_test.vue3_tree_org.domain.VueTreeNode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 舒克、舒克
 * @date 2025/4/29 14:55
 * @description 构建树结构：通过Map来存储每个父节点对应的子节点列表，从而高效构建树。
 */
@Component
public class VueTreeBuilder {

    public VueTreeNode build(List<VueTreeNode> nodes) {
        Map<Integer, VueTreeNode> nodeMap = new HashMap<>();
        VueTreeNode root = null;

        // 第一次遍历：缓存所有节点
        for (VueTreeNode node : nodes) {
            nodeMap.put(node.getId(), node);
            if (node.getParentId() == 0) {
                root = node;
            }
        }

        // 第二次遍历：构建父子关系
        for (VueTreeNode node : nodes) {
            if (node.getParentId() != 0) {
                VueTreeNode parent = nodeMap.get(node.getParentId());
                if (parent != null) {
                    parent.getChildren().add(node);
                }
            }
        }

        return root;
    }
}
