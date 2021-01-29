/*
quartz 数据持久化的数据表：默认持久化为在内存，可选择使用jdbc持久化到mysql
Navicat MySQL Data Transfer
Source Server         : local
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : quartz
Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001
Date: 2017-11-05 18:01:51
*/
SET FOREIGN_KEY_CHECKS = 0;-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE
  IF
  EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
                                    `SCHED_NAME` VARCHAR ( 120 ) NOT NULL,
                                    `TRIGGER_NAME` VARCHAR ( 200 ) NOT NULL,
                                    `TRIGGER_GROUP` VARCHAR ( 200 ) NOT NULL,
                                    `BLOB_DATA` BLOB DEFAULT NULL,
                                    PRIMARY KEY ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` ),
                                    KEY `SCHED_NAME` ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` ),
                                    CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` ) REFERENCES `qrtz_triggers` ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE
  IF
  EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
                                `SCHED_NAME` VARCHAR ( 120 ) NOT NULL,
                                `CALENDAR_NAME` VARCHAR ( 200 ) NOT NULL,
                                `CALENDAR` BLOB NOT NULL,
                                PRIMARY KEY ( `SCHED_NAME`, `CALENDAR_NAME` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE
  IF
  EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
                                    `SCHED_NAME` VARCHAR ( 120 ) NOT NULL,
                                    `TRIGGER_NAME` VARCHAR ( 200 ) NOT NULL,
                                    `TRIGGER_GROUP` VARCHAR ( 200 ) NOT NULL,
                                    `CRON_EXPRESSION` VARCHAR ( 120 ) NOT NULL,
                                    `TIME_ZONE_ID` VARCHAR ( 80 ) DEFAULT NULL,
                                    PRIMARY KEY ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` ),
                                    CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` ) REFERENCES `qrtz_triggers` ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE
  IF
  EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
                                     `SCHED_NAME` VARCHAR ( 120 ) NOT NULL,
                                     `ENTRY_ID` VARCHAR ( 95 ) NOT NULL,
                                     `TRIGGER_NAME` VARCHAR ( 200 ) NOT NULL,
                                     `TRIGGER_GROUP` VARCHAR ( 200 ) NOT NULL,
                                     `INSTANCE_NAME` VARCHAR ( 200 ) NOT NULL,
                                     `FIRED_TIME` BIGINT ( 13 ) NOT NULL,
                                     `SCHED_TIME` BIGINT ( 13 ) NOT NULL,
                                     `PRIORITY` INT ( 11 ) NOT NULL,
                                     `STATE` VARCHAR ( 16 ) NOT NULL,
                                     `JOB_NAME` VARCHAR ( 200 ) DEFAULT NULL,
                                     `JOB_GROUP` VARCHAR ( 200 ) DEFAULT NULL,
                                     `IS_NONCONCURRENT` VARCHAR ( 1 ) DEFAULT NULL,
                                     `REQUESTS_RECOVERY` VARCHAR ( 1 ) DEFAULT NULL,
                                     PRIMARY KEY ( `SCHED_NAME`, `ENTRY_ID` ),
                                     KEY `IDX_QRTZ_FT_TRIG_INST_NAME` ( `SCHED_NAME`, `INSTANCE_NAME` ),
                                     KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` ( `SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY` ),
                                     KEY `IDX_QRTZ_FT_J_G` ( `SCHED_NAME`, `JOB_NAME`, `JOB_GROUP` ),
                                     KEY `IDX_QRTZ_FT_JG` ( `SCHED_NAME`, `JOB_GROUP` ),
                                     KEY `IDX_QRTZ_FT_T_G` ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` ),
                                     KEY `IDX_QRTZ_FT_TG` ( `SCHED_NAME`, `TRIGGER_GROUP` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE
  IF
  EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
                                  `SCHED_NAME` VARCHAR ( 120 ) NOT NULL,
                                  `JOB_NAME` VARCHAR ( 200 ) NOT NULL,
                                  `JOB_GROUP` VARCHAR ( 200 ) NOT NULL,
                                  `DESCRIPTION` VARCHAR ( 250 ) DEFAULT NULL,
                                  `JOB_CLASS_NAME` VARCHAR ( 250 ) NOT NULL,
                                  `IS_DURABLE` VARCHAR ( 1 ) NOT NULL,
                                  `IS_NONCONCURRENT` VARCHAR ( 1 ) NOT NULL,
                                  `IS_UPDATE_DATA` VARCHAR ( 1 ) NOT NULL,
                                  `REQUESTS_RECOVERY` VARCHAR ( 1 ) NOT NULL,
                                  `JOB_DATA` BLOB DEFAULT NULL,
                                  PRIMARY KEY ( `SCHED_NAME`, `JOB_NAME`, `JOB_GROUP` ),
                                  KEY `IDX_QRTZ_J_REQ_RECOVERY` ( `SCHED_NAME`, `REQUESTS_RECOVERY` ),
                                  KEY `IDX_QRTZ_J_GRP` ( `SCHED_NAME`, `JOB_GROUP` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE
  IF
  EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` ( `SCHED_NAME` VARCHAR ( 120 ) NOT NULL, `LOCK_NAME` VARCHAR ( 40 ) NOT NULL, PRIMARY KEY ( `SCHED_NAME`, `LOCK_NAME` ) ) ENGINE = INNODB DEFAULT CHARSET = utf8;-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE
  IF
  EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` ( `SCHED_NAME` VARCHAR ( 120 ) NOT NULL, `TRIGGER_GROUP` VARCHAR ( 200 ) NOT NULL, PRIMARY KEY ( `SCHED_NAME`, `TRIGGER_GROUP` ) ) ENGINE = INNODB DEFAULT CHARSET = utf8;-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE
  IF
  EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
                                      `SCHED_NAME` VARCHAR ( 120 ) NOT NULL,
                                      `INSTANCE_NAME` VARCHAR ( 200 ) NOT NULL,
                                      `LAST_CHECKIN_TIME` BIGINT ( 13 ) NOT NULL,
                                      `CHECKIN_INTERVAL` BIGINT ( 13 ) NOT NULL,
                                      PRIMARY KEY ( `SCHED_NAME`, `INSTANCE_NAME` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE
  IF
  EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
                                      `SCHED_NAME` VARCHAR ( 120 ) NOT NULL,
                                      `TRIGGER_NAME` VARCHAR ( 200 ) NOT NULL,
                                      `TRIGGER_GROUP` VARCHAR ( 200 ) NOT NULL,
                                      `REPEAT_COUNT` BIGINT ( 7 ) NOT NULL,
                                      `REPEAT_INTERVAL` BIGINT ( 12 ) NOT NULL,
                                      `TIMES_TRIGGERED` BIGINT ( 10 ) NOT NULL,
                                      PRIMARY KEY ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` ),
                                      CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` ) REFERENCES `qrtz_triggers` ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE
  IF
  EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
                                       `SCHED_NAME` VARCHAR ( 120 ) NOT NULL,
                                       `TRIGGER_NAME` VARCHAR ( 200 ) NOT NULL,
                                       `TRIGGER_GROUP` VARCHAR ( 200 ) NOT NULL,
                                       `STR_PROP_1` VARCHAR ( 512 ) DEFAULT NULL,
                                       `STR_PROP_2` VARCHAR ( 512 ) DEFAULT NULL,
                                       `STR_PROP_3` VARCHAR ( 512 ) DEFAULT NULL,
                                       `INT_PROP_1` INT ( 11 ) DEFAULT NULL,
                                       `INT_PROP_2` INT ( 11 ) DEFAULT NULL,
                                       `LONG_PROP_1` BIGINT ( 20 ) DEFAULT NULL,
                                       `LONG_PROP_2` BIGINT ( 20 ) DEFAULT NULL,
                                       `DEC_PROP_1` DECIMAL ( 13, 4 ) DEFAULT NULL,
                                       `DEC_PROP_2` DECIMAL ( 13, 4 ) DEFAULT NULL,
                                       `BOOL_PROP_1` VARCHAR ( 1 ) DEFAULT NULL,
                                       `BOOL_PROP_2` VARCHAR ( 1 ) DEFAULT NULL,
                                       PRIMARY KEY ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` ),
                                       CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` ) REFERENCES `qrtz_triggers` ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE
  IF
  EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
                               `SCHED_NAME` VARCHAR ( 120 ) NOT NULL,
                               `TRIGGER_NAME` VARCHAR ( 200 ) NOT NULL,
                               `TRIGGER_GROUP` VARCHAR ( 200 ) NOT NULL,
                               `JOB_NAME` VARCHAR ( 200 ) NOT NULL,
                               `JOB_GROUP` VARCHAR ( 200 ) NOT NULL,
                               `DESCRIPTION` VARCHAR ( 250 ) DEFAULT NULL,
                               `NEXT_FIRE_TIME` BIGINT ( 13 ) DEFAULT NULL,
                               `PREV_FIRE_TIME` BIGINT ( 13 ) DEFAULT NULL,
                               `PRIORITY` INT ( 11 ) DEFAULT NULL,
                               `TRIGGER_STATE` VARCHAR ( 16 ) NOT NULL,
                               `TRIGGER_TYPE` VARCHAR ( 8 ) NOT NULL,
                               `START_TIME` BIGINT ( 13 ) NOT NULL,
                               `END_TIME` BIGINT ( 13 ) DEFAULT NULL,
                               `CALENDAR_NAME` VARCHAR ( 200 ) DEFAULT NULL,
                               `MISFIRE_INSTR` SMALLINT ( 2 ) DEFAULT NULL,
                               `JOB_DATA` BLOB DEFAULT NULL,
                               PRIMARY KEY ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP` ),
                               KEY `IDX_QRTZ_T_J` ( `SCHED_NAME`, `JOB_NAME`, `JOB_GROUP` ),
                               KEY `IDX_QRTZ_T_JG` ( `SCHED_NAME`, `JOB_GROUP` ),
                               KEY `IDX_QRTZ_T_C` ( `SCHED_NAME`, `CALENDAR_NAME` ),
                               KEY `IDX_QRTZ_T_G` ( `SCHED_NAME`, `TRIGGER_GROUP` ),
                               KEY `IDX_QRTZ_T_STATE` ( `SCHED_NAME`, `TRIGGER_STATE` ),
                               KEY `IDX_QRTZ_T_N_STATE` ( `SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE` ),
                               KEY `IDX_QRTZ_T_N_G_STATE` ( `SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE` ),
                               KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` ( `SCHED_NAME`, `NEXT_FIRE_TIME` ),
                               KEY `IDX_QRTZ_T_NFT_ST` ( `SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME` ),
                               KEY `IDX_QRTZ_T_NFT_MISFIRE` ( `SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME` ),
                               KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` ( `SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE` ),
                               KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` ( `SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE` ),
                               CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY ( `SCHED_NAME`, `JOB_NAME`, `JOB_GROUP` ) REFERENCES `qrtz_job_details` ( `SCHED_NAME`, `JOB_NAME`, `JOB_GROUP` )
) ENGINE = INNODB DEFAULT CHARSET = utf8;