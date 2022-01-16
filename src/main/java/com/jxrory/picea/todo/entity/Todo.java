package com.jxrory.picea.todo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 任务表
 * @TableName todo
 */
@TableName(value ="todo")
@Data
public class Todo implements Serializable {
    /**
     * UID (唯一标识符)
     */
    @TableId
    private String uid;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 优先级: 0: 未定义, 1-9: 优先级逐步降低
     */
    private Byte priority;

    /**
     * 待办事项的完成百分比
     */
    private Integer percent;

    /**
     * 修改次数, 创建时为: 0
     */
    private Integer seq;

    /**
     * TODO状态: 0 NEEDS-ACTIO, 1 COMPLETED, 2 IN-PROCESS, 9 CANCELLED
     */
    private Integer status;

    /**
     * 指定警报触发器相对于日历组件的开始或结束的关系: START | END
     */
    private String related;

    /**
     * 类别: "PUBLIC", "PRIVATE", "CONFIDENTIAL", iana-token, x-name ;默认为 PUBLIC
     */
    private String clazz;

    /**
     * GPS坐标
     */
    private String geo;

    /**
     * 工作空间Id
     */
    private String workspaceId;

    /**
     * 开始时间
     */
    private Date dtstart;

    /**
     * 完成时间
     */
    private Date completed;

    /**
     * 到期时间
     */
    private Date due;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 最后更新时间
     */
    private Date updated;

    /**
     * 最后修订的日期时间
     */
    private Date dtstamp;

    /**
     * 类别
     */
    private String categories;

    /**
     * 地址信息
     */
    private String location;

    /**
     * 组织者/拥有者
     */
    private String organizer;

    /**
     * 简介一般是标题
     */
    private String summary;

    /**
     * 关联的url
     */
    private String url;

    /**
     * 内容
     */
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}