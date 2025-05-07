package com.shuke.my_test.tree_lazy.service;


import com.shuke.my_test.tree_lazy.domain.vo.LabelTypeVO;

import java.util.List;

/**
 * @author 舒克、舒克
 * @date 2025/5/6 17:02
 * @description
 */
public interface LabelTypeService {
    List<LabelTypeVO> getChildrenWithStatus(Integer parentId);
}
