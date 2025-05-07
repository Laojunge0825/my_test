package com.shuke.my_test.tree_lazy.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shuke.my_test.tree_lazy.domain.LabelType;
import com.shuke.my_test.tree_lazy.domain.vo.LabelTypeVO;
import com.shuke.my_test.tree_lazy.mapper.LabelTypeMapper;
import com.shuke.my_test.tree_lazy.service.LabelTypeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 舒克、舒克
 * @date 2025/5/6 17:03
 * @description
 */
@Service
public class LabelTypeServiceImpl implements LabelTypeService {

    @Resource
    private LabelTypeMapper labelTypeMapper;

    /**
     * 获取子节点并计算是否有下级
     */
    @Override
    public List<LabelTypeVO> getChildrenWithStatus(Integer parentId) {
        // 1. 查询直接子节点
        List<LabelType> children = labelTypeMapper.selectList(
                new QueryWrapper<LabelType>()
                        .eq("parent_id", parentId)
                        .orderByAsc("id")
        );

        // 2. 转换为VO并计算hasChildren
        return children.stream().map(entity -> {
            LabelTypeVO vo = new LabelTypeVO();
            BeanUtil.copyProperties(entity, vo);
            vo.setHasChildren(hasChildren(entity.getId()));
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 判断是否存在子节点（性能优化版）
     */
    private boolean hasChildren(Integer id) {
        return labelTypeMapper.exists(
                new QueryWrapper<LabelType>()
                        .eq("parent_id", id)
                        .last("LIMIT 1")
        );
    }
}
