DROP TABLE IF EXISTS `DBMIGRATE_DATABASE_CONNECTION`;
CREATE TABLE `DBMIGRATE_DATABASE_CONNECTION`
(
    `id`                 bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`               varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '连接名称',
    `type`               varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '数据库类型',
    `version`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '驱动版本',
    `driver`             varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '驱动类名称',
    `mode`               varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL DEFAULT '0' COMMENT '连接模式：0默认 1专业',
    `address`            varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '连接地址',
    `port`               varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL DEFAULT '' COMMENT '连接端口号',
    `database_name`      varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '数据库名',
    `character_encoding` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL DEFAULT '' COMMENT '编码格式',
    `url`                longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'jdbc-url连接串',
    `username`           varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '连接账号',
    `password`           varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '账号密码',
    `create_time`        timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`        timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库连接' ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
