# 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `uid` char(32) NOT NULL COMMENT '用户Id',
  `username` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
  `nick` varchar(64) DEFAULT '' COMMENT '用户别名, 展示名字',
  `email` varchar(64) DEFAULT '' COMMENT '电子邮箱',
  `mobile` varchar(16) DEFAULT '' COMMENT '手机号码',
  `sex` tinyint(8) DEFAULT 0 COMMENT '0 未知, 1 男, 2 女',
  `avatar` varchar(128) DEFAULT '' COMMENT '头像URL',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `user_username_IDX` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

# Todo 表
-- DROP TABLE IF EXISTS `todo`;
CREATE TABLE IF NOT EXISTS `todo` (
  `uid` char(32) NOT NULL COMMENT 'UID (唯一标识符)',
  `user_id` char(32) NOT NULL COMMENT '用户Id',
  `priority` tinyint(8) DEFAULT 0 COMMENT '优先级: 0: 未定义, 1-9: 优先级逐步降低',
  `percent` int(8) DEFAULT 0 COMMENT '待办事项的完成百分比',
  `seq` int(8) DEFAULT 0 COMMENT '修改次数, 创建时为: 0',
  `status` int(8) DEFAULT 0 COMMENT 'TODO状态: 0 NEEDS-ACTIO, 1 COMPLETED, 2 IN-PROCESS, 9 CANCELLED',
  `related` varchar(8) DEFAULT '' COMMENT '指定警报触发器相对于日历组件的开始或结束的关系: START | END',
  `clazz` varchar(16) DEFAULT 'PUBLIC' COMMENT '类别: "PUBLIC", "PRIVATE", "CONFIDENTIAL", iana-token, x-name ;默认为 PUBLIC',
  `geo` varchar(32) DEFAULT '' COMMENT 'GPS坐标',
  `dtstart` datetime DEFAULT NULL COMMENT '开始时间',
  `completed` datetime DEFAULT NULL COMMENT '完成时间',
  `due` datetime DEFAULT NULL COMMENT '到期时间',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `dtstamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修订的日期时间',
  `categories` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '类别',
  `location` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '地址信息',
  `organizer` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '组织者/拥有者',
  `summary` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '简介一般是标题',
  `url` varchar(256) DEFAULT '' COMMENT '关联的url',
  `description` longtext COLLATE utf8mb4_unicode_ci COMMENT '内容',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;