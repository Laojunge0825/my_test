package com.shuke.my_test.data_capture.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuke.my_test.data_capture.domain.DataMappingConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author 16422
* @description 针对表【data_mapping_config】的数据库操作Mapper
* @createDate 2025-04-09 09:53:41
*/
@Mapper
public interface DataMappingConfigMapper extends BaseMapper<DataMappingConfig> {

    /**
     * 根据点位地址、协议类型和设备ID查询数据映射配置
     *
     * @param pointAddress 点位地址
     * @param protocolType 协议类型
     * @param deviceId     设备ID
     * @return 数据映射配置
     */
    @Select("SELECT * FROM data_mapping_config WHERE point_address = #{pointAddress} " +
            "AND (protocol_type IS NULL OR protocol_type = #{protocolType}) " +
            "AND (device_id IS NULL OR device_id = #{deviceId})")
    DataMappingConfig findByPointAndProtocolAndDevice(
            @Param("pointAddress") String pointAddress,
            @Param("protocolType") String protocolType,
            @Param("deviceId") Integer deviceId);

}




