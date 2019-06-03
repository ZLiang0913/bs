/*
Navicat MySQL Data Transfer

Source Server         : cms
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : pinyougoudb

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-05-17 13:45:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL,
  `source_type` varchar(1) DEFAULT NULL COMMENT '会员来源：1:PC，2：H5，3：Android，4：IOS，5：WeChat',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `status` varchar(1) DEFAULT NULL COMMENT '使用状态（Y正常 N非正常）',
  `head_pic` varchar(150) DEFAULT NULL COMMENT '头像地址',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ号码',
  `account_balance` decimal(10,0) DEFAULT NULL COMMENT '账户余额',
  `is_mobile_check` varchar(1) DEFAULT '0' COMMENT '手机是否验证 （0否  1是）',
  `is_email_check` varchar(1) DEFAULT '0' COMMENT '邮箱是否检测（0否  1是）',
  `sex` varchar(1) DEFAULT '0' COMMENT '性别，1男，2女',
  `user_level` int(11) DEFAULT NULL COMMENT '会员等级',
  `points` int(11) DEFAULT NULL COMMENT '积分',
  `experience_value` int(11) DEFAULT NULL COMMENT '经验值',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'sunwukong', '123123', '1112221111', null, '2017-08-19 20:50:21', '2017-08-19 20:50:21', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('2', 'zhubajie', '4297f44b13955235245b2497399d7a93', '111122', null, '2017-08-19 21:00:23', '2017-08-19 21:00:23', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('3', 'shaheshang', '96e79218965eb72c92a549dd5a330112', '13900112222', null, '2017-08-19 22:37:44', '2017-08-19 22:37:44', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('4', 'zhangsan', '00b7691d86d96aebd21dd9e138f90840', '17701265258', null, '2017-08-19 23:44:45', '2017-08-19 23:44:45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('5', 'lisi', '8fbaad286e993d37b34b41749894b4a7', '13401341444', null, '2017-08-20 11:08:29', '2017-08-20 11:08:29', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('6', 'wangwu', 'd41d8cd98f00b204e9800998ecf8427e', '13601566766', null, '2017-08-20 11:09:26', '2017-08-20 11:09:26', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('7', 'zhaoliu', 'f379eaf3c831b04de153469d1bec345e', '13669669966', null, '2017-08-20 12:09:27', '2017-08-20 12:09:27', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('8', 'lijialong', '4297f44b13955235245b2497399d7a93', '13260006290', null, '2017-08-20 12:23:37', '2017-08-20 12:23:37', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('9', 'tangseng', '4297f44b13955235245b2497399d7a93', '13901223232', null, '2017-10-07 23:07:42', '2017-10-07 23:07:42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('10', 'niumowang', '4297f44b13955235245b2497399d7a93', '13900112222', null, '2017-10-07 23:46:53', '2017-10-07 23:46:53', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('11', 'honghaier', 'f14029217ff5e7a50cdc7e70f686cf29', '13919991999', null, '2017-10-08 11:23:02', '2017-10-08 11:23:02', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('12', 'tieshanxian', 'f14029217ff5e7a50cdc7e70f686cf29', '13999999999', null, '2017-10-08 12:10:26', '2017-10-08 12:10:26', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('13', 'nezha', '1a100d2c0dab19c4430e7d73762b3423', '17338118923', null, '2017-10-08 12:23:27', '2017-10-08 12:23:27', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('14', 'litianwang', 'b0baee9d279d34fa1dfd71aadb908c3f', '17338118923', null, '2017-10-08 12:28:25', '2017-10-08 12:28:25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('15', 'taiba', '97d84aa49109e72a54980e79802844be', '17338118923', null, '2017-10-08 12:34:53', '2017-10-08 12:34:53', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('17', 'xiaohong', '202cb962ac59075b964b07152d234b70', '15852939091', null, '2019-02-14 22:36:58', '2019-02-14 22:36:58', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('18', 'zha', '123', '158', null, '2019-02-14 22:36:58', '2019-02-14 22:36:58', '1', null, null, null, null, null, null, '0', '0', '0', null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('19', 'zhaol', '202cb962ac59075b964b07152d234b70', '15852939091', null, '2019-02-24 19:38:35', '2019-02-24 19:38:35', '1', null, null, null, null, null, null, '0', '0', '0', null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('20', 'zhaoliang', '202cb962ac59075b964b07152d234b70', '17315375909', null, '2019-04-02 09:45:57', '2019-04-02 09:45:57', '1', null, null, null, null, null, null, '0', '0', '0', null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('21', 'zhao', '202cb962ac59075b964b07152d234b70', '15852939091', null, '2019-04-03 15:49:26', '2019-04-03 15:49:26', '1', null, null, null, null, null, null, '0', '0', '0', null, null, null, null, null);
