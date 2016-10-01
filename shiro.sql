drop database ssm_shiro;
# 创建数据库 ssm_shiro
CREATE DATABASE	ssm_shiro DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
# 使用数据库 ssm_shiro
USE ssm_shiro;


drop table if EXISTS t_user;
drop table if EXISTS t_role;
drop table if EXISTS t_user_role;
drop table if EXISTS t_resource;
drop table if EXISTS t_role_resource;




# 创建数据表 t_user
CREATE TABLE t_user(
  id TINYINT PRIMARY KEY AUTO_INCREMENT comment '用户 ID',
  username VARCHAR(30) NOT NULL comment '用户名',
  `password` VARCHAR(32) NOT NULL comment '密码',
  nickname VARCHAR(30) NOT NULL comment '昵称',
  `status` TINYINT not null comment '状态:1 启用,2 禁用'
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

# 创建数据用于测试
INSERT INTO t_user(username,`password`,nickname,`status`)
VALUES('admin','a66abb5684c45962d887564f08346e8d','超级管理员',1);
INSERT INTO t_user(username,`password`,nickname,`status`)
VALUES('dev','c43812121e594f158520698ba706118f','开发工程师',1);
INSERT INTO t_user(username,`password`,nickname,`status`)
VALUES('test','47ec2dd791e31e2ef2076caf64ed9b3d','测试工程师',1);
INSERT INTO t_user(username,`password`,nickname,`status`)
VALUES('doc','5afd1e481507a2a181decc3860b32d15','文档工程师',1);

# 创建数据表 t_role
# name 字段用于显示给人看, sn 字段用在代码中做角色匹配
CREATE TABLE t_role(
  id TINYINT PRIMARY KEY AUTO_INCREMENT comment '角色表 ID',
  `name` VARCHAR(20) NOT NULL comment '角色名称',
  sn VARCHAR(20) NOT NULL comment '角色字符串'
)engine=innodb auto_increment=1 DEFAULT charset=utf8 comment='角色信息表';

# 创建数据用于测试
INSERT INTO t_role(`name`,`sn`) VALUES('管理员','admin'),('开发工程师','dev'),('测试工程师','test'),('文档工程师','doc');

# 创建数据表 t_user_role
CREATE TABLE t_user_role(
  id TINYINT PRIMARY KEY AUTO_INCREMENT comment '用户角色关联表 ID',
  user_id TINYINT NOT NULL,
  role_id TINYINT NOT NULL
)engine=innodb auto_increment=1 charset=utf8 comment='用户角色关联表';

# 创建数据用于测试
INSERT INTO `t_user_role`(`user_id`,`role_id`)
VALUES(1,1),(2,2),(3,3),(4,4);

# 创建资源表 t_resource
# 资源在本项目中的含义就是 "权限"
CREATE TABLE t_resource(
  id TINYINT PRIMARY KEY AUTO_INCREMENT comment '资源 ID',
  `name` VARCHAR(20) NOT NULL comment '资源名称,一般是中文名称(给人看的)',
  permission VARCHAR(40) NOT NULL comment '资源权限字符串,一般是 Shiro 默认的通配符表示(给人看的)',
  url VARCHAR(40) NOT NULL comment '资源 url 表示,我们设计的系统让 Shiro 通过这个路径字符串去匹配浏览器中显示的路径'
)engine=innodb auto_increment=1 charset=utf8 comment='资源表';

# 创建数据用于测试
INSERT INTO t_resource(`name`,permission,url)
VALUES('系统管理','admin:*','/admin/**'),
('用户管理','user:*','/admin/user/**'),
('用户添加','user:add','/admin/user/add'),
('用户删除','user:delete','/admin/user/delete'),
('用户修改','user:update','/admin/user/update'),
('用户查询','user:list','/admin/user/list'),
('用户资源查询','user:resources:*','/admin/user/resources/*'),
('角色管理','role:*','/admin/role/**'),
('角色添加','role:add','/admin/role/add'),
('角色删除','role:delete','/admin/role/delete'),
('角色修改','role:update','/admin/role/update'),
('角色查询','role:list','/admin/role/list'),
('角色资源查询','role:resources:*','/admin/role/resources/*'),
('资源管理','resource:*','/admin/resource/**'),
('资源增加','resource:add','/admin/resource/add'),
('资源删除','resource:delete','/admin/resource/delete'),
('资源修改','resource:update','/admin/resource/update'),
('资源查询','resource:list','/admin/resource/list');


# 创建角色资源关联表
CREATE TABLE t_role_resource(
  id TINYINT PRIMARY KEY AUTO_INCREMENT comment '角色资源关联 ID',
  role_id TINYINT not null comment '角色 id',
  resource_id TINYINT not null comment '资源 id'
)engine=innodb auto_increment=1 charset=utf8 comment='角色资源关联表';

# 创建数据用于测试
INSERT INTO t_role_resource(role_id,resource_id)
VALUES(1,1),
(2,3),(2,5),(2,6),(2,7),(2,9),(2,11),(2,12),(2,13),(2,15),(2,17),(2,18),
(3,6),(3,7),(3,8),(3,14),
(4,6),(4,7),(4,12),(4,18);