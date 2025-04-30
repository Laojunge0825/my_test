package com.shuke.my_test.vue3_tree_org.service;


import com.shuke.my_test.vue3_tree_org.domain.VueTreeNode;

import java.util.List;

/**
 * @author 舒克、舒克
 * @date 2025/4/30 10:08
 * @description
 */
public interface TreeService {

    /**
     * 构建树结构
     * @param nodes 节点列表
     * @return 根节点
     */
    VueTreeNode build(List<VueTreeNode> nodes);
}
