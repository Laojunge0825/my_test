package com.shuke.my_test.data_capture.service.data_parse;


import com.shuke.my_test.data_capture.domain.Master;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 舒克、舒克
 * @date 2025/4/10 10:03
 * @description  并行处理服务（动态批次）
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ParallelDataProcessor {
    private final DataParseService01 dataParseService;
    private final ExecutorService dataParseThreadPool;

    /**
     * 动态并行处理数据
     * @param dataList 数据列表
     * @param desiredBatches 期望的批次数（根据实际情况自动调整）
     */
    public void dynamicParallelProcess(List<Master> dataList, int desiredBatches) {
        // 参数校验
        if (dataList == null || dataList.isEmpty()) {
            log.warn("数据列表为空，跳过处理");
            return;
        }

        if (desiredBatches <= 0) {
            log.warn("无效的批次数: {}，将使用默认值", desiredBatches);
            desiredBatches = 1; // 或根据CPU核心数设置默认值
        }

        int total = dataList.size();
        int actualBatches = Math.min(desiredBatches, total);
        int batchSize = (total + actualBatches - 1) / actualBatches; // 计算每批大小

        // 记录批次信息
        log.info("开始并行处理 {} 条数据，分为 {} 批，每批约 {} 条",
                total, actualBatches, batchSize);

        List<CompletableFuture<Void>> futures = IntStream.range(0, actualBatches)
                .mapToObj(i -> {
                    int from = i * batchSize;
                    int to = Math.min(from + batchSize, total);
                    List<Master> batch = dataList.subList(from, to);
                    return CompletableFuture.runAsync(
                            () -> batch.forEach(dataParseService::parseAndSaveData),
                            dataParseThreadPool
                    );
                })
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }
}
