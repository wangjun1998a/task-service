package com.joinbright.taskservice.mapper.master;

import com.joinbright.taskservice.dto.SystemUserIdDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author alin
 * @date 2020/7/6 10:39
 */

@Mapper
@Repository
public interface InformationMapper {
    /**
     * 获取所有的信息平台的用户信息
     *
     * @param systemName f1
     * @return list
     */
    List<SystemUserIdDTO> getAllUsers(@Param("systemName") String systemName);
    /**
     * 插入信息平台的全部用户数据
     *
     * @param map allData
     * @return 插入数据的条数
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
     *
     * @param deleteMap map
     * @return count
     */
    int deleteUser(@Param("deleteMap") Map<String, Object> deleteMap);
}
