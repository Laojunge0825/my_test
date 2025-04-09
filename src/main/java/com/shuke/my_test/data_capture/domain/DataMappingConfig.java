package com.shuke.my_test.data_capture.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName data_mapping_config
 */
@TableName(value ="data_mapping_config")
@Data
public class DataMappingConfig implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 点位地址
     */
    private String pointAddress;

    /**
     * 目标表名
     */
    private String targetTable;

    /**
     * 目标字段名
     */
    private String targetField;

    /**
     * 协议类型(modbus/iec104)
     */
    private String protocolType;

    /**
     * 设备ID(0表示所有设备)
     */
    private Integer deviceId;

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
        DataMappingConfig other = (DataMappingConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPointAddress() == null ? other.getPointAddress() == null : this.getPointAddress().equals(other.getPointAddress()))
            && (this.getTargetTable() == null ? other.getTargetTable() == null : this.getTargetTable().equals(other.getTargetTable()))
            && (this.getTargetField() == null ? other.getTargetField() == null : this.getTargetField().equals(other.getTargetField()))
            && (this.getProtocolType() == null ? other.getProtocolType() == null : this.getProtocolType().equals(other.getProtocolType()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPointAddress() == null) ? 0 : getPointAddress().hashCode());
        result = prime * result + ((getTargetTable() == null) ? 0 : getTargetTable().hashCode());
        result = prime * result + ((getTargetField() == null) ? 0 : getTargetField().hashCode());
        result = prime * result + ((getProtocolType() == null) ? 0 : getProtocolType().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pointAddress=").append(pointAddress);
        sb.append(", targetTable=").append(targetTable);
        sb.append(", targetField=").append(targetField);
        sb.append(", protocolType=").append(protocolType);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}