-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.44 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  10.2.0.5623
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 page_views 的数据库结构
CREATE DATABASE IF NOT EXISTS `page_views` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `page_views`;

-- 导出  表 page_views.article 结构
CREATE TABLE IF NOT EXISTS `article` (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blog_user_id` bigint(20) DEFAULT NULL,
  `article_title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `article_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='这个用户下包含的博客';

-- 数据导出被取消选择。

-- 导出  表 page_views.blog_user 结构
CREATE TABLE IF NOT EXISTS `blog_user` (
  `blog_user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blog_user_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `blog_type` int(11) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`blog_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='博客用户表';

-- 数据导出被取消选择。

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
