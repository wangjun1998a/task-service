package com.joinbright.taskservice.service.impl;

import com.joinbright.taskservice.service.CustomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author alin
 * @date 2020/7/6 10:05
 */
@Slf4j
@Service
public class TaskServiceImpl {
    @Autowired
    private CustomService customService;

    @Scheduled(cron = "0 0/5 * * * *")
    public void f1InformationTask() {
        long start = System.currentTimeMillis();
        log.info("同步信息平台用户数据...");
        log.info("当前时间: " + new Date());
        customService.insertInformationUsers();
        long end = System.currentTimeMillis();
        log.info("执行耗时: [" + (end - start) + "] ms");
    }

    @Scheduled(cron = "0 0/5 * * * *")
    public void zenTaoTask() {
        long start = System.currentTimeMillis();
        log.info("同步禅道用户数据...");
        log.info("当前时间: " + new Date());
        customService.insertZenTaoUser();
        long end = System.currentTimeMillis();
        log.info("执行耗时: [" + (end - start) + "] ms");
    }
}
