package com.shuke.my_test.tree_lazy.controller;


import com.shuke.my_test.domain.ResponseResult;
import com.shuke.my_test.tree_lazy.domain.vo.LabelTypeVO;
import com.shuke.my_test.tree_lazy.service.LabelTypeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 舒克、舒克
 * @date 2025/5/6 17:00
 * @description
 */
@RestController
@RequestMapping("/api/tree")
public class LabelTypeController {

    @Resource
    private LabelTypeService labelTypeService;

    /**
     * 懒加载子节点
     * @param parentId 父节点ID (0表示根节点)
     */
    @GetMapping("/children/{parentId}")
    public ResponseResult<List<LabelTypeVO>> getChildren(
            @PathVariable Integer parentId) {

        List<LabelTypeVO> children = labelTypeService.getChildrenWithStatus(parentId);
        return ResponseResult.success(children);
    }

}
