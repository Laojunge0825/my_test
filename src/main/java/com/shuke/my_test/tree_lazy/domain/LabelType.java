package com.shuke.my_test.tree_lazy.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 舒克、舒克
 * @date 2025/5/6 16:58
 * @description
 */
@Data
@TableName(value = "t_label_type")
public class LabelType {
    /**
     * 流水
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标签分类名称
     */
    private String name;

    /**
     * 数据库的设备名称
     */
    private String deviceName;

    /**
     * 模型id
     */
    private Integer modelId;

    /**
     * 设备编号(父设备下的第几个设备)
     */
    private Integer deviceNo;

    /**
     * 设备编码
     */
    private String code;

    /**
     * 标签分类路径，从根目录一直到当前节点的所有节点id的组合，如 /21/23/234
     */
    private String fullName;

    /**
     * 上级标签id
     */
    private Integer parentId;

    /**
     * 状态（0失效，1生效）
     */
    private Integer status;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;
}
