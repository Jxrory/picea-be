package com.jxrory.picea.user.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 用户Id
     */
    private String uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户别名, 展示名字
     */
    private String nick;

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
    private String avator;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 最后更新时间
     */
    private Date updated;

    private static final long serialVersionUID = 1L;
}