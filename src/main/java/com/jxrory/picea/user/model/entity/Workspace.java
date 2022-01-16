package com.jxrory.picea.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 工作空间配置
 * @TableName workspace
 */
@TableName(value ="workspace")
@Data
public class Workspace implements Serializable {
    /**
     * 用户Id
     */
    @TableId
    private String uid;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 工作空间名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 最后更新时间
     */
    private Date updated;

    /**
     * 前端配置使用
     */
    private String config;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}