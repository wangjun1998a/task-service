package com.joinbright.taskservice.service;

/**
 * @author alin
 * @date 2020/7/6 10:14
 */

public interface CustomService {
    /**
     * 插入全部的禅道数据
     *
     * @return 插入的数据量
     */
    String insertZenTaoUser();

    /**
     * 插入F1信息平台的用户数据
     *
     * @return 插入的数据量
     */
    String insertInformationUsers();
}
