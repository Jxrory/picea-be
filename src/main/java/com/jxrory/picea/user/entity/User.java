package com.jxrory.picea.user.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jxrory.picea.model.enums.GenderEnum;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户Id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String uid;

    /**
     * 用户名/登录名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 密码 salt, 不能传到前端
     */
    @JsonIgnore
    private String pwSalt;

    /**
     * 用户别名, 展示名字
     */
    private String nickname;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 0 未知, 1 男, 2 女
     */
    private GenderEnum sex;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 最后更新时间
     */
    private Date updated;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}