# mvn clean mybatis-generator:generate -Dmybatis.generator.configurationFile=src/main/resources/generatorConfig.xml

CREATE TABLE `resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `service_name` varchar(100) NOT NULL DEFAULT '' COMMENT '服务名称',
  `service_code` varchar(100) NOT NULL DEFAULT '' COMMENT '服务code',
  `func_parent` varchar(100) NOT NULL DEFAULT '' COMMENT '模块',
  `func_parent_name` varchar(100) NOT NULL DEFAULT '' COMMENT '模块名称',
  `func_action` varchar(100) NOT NULL DEFAULT '' COMMENT '功能',
  `func_action_name` varchar(100) NOT NULL DEFAULT '' COMMENT '功能名称',
  `func_type` int(1) NOT NULL DEFAULT '0' COMMENT '类型 0代表模块，1代表功能',
  `valid` int(1) NOT NULL DEFAULT '0' COMMENT '默认为0有效，1无效',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `func_action` (`func_action`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


CREATE TABLE `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `service_code` int(11) NOT NULL COMMENT '服务code',
  `service_name` varchar(100) NOT NULL DEFAULT '' COMMENT '服务名称',
  `role_name` varchar(100) NOT NULL DEFAULT '' COMMENT '角色名称',
  `valid` int(1) NOT NULL DEFAULT '0' COMMENT '0有效，1无效',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `role_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色',
  `resource_id` int(11) NOT NULL COMMENT '资源',
  `valid` int(1) NOT NULL COMMENT '0有效，1无效',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;