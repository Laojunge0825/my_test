package com.shuke.my_test.vue3_tree_org.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 舒克、舒克
 * @date 2025/4/29 14:51
 * @description  适配 vue3-tree-org 组件的树形结构
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VueTreeNode implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 父节点id
     */
    @JsonProperty("pid")
    private Integer parentId;

    /**
     * 节点是否展开
     */
    private Boolean expand;

    /**
     * 子节点
     */
    private List<VueTreeNode> children = new ArrayList<>();

    /**
     * 自定义样式
     */
    private Map<String, Object> style;  // 自定义样式

    /**
     * 节点是否禁用
     */
    private Boolean disabled;          // 禁用节点操作

    /**
     * 禁止拖拽
     */
    private Boolean noDragging;        // 禁止拖拽

    @Serial
    private static final long serialVersionUID = 1L;

    public VueTreeNode(Integer id, String label, Integer parentId) {
        this(id, label, parentId, false, new ArrayList<>(), null, false, false);
    }
}
