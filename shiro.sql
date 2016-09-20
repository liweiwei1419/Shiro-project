drop database ssm_shiro;
# 创建数据库 ssm_shiro
CREATE DATABASE	ssm_shiro DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
# 使用数据库 ssm_shiro
USE ssm_shiro;
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
VALUES('liwei','be78263da332dc2c7005f7551d7e57cd','李威威',1);
INSERT INTO t_user(username,`password`,nickname,`status`)
VALUES('huzhenyu','666666','胡振宇',1);
INSERT INTO t_user(username,`password`,nickname,`status`)
VALUES('liguowu','888888','李国武',1);
INSERT INTO t_user(username,`password`,nickname,`status`)
VALUES('wudi','333333','吴迪',1);

# 创建数据表 t_role
CREATE TABLE t_role(
  id TINYINT PRIMARY KEY AUTO_INCREMENT comment '角色表 ID',
  `name` VARCHAR(20) NOT NULL comment '角色名称',
  sn VARCHAR(20) NOT NULL comment '角色字符串'
)engine=innodb auto_increment=1 DEFAULT charset=utf8 comment='角色信息表';
# name 字段用于显示给人看, sn 字段用在代码中做角色匹配


# 创建数据用于测试
INSERT INTO t_role(`name`,`sn`) VALUES('管理员','admin'),('开发工程师','dev'),('测试工程师','test');

# 创建数据表 t_user_role
CREATE TABLE t_user_role(
  id TINYINT PRIMARY KEY AUTO_INCREMENT comment '用户角色关联表 ID',
  user_id TINYINT NOT NULL,
  role_id TINYINT NOT NULL
)engine=innodb auto_increment=1 charset=utf8 comment='用户角色关联表';

# 创建数据用于测试
INSERT INTO `t_user_role`(`user_id`,`role_id`)
VALUES(1,1),(2,1),(3,2),(4,3),(5,2);

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
('用户删除','user:delete','/admin/delete/**'),
('角色管理','role:*','/admin/role/**'),
('角色添加','role:add','/admin/role/add'),
('角色修改','role:update','/admin/role/update'),
('资源管理','resource:*','/admin/resource/**');

# 创建角色资源关联表
CREATE TABLE t_role_resource(
  id TINYINT PRIMARY KEY AUTO_INCREMENT comment '角色资源关联 ID',
  role_id TINYINT not null comment '角色 id',
  resource_id TINYINT not null comment '资源 id'
)engine=innodb auto_increment=1 charset=utf8 comment='角色资源关联表';

# 创建数据用于测试
INSERT INTO t_role_resource(role_id,resource_id)
VALUES(1,1),(1,2),(1,5),(1,8),
(2,2),(2,5),(2,8),
(3,3),(3,4),(3,6),(3,7);