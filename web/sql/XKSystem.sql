/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.47 : Database - zucc_course_selection_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zucc_course_selection_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zucc_course_selection_system`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `access_level` int(11) NOT NULL DEFAULT '1' COMMENT '权限等级',
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`name`,`password`,`access_level`) values (1,'admin1','123456',1),(4,'admin2','5555',1),(5,'admin','123456',2),(7,'admin10','123456',2),(8,'admin7','123456',2);

/*Table structure for table `clazz` */

DROP TABLE IF EXISTS `clazz`;

CREATE TABLE `clazz` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `selected_num` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `clazz` */

insert  into `clazz`(`id`,`name`,`selected_num`) values (1,'通信1703',7),(2,'自动化1704',6),(3,'电科1705',0),(4,'电信1705',0);

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `teacher_id` int(5) NOT NULL,
  `course_date` varchar(32) DEFAULT NULL,
  `course_place` varchar(10) NOT NULL,
  `selected_num` int(5) NOT NULL DEFAULT '0',
  `max_num` int(5) NOT NULL DEFAULT '50',
  PRIMARY KEY (`id`),
  KEY `course_teacher_foreign` (`teacher_id`),
  CONSTRAINT `course_teacher_foreign` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`name`,`teacher_id`,`course_date`,`course_place`,`selected_num`,`max_num`) values (23,'大学英语',15,'周二下午678','教六210',4,60),(25,'电路原理',14,'周二下午678','教三205',4,50),(26,'电路原理',14,'周二下午678','教三205',2,50),(28,'电路原理',14,'周二下午678','教三205',1,50);

/*Table structure for table `selected_clazz` */

DROP TABLE IF EXISTS `selected_clazz`;

CREATE TABLE `selected_clazz` (
  `clazz_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  KEY `selected_clazz_fk` (`clazz_id`),
  KEY `selected_clazz_student_fk` (`student_id`),
  CONSTRAINT `selected_clazz_fk` FOREIGN KEY (`clazz_id`) REFERENCES `clazz` (`id`),
  CONSTRAINT `selected_clazz_student_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `selected_clazz` */

/*Table structure for table `selected_course` */

DROP TABLE IF EXISTS `selected_course`;

CREATE TABLE `selected_course` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `student_id` int(5) NOT NULL,
  `course_id` int(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `selected_course_student_fk` (`student_id`),
  KEY `selected_course_course_fk` (`course_id`),
  CONSTRAINT `selected_course_course_fk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `selected_course_student_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `selected_course` */

insert  into `selected_course`(`id`,`student_id`,`course_id`) values (43,12,25),(45,12,23),(46,14,26),(47,14,25),(48,14,23),(50,14,28);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `sex` varchar(5) NOT NULL DEFAULT '男',
  `clazz_id` int(11) NOT NULL COMMENT '所属班级',
  PRIMARY KEY (`id`,`number`),
  KEY `id` (`id`),
  KEY `fk_clazz_id` (`clazz_id`),
  CONSTRAINT `fk_clazz_id` FOREIGN KEY (`clazz_id`) REFERENCES `clazz` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`number`,`name`,`password`,`sex`,`clazz_id`) values (12,'31901565784216528','李四','1234567','男',1),(13,'31901565784225561','王五','123456','男',2),(14,'31901565784239833','张三','123456','女',3),(17,'31901569072586868','唐七','123456','男',1),(18,'31901569073863136','赵六','123456','女',2),(20,'31901569082440866','朝九','123456','男',4),(21,'31901569128519054','唐七','123456','男',4);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `sex` varchar(5) NOT NULL DEFAULT '男',
  PRIMARY KEY (`id`,`number`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`id`,`number`,`name`,`password`,`sex`) values (14,'91565784261352','张老师','123456','男'),(15,'91565784273346','王老师','6666','男');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
