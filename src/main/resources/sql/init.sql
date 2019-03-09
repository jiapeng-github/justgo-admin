
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `ID` bigint(1) NOT NULL AUTO_INCREMENT,
  `DICT_NAME` varchar(50) DEFAULT NULL COMMENT '字典名字',
  `DICT_VALUE` varchar(50) DEFAULT NULL COMMENT '字典值',
  `DICT_DESC` text COMMENT '详细描述',
  `VALID` varchar(50) DEFAULT NULL COMMENT '是否有效 0为无效  1为有效',
  `CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES ('4', '操作系统', 'system', '系统', '4', '2018-01-11 18:08:30');
INSERT INTO `sys_dictionary` VALUES ('5', '轮播图位置', 'location', '轮播图位置', '1', '2018-01-11 22:28:12');

-- ----------------------------
-- Table structure for sys_dictionarydata
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionarydata`;
CREATE TABLE `sys_dictionarydata` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DICT_VALUE` varchar(50) DEFAULT NULL COMMENT 'dictionary中的值',
  `DICTDATA_NAME` varchar(50) DEFAULT NULL COMMENT '字典数据名字',
  `DICTDATA_VALUE` text COMMENT '字典数据值',
  `DICTDATA_DESC` text COMMENT '详细描述',
  `VALID` varchar(50) DEFAULT NULL COMMENT '是否有效 0为无效  1为有效',
  `CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dictionarydata
-- ----------------------------
INSERT INTO `sys_dictionarydata` VALUES ('1', 'system', 'IOS', 'IOS', 'IOS', '1', '2018-01-11 19:17:28');
INSERT INTO `sys_dictionarydata` VALUES ('2', 'system', 'Android', 'Android', 'Android', '1', '2018-01-11 19:18:15');
INSERT INTO `sys_dictionarydata` VALUES ('3', 'location', '首页', '首页', '首页', '1', '2018-01-11 22:23:39');
INSERT INTO `sys_dictionarydata` VALUES ('4', 'location', '中间', '中间', '中间', '1', '2018-01-11 22:23:43');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `RESOURCE_NAME` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `RESOURCE_URL` varchar(1000) DEFAULT NULL COMMENT '资源URL',
  `F_ID` bigint(20) DEFAULT NULL,
  `PERMISSION` varchar(100) DEFAULT NULL COMMENT '权限字符串',
  `PRIORITY` int(11) DEFAULT NULL COMMENT '资源顺序',
  `RESOURCE_TYPE` int(11) DEFAULT NULL COMMENT '资源类型\n            1:菜单标题\n            2:菜单\n            3:功能',
  `ICON` varchar(100) DEFAULT NULL COMMENT '图标',
  `RESOURCE_STATUS` int(11) DEFAULT NULL COMMENT '状态\n            1:正常\n            2:禁用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('13', '字典管理', '', null, '', '7', '1', 'fa fa-newspaper-o', '1');
INSERT INTO `sys_resource` VALUES ('14', '字典列表', '/sysDict/manageSysDict', '13', 'test:view', '1', '2', '', '1');
INSERT INTO `sys_resource` VALUES ('52', '系统管理', null, null, null, '1', '1', 'fa fa-gears', '1');
INSERT INTO `sys_resource` VALUES ('59', '用户管理', '/security/userView', '52', 'user:view', '1', '2', null, '1');
INSERT INTO `sys_resource` VALUES ('66', '角色管理', '/security/roleView', '52', 'role:view', '2', '2', null, '1');
INSERT INTO `sys_resource` VALUES ('80', '重刷redis冻结缓存', '/member/refreshRedis', '10', 'member:edit', '2', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('87', '用户列表', '/security/userList', '59', 'user:view', '1', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('94', '添加用户', '/security/addUser', '59', 'user:add', '2', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('101', '编辑用户', '/security/editUser', '59', 'user:edit', '3', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('108', '删除用户', '/security/deleteUser', '59', 'user:delete', '4', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('115', '角色列表', '/security/roleList', '66', 'role:view', '1', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('122', '添加角色', '/security/addRole', '66', 'role:add', '2', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('129', '编辑角色', '/security/editRole', '66', 'role:edit', '3', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('136', '删除角色', '/security/deleteRole', '66', 'role:delete', '4', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('143', '授权', '/security/authorization', '66', 'security:authorization', '1', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('325', '资源管理', '/security/resourcesView', '52', 'resources:view', '3', '2', null, '1');
INSERT INTO `sys_resource` VALUES ('383', '修改字典', '/sysDict/editSysDict', '14', 'sysDict:editSysDict', '0', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('389', '获取全部角色', '/security/getAllRole', '66', 'role:getAllRole', '6', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('393', '字典列表', '/sysDict/listSysDict', '14', 'sysDict:listSysDict', '1', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('394', '获取根据roleID获取全部资源(包含未分配的资源)', '/security/getALLResource/**', '325', 'resources:getALLResource', '2', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('399', '导出字典', '/sysDict/report', '14', 'sysDict:report', '2', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('401', '查询redis值，用于验证是否存到redis了', '/sysDict/findRedis', '14', 'sysDict:findRedis', '3', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('405', '字典数据列表跳转', '/sysDict/manageSysDictData', '14', 'sysDict:manageSysDictData', '4', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('409', '删除字典', '/sysDict/delSysDict', '14', 'sysDict:delSysDict', '5', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('410', '新增字典', '/sysDict/addSysDict', '14', 'sysDict:addSysDict', '6', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('415', '字典数据列表', '/sysDict/flushRedisData', '14', 'sysDict:edit', '7', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('419', '修改字典数据', '/sysDict/editSysDictData', '14', 'sysDict:editSysDictData', '8', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('424', '删除字典数据', '/sysDict/delSysDictData', '14', 'sysDict:delSysDictData', '9', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('425', '新增字典数据', '/sysDict/addSysDictData', '14', 'sysDict:add', '10', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('427', '从db字典中重导数据到redis', '/sysDict/flushRedisData', '14', 'sysDict:edit', '11', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('431', '编辑资源', '/security/updateResource', '325', 'security:updateResource', '4', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('440', '字典数据列表', '/sysDict/listSysDictData', '14', 'sysDict:listSysDictData', '12', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('500', '知识点管理', '', null, '', '500', '1', 'fa fa-columns', '1');
INSERT INTO `sys_resource` VALUES ('501', '知识点列表', '/knowledgePoint/index', '500', 'knowledgePoint:view', '501', '2', '', '1');
INSERT INTO `sys_resource` VALUES ('502', '知识点详情', '/knowledgePoint/detail', '501', 'knowledgePoint:detail', '502', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('503', '新增知识点', '/knowledgePoint/create', '501', 'knowledgePoint:create', '503', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('504', '修改知识点', '/knowledgePoint/modify', '501', 'knowledgePoint:modify', '504', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('505', '删除知识点', '/knowledgePoint/delete', '501', 'knowledgePoint:delete', '505', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('506', '知识点列表', '/knowledgePoint/list', '501', 'knowledgePoint:list', '506', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('600', '重点难点知识管理', '', null, '', '600', '1', 'fa fa-calendar-check-o', '1');
INSERT INTO `sys_resource` VALUES ('601', '重点难点知识列表', '/keyPoint/index', '600', 'keyPoint:view', '601', '2', '', '1');
INSERT INTO `sys_resource` VALUES ('602', '重点难点知识详情', '/keyPoint/detail', '601', 'keyPoint:detail', '602', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('603', '新增重点难点知识', '/keyPoint/create', '601', 'keyPoint:create', '603', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('604', '修改重点难点知识', '/keyPoint/modify', '601', 'keyPoint:modify', '604', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('605', '删除重点难点知识', '/keyPoint/delete', '601', 'keyPoint:delete', '606', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('606', '重点难点知识列表', '/keyPoint/list', '601', 'keyPoint:list', '606', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('700', '问题解答管理', '', null, '', '700', '1', 'fa fa-question', '1');
INSERT INTO `sys_resource` VALUES ('701', '问题解答列表', '/questionAnswer/index', '700', 'questionAnswer:view', '701', '2', '', '1');
INSERT INTO `sys_resource` VALUES ('702', '问题解答详情', '/questionAnswer/detail', '701', 'questionAnswer:detail', '702', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('703', '新增问题解答', '/questionAnswer/create', '701', 'questionAnswer:create', '703', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('704', '修改问题解答', '/questionAnswer/modify', '701', 'questionAnswer:modify', '704', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('705', '删除问题解答', '/questionAnswer/delete', '701', 'questionAnswer:delete', '707', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('706', '问题解答列表', '/questionAnswer/list', '701', 'questionAnswer:list', '706', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('800', '知识拓展管理', '', null, '', '800', '1', 'fa fa-server', '1');
INSERT INTO `sys_resource` VALUES ('801', '知识拓展列表', '/knowledgeMore/index', '800', 'knowledgeMore:view', '801', '2', '', '1');
INSERT INTO `sys_resource` VALUES ('802', '知识拓展详情', '/knowledgeMore/detail', '801', 'knowledgeMore:detail', '802', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('803', '新增知识拓展', '/knowledgeMore/create', '801', 'knowledgeMore:create', '803', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('804', '修改知识拓展', '/knowledgeMore/modify', '801', 'knowledgeMore:modify', '804', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('805', '删除知识拓展', '/knowledgeMore/delete', '801', 'knowledgeMore:delete', '808', '3', null, '1');
INSERT INTO `sys_resource` VALUES ('806', '知识拓展列表', '/knowledgeMore/list', '801', 'knowledgeMore:list', '806', '3', null, '1');
-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述',
  `F_ID` bigint(20) DEFAULT NULL COMMENT 'F_ID',
  `ROLE_STATUS` int(11) DEFAULT NULL COMMENT '状态\n            1:正常\n            2:禁用',
  `APP_ID` int(3) DEFAULT NULL COMMENT 'appID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('3', '超级管理员', '超级管理员', null, '1', '2');
INSERT INTO `sys_role` VALUES ('10', '管理员', '管理员', '3', '1', '2');
INSERT INTO `sys_role` VALUES ('11', '老师', null, '10', '1', '2');
INSERT INTO `sys_role` VALUES ('12', '学生', null, '10', '1', '2');
-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `ROLE_ID` bigint(20) NOT NULL,
  `RESOURCE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表';

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('11', '500');
INSERT INTO `sys_role_resource` VALUES ('11', '501');
INSERT INTO `sys_role_resource` VALUES ('11', '502');
INSERT INTO `sys_role_resource` VALUES ('11', '503');
INSERT INTO `sys_role_resource` VALUES ('11', '504');
INSERT INTO `sys_role_resource` VALUES ('11', '505');
INSERT INTO `sys_role_resource` VALUES ('11', '506');
INSERT INTO `sys_role_resource` VALUES ('11', '600');
INSERT INTO `sys_role_resource` VALUES ('11', '601');
INSERT INTO `sys_role_resource` VALUES ('11', '602');
INSERT INTO `sys_role_resource` VALUES ('11', '603');
INSERT INTO `sys_role_resource` VALUES ('11', '604');
INSERT INTO `sys_role_resource` VALUES ('11', '605');
INSERT INTO `sys_role_resource` VALUES ('11', '606');
INSERT INTO `sys_role_resource` VALUES ('11', '700');
INSERT INTO `sys_role_resource` VALUES ('11', '701');
INSERT INTO `sys_role_resource` VALUES ('11', '702');
INSERT INTO `sys_role_resource` VALUES ('11', '703');
INSERT INTO `sys_role_resource` VALUES ('11', '704');
INSERT INTO `sys_role_resource` VALUES ('11', '705');
INSERT INTO `sys_role_resource` VALUES ('11', '706');
INSERT INTO `sys_role_resource` VALUES ('11', '800');
INSERT INTO `sys_role_resource` VALUES ('11', '801');
INSERT INTO `sys_role_resource` VALUES ('11', '802');
INSERT INTO `sys_role_resource` VALUES ('11', '803');
INSERT INTO `sys_role_resource` VALUES ('11', '804');
INSERT INTO `sys_role_resource` VALUES ('11', '805');
INSERT INTO `sys_role_resource` VALUES ('11', '806');
INSERT INTO `sys_role_resource` VALUES ('12', '500');
INSERT INTO `sys_role_resource` VALUES ('12', '501');
INSERT INTO `sys_role_resource` VALUES ('12', '502');
INSERT INTO `sys_role_resource` VALUES ('12', '506');
INSERT INTO `sys_role_resource` VALUES ('12', '600');
INSERT INTO `sys_role_resource` VALUES ('12', '601');
INSERT INTO `sys_role_resource` VALUES ('12', '602');
INSERT INTO `sys_role_resource` VALUES ('12', '606');
INSERT INTO `sys_role_resource` VALUES ('12', '700');
INSERT INTO `sys_role_resource` VALUES ('12', '701');
INSERT INTO `sys_role_resource` VALUES ('12', '702');
INSERT INTO `sys_role_resource` VALUES ('12', '703');
INSERT INTO `sys_role_resource` VALUES ('12', '705');
INSERT INTO `sys_role_resource` VALUES ('12', '706');
INSERT INTO `sys_role_resource` VALUES ('12', '800');
INSERT INTO `sys_role_resource` VALUES ('12', '801');
INSERT INTO `sys_role_resource` VALUES ('12', '802');
INSERT INTO `sys_role_resource` VALUES ('12', '806');


-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_NAME` varchar(20) DEFAULT NULL COMMENT '登录名称',
  `USER_PASSWORD` varchar(512) DEFAULT NULL COMMENT '登录密码',
  `SALT` varchar(512) DEFAULT NULL COMMENT '盐',
  `F_ID` bigint(20) DEFAULT NULL COMMENT 'F_ID',
  `USER_STATUS` int(11) DEFAULT NULL COMMENT '状态\n            1:正常\n            2:禁用',
  `LAST_TIME` datetime DEFAULT NULL COMMENT '最后登录时间',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `FULL_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '692BE87C2B93651A60C6A420AB629866D59514D71283958F6E04F21637D399CEF965596373228E93A7611F1DFD9290B88D3BBAB38C612F9DABC65FAA45201FAD', 'D62F05368E98C113CDB24AACF641E770', null, '1', null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for knowledge_point
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_point`;
CREATE TABLE `knowledge_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `content` text,
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for key_point
-- ----------------------------
DROP TABLE IF EXISTS `key_point`;
CREATE TABLE `key_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `type` char(1) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `file` longblob,
  `file_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question_answer
-- ----------------------------
DROP TABLE IF EXISTS `question_answer`;
CREATE TABLE `question_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` text,
  `questioner` varchar(10) DEFAULT NULL,
  `question_time` datetime DEFAULT NULL,
  `answer` text,
  `answerer` varchar(10) DEFAULT NULL,
  `answer_time` datetime DEFAULT NULL,
  `file` longblob,
  `file_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for knowledge_more
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_more`;
CREATE TABLE `knowledge_more` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `file` longblob,
  `file_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
