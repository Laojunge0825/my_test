package com.shuke.my_test.vue3_tree_org.controller;


import cn.hutool.core.lang.tree.TreeBuilder;
import com.shuke.my_test.domain.ResponseResult;
import com.shuke.my_test.vue3_tree_org.domain.VueTreeNode;
import com.shuke.my_test.vue3_tree_org.service.TreeService;
import com.shuke.my_test.vue3_tree_org.utils.VueTreeBuilder;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 舒克、舒克
 * @date 2025/4/30 10:07
 * @description
 */
@RestController
@RequestMapping("/api/org-tree")
public class OrgTreeController {

    @Resource
    private TreeService treeService;

    private List<VueTreeNode> mockDataFromDatabase() {
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
        return nodes;
    }

    @GetMapping("/structure")
    public ResponseResult<VueTreeNode> getOrgStructure() {
        // 模拟数据库查询结果
        List<VueTreeNode> nodes = mockDataFromDatabase();

        // 构建树结构
        return ResponseResult.success(treeService.build(nodes));
    }
}
