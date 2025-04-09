package com.shuke.my_test.data_capture.task;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import com.shuke.my_test.data_capture.domain.Master;
import com.shuke.my_test.data_capture.mapper.MasterMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


/**
 * @author 舒克、舒克
 * @date 2025/4/8 16:02
 * @description
 */
@Component
@Slf4j
public class DataCaptureSchedule {

    @Autowired
    private MasterMapper masterMapper;

    // 点位集合
    private static final List<String> POINT_CODES = Arrays.asList("A001", "A002", "A003", "A004", "A005", "A006");
    // slave_id集合
    private static final List<Integer> SLAVE_IDS = Arrays.asList(0, 1, 2);

    /**
     * 每1分钟执行一次
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void dataCapture() {
        // 随机生成3-5条数据
        int count = RandomUtil.randomInt(3, 6);
        generateTestData(1);


    }

    /**
     * 生成指定数量的测试数据
     */
    private void generateTestData(int count) {
        for (int i = 0; i < count; i++) {
            try {
                // 随机选择slave_id
                Integer slaveId = RandomUtil.randomEle(SLAVE_IDS);

                // 随机选择3个不重复的点位
                List<String> selectedPoints = RandomUtil.randomEleList(POINT_CODES, 3);

                // 生成点位数据
                JSONObject data = new JSONObject();
                selectedPoints.forEach(point -> {
                    // 根据点位类型生成不同范围的值
                    if (point.equals("A001") || point.equals("A003") || point.equals("A005")) {
                        // 电流、电压、功率等 (50-200)
                        data.set(point, RandomUtil.randomInt(50, 201));
                    } else {
                        // 数量类 (1-20)
                        data.set(point, RandomUtil.randomInt(1, 21));
                    }
                });

                // 创建并保存测试数据
                Master master = new Master();
                master.setSlaveId(slaveId);
                master.setData(data.toString());
                masterMapper.insert(master);

                log.info("生成模拟数据: slave_id={}, data={}", slaveId, data);
            } catch (Exception e) {
                log.error("生成模拟数据失败", e);
            }
        }
        log.info("本次共生成{}条模拟数据", count);
    }
}
