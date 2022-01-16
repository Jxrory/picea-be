package com.jxrory.picea.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
    @TableId
    private String uid;

    /**
     * 用户名/登录名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码 salt
     */
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
    private Byte sex;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 当前用户的工作空间 Id
     */
    private String workspaceId;

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