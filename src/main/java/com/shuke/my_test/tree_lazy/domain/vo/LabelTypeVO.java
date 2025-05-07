package com.shuke.my_test.tree_lazy.domain.vo;


import lombok.Data;

import java.util.List;

/**
 * @author 舒克、舒克
 * @date 2025/5/7 09:35
 * @description
 */
@Data
public class LabelTypeVO {
    private Integer id;

    private String name;

    private Integer parentId;

    private List<LabelTypeVO> children;

    private boolean hasChildren;
}
