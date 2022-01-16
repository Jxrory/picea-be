# picea 库的 DDL
use picea;

# 用户表
DROP TABLE IF EXISTS `user`;
create table user
(
    uid         char(32)                               not null comment '用户Id',
    username    varchar(64)  default ''                not null comment '用户名/登录名',
    password    varchar(128) default ''                not null comment '密码',
    pw_salt     char(32)     default ''                not null comment '密码 salt',
    nickname    varchar(64)  default ''                null comment '用户别名, 展示名字',
    email       varchar(64)  default ''                null comment '电子邮箱',
    mobile      varchar(16)  default ''                null comment '手机号码',
    sex         tinyint(8)   default 0                 null comment '0 未知, 1 男, 2 女',
    avatar      varchar(128) default ''                null comment '头像URL',
    workspace_id varchar(32)  default ''                null comment '当前用户的工作空间 Id',
    created     datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    updated     datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '最后更新时间',
    primary key (uid),
    UNIQUE KEY `IDX_USER_USERNAME` (`username`)
)
    comment '用户表' engine = InnoDB
                  collate = utf8mb4_unicode_ci;


# 任务表
DROP TABLE IF EXISTS `todo`;
create table todo
(
    uid         char(32)                                                          not null comment 'UID (唯一标识符)',
    user_id     char(32)                                                          not null comment '用户Id',
    priority    tinyint(8)                              default 0                 null comment '优先级: 0: 未定义, 1-9: 优先级逐步降低',
    percent     int(8)                                  default 0                 null comment '待办事项的完成百分比',
    seq         int(8)                                  default 0                 null comment '修改次数, 创建时为: 0',
    status      int(8)                                  default 0                 null comment 'TODO状态: 0 NEEDS-ACTIO, 1 COMPLETED, 2 IN-PROCESS, 9 CANCELLED',
    related     varchar(8)                              default ''                null comment '指定警报触发器相对于日历组件的开始或结束的关系: START | END',
    clazz       varchar(16)                             default 'PUBLIC'          null comment '类别: "PUBLIC", "PRIVATE", "CONFIDENTIAL", iana-token, x-name ;默认为 PUBLIC',
    geo         varchar(32)                             default ''                null comment 'GPS坐标',
    workspace_id varchar(32)                             default ''                null comment '工作空间Id',
    dtstart     datetime                                default NULL              null comment '开始时间',
    completed   datetime                                default NULL              null comment '完成时间',
    due         datetime                                default NULL              null comment '到期时间',
    created     datetime                                default CURRENT_TIMESTAMP not null comment '创建时间',
    updated     datetime                                default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '最后更新时间',
    dtstamp     datetime                                default CURRENT_TIMESTAMP not null comment '最后修订的日期时间',
    categories  varchar(128) COLLATE utf8mb4_unicode_ci default ''                null comment '类别',
    location    varchar(128) COLLATE utf8mb4_unicode_ci default ''                null comment '地址信息',
    organizer   varchar(128) COLLATE utf8mb4_unicode_ci default ''                null comment '组织者/拥有者',
    summary     varchar(128) COLLATE utf8mb4_unicode_ci default ''                null comment '简介一般是标题',
    url         varchar(256)                            default ''                null comment '关联的url',
    description longtext COLLATE utf8mb4_unicode_ci                               null comment '内容',
    primary key (uid)
)
    comment '任务表' engine = InnoDB
                  collate = utf8mb4_unicode_ci;


# 用户表工作空间配置表
DROP TABLE IF EXISTS `workspace`;
create table workspace
(
    uid     char(32)                               not null comment '用户Id',
    user_id char(32)                               not null comment '用户Id',
    name    varchar(128) default ''                null comment '工作空间名称',
    created datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    updated datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '最后更新时间',
    config  text         default NULL              null comment '前端配置使用',
    primary key (uid),
    KEY `IDX_USER_ID_WORKSPACE` (user_id)
)
    comment '工作空间配置' engine = InnoDB
                     collate = utf8mb4_unicode_ci;
