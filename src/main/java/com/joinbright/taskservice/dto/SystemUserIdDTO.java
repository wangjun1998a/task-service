package com.joinbright.taskservice.dto;

import lombok.Data;

import java.util.Date;

/**
 * 数据库传输对象
 *
 * @author alin
 * @date 2020年6月3日 14:14:31
 */
@Data
public class SystemUserIdDTO {
    private String guid;
    private String email;
    private String systemName;
    private String userId;
    private Date createTime;
    private Date updateTime;
    private String remark;
}
