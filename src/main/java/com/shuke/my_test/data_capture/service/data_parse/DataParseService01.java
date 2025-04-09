package com.shuke.my_test.data_capture.service.data_parse;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.shuke.my_test.data_capture.domain.DataMappingConfig;
import com.shuke.my_test.data_capture.domain.Master;
import com.shuke.my_test.data_capture.mapper.DataMappingConfigMapper;
import com.shuke.my_test.data_capture.mapper.DynamicTableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 舒克、舒克
 * @date 2025/4/9 10:29
 * @description
 */
@Service
@RequiredArgsConstructor
public class DataParseService01 {
    private final DataMappingConfigMapper configMapper;
    private final DynamicTableMapper dynamicTableMapper;

    public void parseAndSaveData(Master master) {
        // 确定协议类型
        String protocolType = master.getSlaveId() == 0 ? "iec104" : "modbus";

        // 解析JSON数据
        JSONObject dataJson = JSONUtil.parseObj(master.getData());

        // 按目标表分组收集数据
        Map<String, Map<String, Object>> tableDataMap = new LinkedHashMap<>();

        // 遍历所有点位
        for (Map.Entry<String, Object> entry : dataJson.entrySet()) {
            String pointAddress = entry.getKey();

            // 查询配置
            DataMappingConfig config = configMapper.findByPointAndProtocolAndDevice(
                    pointAddress, protocolType, master.getSlaveId());

            if (config != null) {
                tableDataMap.computeIfAbsent(config.getTargetTable(), k -> new HashMap<>())
                        .put(config.getTargetField(), entry.getValue());
            }


        }

        // 处理每组数据
        tableDataMap.forEach((tableName, fields) -> {
            fields.put("create_time", new Date());
            dynamicTableMapper.insertData(tableName, fields, master.getSlaveId());
        });
    }
}
