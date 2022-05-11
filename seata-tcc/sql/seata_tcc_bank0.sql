CREATE
DATABASE IF NOT EXISTS seata_tcc_bank0 DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_bin;


-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account`
(
    `id`             int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account_id`     varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '账户标识',
    `amount`         decimal(20, 2)                  NOT NULL DEFAULT '0.00' COMMENT '金额',
    `freezed_amount` decimal(20, 2)                  NOT NULL DEFAULT '0.00' COMMENT '冻结金额',
    `create_time`    datetime                        NOT NULL COMMENT '创建时间',
    `update_time`    datetime                        NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_accout` (`account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='账户表';

-- ----------------------------
-- Table structure for t_transfer_serial
-- ----------------------------
DROP TABLE IF EXISTS `t_transfer_serial`;
CREATE TABLE `t_transfer_serial`
(
    `id`                     bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `transfer_serial_number` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '转账流水号',
    `account_from_id`        varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '转账账户',
    `account_to_id`          varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '进账账户',
    `amount`                 decimal(20, 2)                  NOT NULL DEFAULT '0.00' COMMENT '金额',
    `status`                 tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，1待处理，2处理，3废弃',
    `create_time`            datetime                        NOT NULL COMMENT '创建时间',
    `update_time`            datetime                        NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `unqi_transfer` (`transfer_serial_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

INSERT INTO `seata_tcc_bank0`.`t_account`(`id`, `account_id`, `amount`, `freezed_amount`, `create_time`, `update_time`)
VALUES (1, 'jannal', 10000.00, 0.00, '2022-05-03 17:23:37', '2022-05-03 17:23:39');