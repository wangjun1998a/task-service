package com.joinbright.taskservice.mapper.master;

import com.joinbright.taskservice.dto.SystemUserIdDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author alin
 * @date 2020/7/6 10:50
 */

@Mapper
@Repository
public interface ZenDaoMapper {


    /**
     * 获取所有的用户信息
     *
     * @return list
     */
    List<SystemUserIdDTO> getAllUsers();

    /**
     * 插入禅道的用户数据
     *
     * @param map map
     * @return 插入的数据条数
     */
    int insertAllUser(@Param("map") Map<String, Object> map);

    /**
     * 更新用户数据
     *
     * @param updateMap map
     * @return count
     */
    int updateUser(@Param("updateMap") Map<String, Object> updateMap);

    /**
     * 删除用户数据
     * @param deleteMap map
     * @return count
     */
    int deleteUser(@Param("deleteMap") Map<String, Object> deleteMap);
}
