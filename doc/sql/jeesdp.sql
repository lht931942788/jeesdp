-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jeesdp
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_department`
--

DROP TABLE IF EXISTS `admin_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_department` (
  `id` varchar(32) NOT NULL,
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `level` int(11) DEFAULT NULL COMMENT '级别',
  `code` varchar(10) DEFAULT NULL COMMENT '编码',
  `description` varchar(500) DEFAULT NULL COMMENT '说明',
  `pid` varchar(32) DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` int(11) DEFAULT NULL COMMENT '标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_department`
--

LOCK TABLES `admin_department` WRITE;
/*!40000 ALTER TABLE `admin_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_department_user`
--

DROP TABLE IF EXISTS `admin_department_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_department_user` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `department_id` varchar(32) DEFAULT NULL COMMENT '部门Id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '人员Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_department_user`
--

LOCK TABLES `admin_department_user` WRITE;
/*!40000 ALTER TABLE `admin_department_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_department_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_file`
--

DROP TABLE IF EXISTS `admin_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_file` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `path` varchar(32) DEFAULT NULL COMMENT '路径',
  `type` int(11) DEFAULT NULL COMMENT '类型：1 文本，2 图片，3 视频，4 其他',
  `extension` varchar(5) DEFAULT NULL COMMENT '扩展名',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` int(11) DEFAULT NULL COMMENT '标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_file`
--

LOCK TABLES `admin_file` WRITE;
/*!40000 ALTER TABLE `admin_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_form`
--

DROP TABLE IF EXISTS `admin_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_form` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `content` text,
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` int(11) DEFAULT NULL COMMENT '标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_form`
--

LOCK TABLES `admin_form` WRITE;
/*!40000 ALTER TABLE `admin_form` DISABLE KEYS */;
INSERT INTO `admin_form` VALUES ('c7d6b132a4ea4f79b0bd6a34d426d52b','111','','','630abd31779246ce95ee060cff9e3a16','2020-05-27 00:47:17','',NULL,0);
/*!40000 ALTER TABLE `admin_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_group`
--

DROP TABLE IF EXISTS `admin_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_group` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(40) DEFAULT NULL COMMENT '名称',
  `type` int(11) DEFAULT NULL COMMENT '类型：1 公共组，2 私有组',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `flag` int(11) DEFAULT NULL COMMENT '是否禁用：1 是，0 否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_group`
--

LOCK TABLES `admin_group` WRITE;
/*!40000 ALTER TABLE `admin_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_group_user`
--

DROP TABLE IF EXISTS `admin_group_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_group_user` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `group_id` varchar(32) DEFAULT NULL COMMENT '组Id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '人员Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组人员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_group_user`
--

LOCK TABLES `admin_group_user` WRITE;
/*!40000 ALTER TABLE `admin_group_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_group_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_message`
--

DROP TABLE IF EXISTS `admin_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_message` (
  `id` varchar(30) NOT NULL,
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` varchar(2048) DEFAULT NULL COMMENT '内容',
  `sender` varchar(32) DEFAULT NULL COMMENT '发送人',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `type` int(11) DEFAULT NULL COMMENT '类型：1 通知,2 公告，3 消息',
  `status` int(11) DEFAULT NULL COMMENT '状态：1 草稿，2 完成，3 已发送',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` int(11) DEFAULT NULL COMMENT '标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站内信表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_message`
--

LOCK TABLES `admin_message` WRITE;
/*!40000 ALTER TABLE `admin_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_message_receiver`
--

DROP TABLE IF EXISTS `admin_message_receiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_message_receiver` (
  `id` varchar(32) NOT NULL,
  `message_id` varchar(32) DEFAULT NULL COMMENT '站内信ID',
  `receiver` varchar(32) DEFAULT NULL COMMENT '接收人',
  `status` int(11) DEFAULT NULL COMMENT '状态：0 未查看，1 已查看',
  `view_time` varchar(20) DEFAULT NULL COMMENT '查看时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站内信接收人表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_message_receiver`
--

LOCK TABLES `admin_message_receiver` WRITE;
/*!40000 ALTER TABLE `admin_message_receiver` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_message_receiver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_resource`
--

DROP TABLE IF EXISTS `admin_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_resource` (
  `id` int(11) NOT NULL COMMENT 'id',
  `pid` varchar(32) NOT NULL COMMENT '父Id',
  `name` varchar(50) NOT NULL COMMENT '菜单',
  `url` varchar(100) DEFAULT NULL COMMENT '菜单地址',
  `path` varchar(100) DEFAULT NULL COMMENT '组件路径',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 菜单，2 按钮，3 路由',
  `code` varchar(15) NOT NULL COMMENT '权限标识',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `icon_class` varchar(20) DEFAULT NULL COMMENT '图标',
  `view_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否只有超级管理员查看 1 是，0 否',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` tinyint(2) NOT NULL DEFAULT '1' COMMENT '可用标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单和按钮表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_resource`
--

LOCK TABLES `admin_resource` WRITE;
/*!40000 ALTER TABLE `admin_resource` DISABLE KEYS */;
INSERT INTO `admin_resource` VALUES (1001,'0','首页','/dashboard','Dashboard',1,'dashboard',1,NULL,0,NULL,NULL,NULL,NULL,1),(1002,'0','系统管理','/system','system/Index',1,'system',2,NULL,0,NULL,NULL,NULL,NULL,1),(1002001,'1002','菜单管理','/system/menu','system/Menu',1,'menu',1,NULL,1,NULL,NULL,NULL,NULL,1),(1002002,'1002','角色管理','/system/role','system/Role',1,'role',2,NULL,1,NULL,NULL,NULL,NULL,1),(1002003,'1002','用户管理','/system/user','system/User',1,'user',3,NULL,0,NULL,NULL,NULL,NULL,1),(1002004,'1002','表单管理','/system/formlist','system/formmaker/FormList',1,'formlist',4,NULL,0,NULL,NULL,NULL,NULL,1),(1002005,'1002','表单设计','/system/formmaker','system/formmaker/FormMaker',3,'formmaker',4,NULL,0,NULL,NULL,NULL,NULL,1),(1002006,'1002','工作流管理','/system/workflow','system/workflow/Workflow',1,'workflow',5,NULL,0,NULL,NULL,NULL,NULL,1),(1002007,'1002','组管理','/system/group','system/Group',1,'group',8,NULL,0,NULL,NULL,NULL,NULL,1),(1002001001,'1002001','添加',NULL,NULL,2,'add',1,NULL,0,NULL,NULL,NULL,NULL,1),(1002001002,'1002001','修改',NULL,NULL,2,'edit',2,NULL,0,NULL,NULL,NULL,NULL,1),(1002001003,'1002001','删除',NULL,NULL,2,'remove',3,NULL,0,NULL,NULL,NULL,NULL,1),(1002002001,'1002002','添加',NULL,NULL,2,'add',1,NULL,0,NULL,NULL,NULL,NULL,1),(1002002002,'1002002','修改',NULL,NULL,2,'edit',2,NULL,0,NULL,NULL,NULL,NULL,1),(1002002003,'1002002','删除',NULL,NULL,2,'remove',3,NULL,0,NULL,NULL,NULL,NULL,1),(1002003001,'1002003','添加',NULL,NULL,2,'add',1,NULL,0,NULL,NULL,NULL,NULL,1),(1002003002,'1002003','修改',NULL,NULL,2,'edit',2,NULL,0,NULL,NULL,NULL,NULL,1),(1002003003,'1002003','删除',NULL,NULL,2,'remove',3,NULL,0,NULL,NULL,NULL,NULL,1),(1002004001,'1002004','新增',NULL,NULL,2,'add',1,NULL,0,NULL,NULL,NULL,NULL,1),(1002004002,'1002004','修改',NULL,NULL,2,'edit',2,NULL,0,NULL,NULL,NULL,NULL,1),(1002004003,'1002004','删除',NULL,NULL,2,'remove',3,NULL,0,NULL,NULL,NULL,NULL,1),(1002006001,'1002006','新增',NULL,NULL,2,'add',1,NULL,0,NULL,NULL,NULL,NULL,1),(1002006002,'1002006','修改',NULL,NULL,2,'edit',2,NULL,0,NULL,NULL,NULL,NULL,1),(1002006003,'1002006','删除',NULL,NULL,2,'remove',3,NULL,0,NULL,NULL,NULL,NULL,1),(1002006004,'1002','流程设计','/system/modeler','system/workflow/Modeler',3,'modeler',7,NULL,0,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `admin_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_role`
--

DROP TABLE IF EXISTS `admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_role` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '说明',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` tinyint(2) NOT NULL DEFAULT '1' COMMENT '可用标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role`
--

LOCK TABLES `admin_role` WRITE;
/*!40000 ALTER TABLE `admin_role` DISABLE KEYS */;
INSERT INTO `admin_role` VALUES ('5640264a2e31478695a284085aabe008','啊啊啊',NULL,NULL,NULL,NULL,NULL,NULL,1),('8a56f59e892e46a0bc96ef522ae51bec','测试','',NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `admin_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_role_resource`
--

DROP TABLE IF EXISTS `admin_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_role_resource` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL COMMENT '角色Id',
  `resource_id` varchar(32) NOT NULL COMMENT '资源Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role_resource`
--

LOCK TABLES `admin_role_resource` WRITE;
/*!40000 ALTER TABLE `admin_role_resource` DISABLE KEYS */;
INSERT INTO `admin_role_resource` VALUES ('1998bb0aa06d41368df6d44b67be9341','5640264a2e31478695a284085aabe008','1002'),('232f5c5f0e534de7ba4da0779d2484f3','8a56f59e892e46a0bc96ef522ae51bec','1002002002'),('2c9b9263d2574d34b60ef406cd2978d3','8a56f59e892e46a0bc96ef522ae51bec','1002001002'),('472811f3cf424ec683d7ce4056a537ae','8a56f59e892e46a0bc96ef522ae51bec','1002002003'),('611fdce6429d4490ab9d2d450d50d317','8a56f59e892e46a0bc96ef522ae51bec','1002001'),('7a36e39fd8ca49a5a0147386bc432323','8a56f59e892e46a0bc96ef522ae51bec','1002001001'),('9b3a042889af4c5db728ea1e4db142ae','8a56f59e892e46a0bc96ef522ae51bec','1001'),('9c143741e657403da53b2ff68fdb7343','8a56f59e892e46a0bc96ef522ae51bec','1002'),('a0431cd49f7141be9a7b7c156bf72ac9','8a56f59e892e46a0bc96ef522ae51bec','1002001003'),('a4483246d7154ed38c76fa80c4ad0008','5640264a2e31478695a284085aabe008','1002001'),('a861a850d1b846118986b2dd645a1bf5','5640264a2e31478695a284085aabe008','1002001003'),('b63d3f0f3dac498d921790c2c64e195b','5640264a2e31478695a284085aabe008','1002001001'),('bae2fccb8f99481294f2b1f8ca35d1a8','8a56f59e892e46a0bc96ef522ae51bec','1002002001'),('ee90811d931c468b9580017a5d420c71','5640264a2e31478695a284085aabe008','1002001002'),('f34c051230f044c785217bb9907d02cc','8a56f59e892e46a0bc96ef522ae51bec','1002002');
/*!40000 ALTER TABLE `admin_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_role_user`
--

DROP TABLE IF EXISTS `admin_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_role_user` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL COMMENT '角色Id',
  `user_id` varchar(32) NOT NULL COMMENT '用户Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role_user`
--

LOCK TABLES `admin_role_user` WRITE;
/*!40000 ALTER TABLE `admin_role_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_user`
--

DROP TABLE IF EXISTS `admin_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_user` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `userinfo_id` varchar(32) DEFAULT NULL COMMENT '用户信息ID',
  `user_type` varchar(32) NOT NULL COMMENT '用户类型',
  `account_non_expired` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户是否过期',
  `account_non_locked` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户是否被锁定',
  `credentials_non_expired` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户凭证是否过期',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户是否禁用',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` tinyint(2) NOT NULL DEFAULT '1' COMMENT '可用标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_user`
--

LOCK TABLES `admin_user` WRITE;
/*!40000 ALTER TABLE `admin_user` DISABLE KEYS */;
INSERT INTO `admin_user` VALUES ('630abd31779246ce95ee060cff9e3a16','sys','超级管理员','$2a$10$hsZbS/MwrNWSYV/Ys6rw0u92dYqTRuzgZijxfVz6KBzXRCHuCpMYy',NULL,NULL,NULL,'1',1,1,1,1,NULL,NULL,NULL,NULL,1),('9ed8ec374b7e4501a8ac14d23c9e8e4d','ceshi','测试','$2a$10$hsZbS/MwrNWSYV/Ys6rw0u92dYqTRuzgZijxfVz6KBzXRCHuCpMYy','15804419906','931942788@qq.com',NULL,'3',1,1,1,1,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `admin_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_user_dictionary`
--

DROP TABLE IF EXISTS `admin_user_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_user_dictionary` (
  `Id` varchar(32) NOT NULL,
  `key` varchar(10) DEFAULT NULL COMMENT '字典值key',
  `label` varchar(20) DEFAULT NULL COMMENT '字典值名称',
  `type` varchar(32) DEFAULT NULL COMMENT '字典名称',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` int(11) DEFAULT NULL COMMENT '是否使用：1 是，0 否',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_user_dictionary`
--

LOCK TABLES `admin_user_dictionary` WRITE;
/*!40000 ALTER TABLE `admin_user_dictionary` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_user_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_user_resource`
--

DROP TABLE IF EXISTS `admin_user_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_user_resource` (
  `id` varchar(30) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `resource_id` varchar(32) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_user_resource`
--

LOCK TABLES `admin_user_resource` WRITE;
/*!40000 ALTER TABLE `admin_user_resource` DISABLE KEYS */;
INSERT INTO `admin_user_resource` VALUES ('1','9ed8ec374b7e4501a8ac14d23c9e8e4d','1002'),('2','9ed8ec374b7e4501a8ac14d23c9e8e4d','1002002');
/*!40000 ALTER TABLE `admin_user_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_userinfo`
--

DROP TABLE IF EXISTS `admin_userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_userinfo` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL COMMENT '性别：1 男，2 女',
  `id_type` int(11) DEFAULT NULL COMMENT '证件类型',
  `id_card` varchar(18) DEFAULT NULL COMMENT '证件号',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `flag` int(11) DEFAULT NULL COMMENT '标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_userinfo`
--

LOCK TABLES `admin_userinfo` WRITE;
/*!40000 ALTER TABLE `admin_userinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(32) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('1','auth_server','$2a$10$xzwyAr4MdDTGFXB/uZgmkO6fA.krG0fZGs4DJv7wdBlXyneWhpTKS','user','authorization_code,password,client_credentials,implicit,refresh_token','http://localhost','ROLE_ADMIN',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflow_content`
--

DROP TABLE IF EXISTS `workflow_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workflow_content` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `process_id` varchar(32) DEFAULT NULL COMMENT '工作流Id',
  `content` longtext COMMENT '流程xml',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程图';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflow_content`
--

LOCK TABLES `workflow_content` WRITE;
/*!40000 ALTER TABLE `workflow_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflow_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflow_log`
--

DROP TABLE IF EXISTS `workflow_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workflow_log` (
  `id` varchar(32) NOT NULL,
  `task_id` varchar(32) DEFAULT NULL COMMENT '任务id',
  `node_id` varchar(30) DEFAULT NULL COMMENT '节点Id',
  `node_name` varchar(100) DEFAULT NULL COMMENT '节点名称',
  `operate_name` varchar(100) DEFAULT NULL COMMENT '操作名称',
  `operator_id` varchar(32) DEFAULT NULL COMMENT '操作人ID',
  `operator_name` varchar(20) DEFAULT NULL COMMENT '操作人名称',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT NULL COMMENT '状态：1 启动，2 流转中，3 挂起，4 通过，5 驳回，6 结束',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflow_log`
--

LOCK TABLES `workflow_log` WRITE;
/*!40000 ALTER TABLE `workflow_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflow_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflow_process`
--

DROP TABLE IF EXISTS `workflow_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workflow_process` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `params` longtext COMMENT '流程变量',
  `status` int(11) DEFAULT NULL COMMENT '状态：1 草稿，2 新增，3 启用，4 停用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流流程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflow_process`
--

LOCK TABLES `workflow_process` WRITE;
/*!40000 ALTER TABLE `workflow_process` DISABLE KEYS */;
INSERT INTO `workflow_process` VALUES ('Process_1','测试',NULL,2,NULL,NULL);
/*!40000 ALTER TABLE `workflow_process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflow_task`
--

DROP TABLE IF EXISTS `workflow_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workflow_task` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `process_id` varchar(32) NOT NULL COMMENT '流程ID',
  `process_name` varchar(100) DEFAULT NULL COMMENT '流程名称',
  `current_node_id` varchar(32) DEFAULT NULL COMMENT '当前节点ID',
  `current_node_name` varchar(100) DEFAULT NULL COMMENT '当前节点名称',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态：1 启动，2 流转中，3 挂起，4 通过，5 驳回，6 结束',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作流任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflow_task`
--

LOCK TABLES `workflow_task` WRITE;
/*!40000 ALTER TABLE `workflow_task` DISABLE KEYS */;
INSERT INTO `workflow_task` VALUES ('ad9aa62bff4241a6b7878fd1f76de69b','Process_1','测试','Task_14nkks5','1',2,'2019-11-29 15:27:40',NULL,'id'),('c0e94bed9d6a4b7abffa5830b8822038','Process_1','测试','Task_14nkks5','1',2,'2019-11-29 15:31:01',NULL,'id');
/*!40000 ALTER TABLE `workflow_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflow_task_user`
--

DROP TABLE IF EXISTS `workflow_task_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workflow_task_user` (
  `id` varchar(32) NOT NULL COMMENT 'Id',
  `task_id` varchar(32) DEFAULT NULL COMMENT '任务ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户Id',
  `status` int(11) DEFAULT '4' COMMENT '状态：4 通过，5 驳回',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务当前节点审核人';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflow_task_user`
--

LOCK TABLES `workflow_task_user` WRITE;
/*!40000 ALTER TABLE `workflow_task_user` DISABLE KEYS */;
INSERT INTO `workflow_task_user` VALUES ('4414e6d5df6641d59a8261987d6bcdf6','c0e94bed9d6a4b7abffa5830b8822038','id',4,NULL,NULL),('bbde987cd40d4c3b96d4a4086264de80','ad9aa62bff4241a6b7878fd1f76de69b','id',4,NULL,NULL);
/*!40000 ALTER TABLE `workflow_task_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-01  8:59:46
