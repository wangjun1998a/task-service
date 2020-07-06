package com.joinbright.taskservice.mapper.zentao;

import com.joinbright.taskservice.dto.ZenTaoUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author alin
 * @date 2020/7/6 10:07
 */

@Mapper
@Repository
public interface ZenTaoMapper {
    /**
     * 查询所有的禅道的用户数据
     * @return list
     */
    List<ZenTaoUserDTO> searchAllUser();
}
