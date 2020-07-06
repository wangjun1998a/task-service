package com.joinbright.taskservice.service.impl;

import com.joinbright.taskservice.dto.F1SysPersonDTO;
import com.joinbright.taskservice.dto.SystemUserIdDTO;
import com.joinbright.taskservice.dto.ZenTaoUserDTO;
import com.joinbright.taskservice.mapper.master.InformationMapper;
import com.joinbright.taskservice.mapper.master.ZenDaoMapper;
import com.joinbright.taskservice.mapper.second.SecondMapper;
import com.joinbright.taskservice.mapper.zentao.ZenTaoMapper;
import com.joinbright.taskservice.service.CustomService;
import com.joinbright.taskservice.util.PatternUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alin
 * @date 2020/7/6 10:15
 */

@Slf4j
@Service
public class CustomServiceImpl implements CustomService {

    @Autowired
    private SecondMapper secondMapper;
    @Autowired
    private ZenTaoMapper zenTaoMapper;
    @Autowired
    private InformationMapper informationMapper;
    @Autowired
    private ZenDaoMapper zenDaoMapper;
    @Autowired
    private PatternUtil patternUtil;


    /**
     * 插入全部的禅道数据
     *
     * @return 插入的数据量
     */
    @Override
    public String insertZenTaoUser() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> updateMap = new HashMap<>();
        Map<String, Object> deleteMap = new HashMap<>();
        Map<String, Object> masterDataMap = new HashMap<>();
        // 获取钉钉数据库中禅道的用户信息,放到map中后面对比
        List<SystemUserIdDTO> existUsers = zenDaoMapper.getAllUsers();
        if (!existUsers.isEmpty()) {
            for (SystemUserIdDTO existUser : existUsers) {
                masterDataMap.put(existUser.getUserId(), existUser.getEmail());
            }
        }
        // 查询禅道数据库获取全部的用户信息并放入到一个Map里
        List<ZenTaoUserDTO> users = zenTaoMapper.searchAllUser();
        if (!users.isEmpty()) {
            for (ZenTaoUserDTO user : users) {
                if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                    boolean isPattern = patternUtil.patternMail(user.getEmail());
                    // 邮箱格式匹配
                    if (isPattern) {
                        if (!masterDataMap.containsKey(user.getAccount()) && !masterDataMap.containsValue(user.getEmail())) {
                            // 键和值全不匹配证明这数据是不存在的 走新增
                            map.put(user.getAccount(), user);
                        } else if (masterDataMap.containsKey(user.getAccount()) && !masterDataMap.get(user.getAccount()).equals(user.getEmail())) {
                            // 键匹配但是值不匹配证明这数据发生改变的 走修改
                            updateMap.put(user.getAccount(), user);
                        } else if (!masterDataMap.containsKey(user.getAccount()) && masterDataMap.get(user.getAccount()).equals(user.getEmail())) {
                            // 键不匹配但是值匹配证明这数据是异常数据 走删除
                            deleteMap.put(user.getAccount(), user);
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("成功--");
        int isSuccess = 0;
        if (!map.isEmpty()) {
            isSuccess = zenDaoMapper.insertAllUser(map);
            sb.append("插入数据「 " + isSuccess + " 」条\t");
            isSuccess = 0;
        }
        if (!updateMap.isEmpty()) {
            isSuccess = zenDaoMapper.updateUser(updateMap);
            sb.append("更新数据「 " + isSuccess + " 」条\t");
            isSuccess = 0;
        }
        if (!deleteMap.isEmpty()) {
            isSuccess = zenDaoMapper.deleteUser(deleteMap);
            sb.append("删除数据「 " + isSuccess + " 」条\t");
        }
        log.info("禅道数据同步----" + sb.toString());
        return sb.toString();
    }

    /**
     * 插入F1信息平台的用户数据
     *
     * @return 插入的数据量
     */
    @Override
    public String insertInformationUsers() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> updateMap = new HashMap<>();
        Map<String, Object> deleteMap = new HashMap<>();
        // 查询钉钉对应数据库中信息平台的数据,并放到map中对比
        Map<String, Object> masterDataMap = new HashMap<>();
        List<SystemUserIdDTO> existUsers = informationMapper.getAllUsers("f1");
        if (!existUsers.isEmpty()) {
            for (SystemUserIdDTO existUser : existUsers) {
                masterDataMap.put(existUser.getUserId(), existUser.getEmail());
            }
        }
        // 查询信息平台的全部用户数据
        List<F1SysPersonDTO> users = secondMapper.searchAllF1Users();
        for (F1SysPersonDTO user : users) {
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                boolean isPattern = patternUtil.patternMail(user.getEmail());
                // 邮箱格式匹配
                if (isPattern) {
                    if (!masterDataMap.containsKey(user.getLogoId()) && !masterDataMap.containsValue(user.getEmail())) {
                        // 键和值全不匹配证明这数据是不存在的 走新增
                        map.put(user.getLogoId(), user);
                    } else if (masterDataMap.containsKey(user.getLogoId()) && !masterDataMap.get(user.getLogoId()).equals(user.getEmail())) {
                        // 键匹配但是值不匹配证明这数据发生改变的 走修改
                        updateMap.put(user.getLogoId(), user);
                    } else if (!masterDataMap.containsKey(user.getLogoId()) && masterDataMap.get(user.getLogoId()).equals(user.getEmail())) {
                        // 键不匹配但是值匹配证明这数据是异常数据 走删除
                        deleteMap.put(user.getLogoId(), user);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("成功--");
        int isSuccess = 0;
        if (!map.isEmpty()) {
            isSuccess = informationMapper.insertAllUser(map);
            sb.append("插入数据「 " + isSuccess + " 」条\t");
            isSuccess = 0;
        }
        if (!updateMap.isEmpty()) {
            isSuccess = informationMapper.updateUser(updateMap);
            sb.append("更新数据「 " + isSuccess + " 」条\t");
            isSuccess = 0;
        }
        if (!deleteMap.isEmpty()) {
            isSuccess = informationMapper.deleteUser(deleteMap);
            sb.append("删除数据「 " + isSuccess + " 」条\t");
        }
        log.info("信息平台数据同步----" + sb.toString());
        return sb.toString();
    }
}
