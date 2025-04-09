package com.shuke.my_test.data_capture.service.data_parse;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuke.my_test.data_capture.domain.Bms;
import com.shuke.my_test.data_capture.domain.Master;
import com.shuke.my_test.data_capture.domain.Pcs;
import com.shuke.my_test.data_capture.mapper.BmsMapper;
import com.shuke.my_test.data_capture.mapper.MasterMapper;
import com.shuke.my_test.data_capture.mapper.PcsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 舒克、舒克
 * @date 2025/4/8 15:57
 * @description 
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataParseService {
    private final MasterMapper masterMapper;
    private final BmsMapper bmsMapper;
    private final PcsMapper pcsMapper;

    /**
     * 解析并存储数据
     */
    public void parseAndStoreData() {
        // 1. 查询所有未处理的原始数据
        LambdaQueryWrapper<Master> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Master::getStatus, 0);
        List<Master> rawDataList = masterMapper.selectList(queryWrapper);

        for (Master rawData : rawDataList) {
            try {
                // 2. 解析JSON数据
                JSONObject dataJson = JSONUtil.parseObj(rawData.getData());

                // 3. 根据协议类型处理数据
                if (rawData.getSlaveId() == 0) {
                    // IEC104协议数据处理
                    processIec104Data(rawData.getSlaveId(), dataJson);
                } else {
                    // Modbus协议数据处理
                    processModbusData(rawData.getSlaveId(), dataJson);
                }

                // 4. 标记数据为已处理(可选)
                 rawData.setStatus(1);
                 masterMapper.updateById(rawData);

            } catch (Exception e) {
                log.error("解析数据失败, id: {}", rawData.getId(), e);
            }
        }
    }

    /**
     * 处理IEC104协议数据
     */
    private void processIec104Data(Integer slaveId, JSONObject dataJson) {
        // 与Modbus处理逻辑相同，可以根据实际需求调整
        processModbusData(slaveId, dataJson);
    }

    /**
     * 处理Modbus协议数据
     */
    private void processModbusData(Integer slaveId, JSONObject dataJson) {
        // 创建BMS和PCS对象
        Bms bms = new Bms();
        Pcs pcs = new Pcs();

        bms.setSlaveId(slaveId);
        pcs.setSlaveId(slaveId);

        // 遍历所有数据点
        for (Map.Entry<String, Object> entry : dataJson.entrySet()) {
            String point = entry.getKey();
            Object value = entry.getValue();

            switch (point) {
                case "A001":
                    bms.setI(Integer.valueOf(value.toString()));
                    break;
                case "A002":
                    pcs.setBatteryNum(Integer.valueOf(value.toString()));
                    break;
                case "A003":
                    bms.setVol(Integer.valueOf(value.toString()));
                    break;
                case "A004":
                    pcs.setGroupNum(Integer.valueOf(value.toString()));
                    break;
                case "A005":
                    bms.setPower(Integer.valueOf(value.toString()));
                    break;
                case "A006":
                    pcs.setClusterNum(Integer.valueOf(value.toString()));
                    break;
                default:
                    log.warn("未知点位地址: {}", point);
            }
        }

        // 保存数据
        if (bms.getI() != null || bms.getVol() != null || bms.getPower() != null) {
            bmsMapper.insert(bms);
        }

        if (pcs.getBatteryNum() != null || pcs.getGroupNum() != null || pcs.getClusterNum() != null) {
            pcsMapper.insert(pcs);
        }
    }
}
