
CREATE DATABASE IF NOT EXISTS test_tx DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_bin;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
                           `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '账户名',
                           `money` decimal(20,2) NOT NULL COMMENT '金额',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

BEGIN;
INSERT INTO `account` VALUES (1, 'jannal', 2000.00);
INSERT INTO `account` VALUES (2, 'tom', 500.00);
COMMIT;

