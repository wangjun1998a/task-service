package com.joinbright.taskservice.mapper.second;

import com.joinbright.taskservice.dto.F1SysPersonDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author alin
 * @date 2020/7/6 10:08
 */

@Mapper
@Repository
public interface SecondMapper {

    /**
     * 获取F1全部的用户数据
     *
     * @return not defined
     */
    List<F1SysPersonDTO> searchAllF1Users();
}
