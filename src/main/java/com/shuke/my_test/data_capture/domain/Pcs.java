package com.shuke.my_test.data_capture.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName pcs
 */
@TableName(value ="pcs")
@Data
public class Pcs implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Integer slaveId;

    /**
     * 
     */
    private Integer batteryNum;

    /**
     * 
     */
    private Integer groupNum;

    /**
     * 
     */
    private Integer clusterNum;

    /**
     * 
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Pcs other = (Pcs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSlaveId() == null ? other.getSlaveId() == null : this.getSlaveId().equals(other.getSlaveId()))
            && (this.getBatteryNum() == null ? other.getBatteryNum() == null : this.getBatteryNum().equals(other.getBatteryNum()))
            && (this.getGroupNum() == null ? other.getGroupNum() == null : this.getGroupNum().equals(other.getGroupNum()))
            && (this.getClusterNum() == null ? other.getClusterNum() == null : this.getClusterNum().equals(other.getClusterNum()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSlaveId() == null) ? 0 : getSlaveId().hashCode());
        result = prime * result + ((getBatteryNum() == null) ? 0 : getBatteryNum().hashCode());
        result = prime * result + ((getGroupNum() == null) ? 0 : getGroupNum().hashCode());
        result = prime * result + ((getClusterNum() == null) ? 0 : getClusterNum().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", slaveId=").append(slaveId);
        sb.append(", batteryNum=").append(batteryNum);
        sb.append(", groupNum=").append(groupNum);
        sb.append(", clusterNum=").append(clusterNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}