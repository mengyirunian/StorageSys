CREATE TABLE `resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `func_parent` varchar(100) CHARACTER SET latin1 NOT NULL DEFAULT 'parent' COMMENT '模块',
  `func_action` varchar(100) CHARACTER SET latin1 NOT NULL DEFAULT 'action' COMMENT '功能',
  `func_parent_name` varchar(100) CHARACTER SET latin1 NOT NULL DEFAULT 'parentXYZ' COMMENT '模块名称',
  `func_action_name` varchar(100) CHARACTER SET latin1 NOT NULL DEFAULT 'actionXYZ' COMMENT '功能名称',
  `valid` int(1) NOT NULL DEFAULT '0' COMMENT '默认为0有效，1无效',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;