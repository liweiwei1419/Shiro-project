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
  id TINYINT PRIMARY KEY,
  user_id TINYINT NOT NULL,
  role_id TINYINT NOT NULL
);

