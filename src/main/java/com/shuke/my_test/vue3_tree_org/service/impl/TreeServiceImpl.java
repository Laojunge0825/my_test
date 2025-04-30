package com.shuke.my_test.vue3_tree_org.service.impl;


import com.shuke.my_test.vue3_tree_org.domain.VueTreeNode;
import com.shuke.my_test.vue3_tree_org.service.TreeService;
import com.shuke.my_test.vue3_tree_org.utils.VueTreeBuilder;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 舒克、舒克
 * @date 2025/4/30 10:09
 * @description
 */
@Service
public class TreeServiceImpl implements TreeService {
    @Resource
    private VueTreeBuilder vueTreeBuilder;

    @Override
    public VueTreeNode build(List<VueTreeNode> nodes) {

        return vueTreeBuilder.build(nodes);
    }
}
