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