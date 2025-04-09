package com.shuke.my_test.data_capture.task;


import com.shuke.my_test.data_capture.service.data_parse.DataParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 舒克、舒克
 * @date 2025/4/8 16:35
 * @description  解析数据类
 */
@Component
public class DataParseSchedule {

    @Autowired
    private DataParseService dataParseService;
    /**
     * 每1分钟执行一次
     */
//    @Scheduled(cron = "0 0/1 * * * ?")
    public void dataParse() {
       dataParseService.parseAndStoreData();


    }
}
