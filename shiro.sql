CREATE DATABASE	ssm_shiro DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE ssm_shiro;
CREATE TABLE t_user(
  id TINYINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(30) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  nickname VARCHAR(30) NOT NULL,
  `status` TINYINT
);


INSERT INTO t_user(username,`password`,nickname,`status`)
VALUES('liwei','123456','李威威',1);

INSERT INTO t_user(username,`password`,nickname,`status`)
VALUES('huzhenyu','666666','胡振宇',1);
INSERT INTO t_user(username,`password`,nickname,`status`)
VALUES('liguowu','888888','李国武',1);
INSERT INTO t_user(username,`password`,nickname,`status`)
VALUES('wudi','333333','吴迪',1);


CREATE TABLE t_role(
  id TINYINT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  sn VARCHAR(20) NOT NULL
);

INSERT INTO t_role(`name`,`sn`) VALUES
('管理员','admin'),
('开发工程师','dev'),
('测试工程师','test');

CREATE TABLE t_user_role(
  id TINYINT PRIMARY KEY AUTO_INCREMENT,
  user_id TINYINT NOT NULL,
  role_id TINYINT NOT NULL
);

INSERT INTO `t_user_role`(`user_id`,`role_id`)
VALUES(1,1),(2,1),(3,2),(4,3),(5,2);

CREATE TABLE t_resource(
  id TINYINT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  permission VARCHAR(40) NOT NULL,
  url VARCHAR(40) NOT NULL
);

INSERT INTO t_resource(`name`,permission,url)
VALUES('系统管理','admin:*','/admin/**'),
('用户管理','user:*','/admin/user/**'),
('用户添加','user:add','/admin/user/add'),
('用户删除','user:delete','/admin/delete/**'),
('角色管理','role:*','/admin/role/**'),
('角色添加','role:add','/admin/role/add'),
('角色修改','role:update','/admin/role/update'),
('资源管理','resource:*','/admin/resource/**');

CREATE TABLE t_role_resource(
  id TINYINT PRIMARY KEY AUTO_INCREMENT,
  role_id TINYINT,
  resource_id TINYINT
);

INSERT INTO t_role_resource(role_id,resource_id)
VALUES(1,1),(1,2),(1,5),(1,8),
(2,2),(2,5),(2,8),
(3,3),(3,4),(3,6),(3,7);

