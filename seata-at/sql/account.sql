CREATE DATABASE IF NOT EXISTS seata_account DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_bin;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '用户唯一标识',
    `username`    varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
    `password`    varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
    `create_time` datetime                        NOT NULL COMMENT '创建时间',
    `update_time` datetime                        NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户';


DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20) NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int(11) NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    `ext`           varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

