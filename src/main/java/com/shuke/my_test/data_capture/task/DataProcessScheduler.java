package com.shuke.my_test.data_capture.task;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuke.my_test.data_capture.domain.Master;
import com.shuke.my_test.data_capture.mapper.MasterMapper;
import com.shuke.my_test.data_capture.service.data_parse.DataParseService01;
import com.shuke.my_test.data_capture.service.data_parse.ParallelDataProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 舒克、舒克
 * @date 2025/4/9 10:10
 * @description
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataProcessScheduler {
    private final MasterMapper masterMapper;
    private final DataParseService01 dataParseService;
    private final ParallelDataProcessor parallelDataProcessor;

    /**
     * 每1分钟执行一次
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void processUnparsedData() {
        // 查询未处理的数据
        List<Master> unprocessedData = masterMapper.selectList(
                new LambdaQueryWrapper<Master>()
                        .eq(Master::getStatus,0));

        // 分批次处理数据
        parallelDataProcessor.dynamicParallelProcess(unprocessedData, 2);

//        // 处理数据
//        unprocessedData.forEach(data -> {
//            try {
//                dataParseService.parseAndSaveData(data);
//                // 标记为已处理
//                data.setStatus(1);
//                masterMapper.updateById(data);
//            } catch (Exception e) {
//                log.error("处理数据失败, id: {}", data.getId(), e);
//            }
//        });
    }
}