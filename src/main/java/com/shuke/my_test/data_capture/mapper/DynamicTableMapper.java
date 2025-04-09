package com.shuke.my_test.data_capture.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.Map;

/**
 * @author 舒克、舒克
 * @date 2025/4/9 10:31
 * @description  动态解析数据表Mapper
 */
@Mapper
public interface DynamicTableMapper {
    @Insert({
            "<script>",
            "INSERT INTO ${tableName} (slave_id, ",
            "<foreach collection='fields.keySet()' item='field' separator=','>",
            "${field}",
            "</foreach>",
            ") VALUES (#{slaveId}, ",
            "<foreach collection='fields.values()' item='value' separator=','>",
            "#{value}",
            "</foreach>",
            ") ",
            "</script>"
    })
    void insertData(@Param("tableName") String tableName,
                    @Param("fields") Map<String, Object> fields,
                    @Param("slaveId") Integer slaveId);
}