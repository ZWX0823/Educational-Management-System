-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: ems
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admin` (
  `AdminID` varchar(15) NOT NULL,
  `AdminName` varchar(15) NOT NULL,
  `Phone` char(11) DEFAULT NULL,
  `AccountNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`AdminID`),
  UNIQUE KEY `AccountNumber_UNIQUE` (`AccountNumber`),
  KEY `Admin_AccountNumber_idx` (`AccountNumber`),
  CONSTRAINT `Admin_AccountNumber` FOREIGN KEY (`AccountNumber`) REFERENCES `login` (`accountnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('0','Admin','176xxxx1111','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_dept`
--

DROP TABLE IF EXISTS `admin_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admin_dept` (
  `AdminID` varchar(15) NOT NULL,
  `AdminName` varchar(15) DEFAULT NULL,
  `Phone` char(11) DEFAULT NULL,
  `Dept` char(2) NOT NULL,
  `AccountNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`AdminID`),
  KEY `AD_AccountNumber_idx` (`AccountNumber`),
  CONSTRAINT `AD_AccountNumber` FOREIGN KEY (`AccountNumber`) REFERENCES `login` (`accountnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_dept`
--

LOCK TABLES `admin_dept` WRITE;
/*!40000 ALTER TABLE `admin_dept` DISABLE KEYS */;
INSERT INTO `admin_dept` VALUES ('13001','Mr.蛮',NULL,'13','admin13');
/*!40000 ALTER TABLE `admin_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `class` (
  `ClassID` char(10) NOT NULL,
  `ClassName` varchar(45) NOT NULL,
  `Specialty` char(8) DEFAULT NULL,
  `Number` int(11) DEFAULT '0',
  `AdmissionDate` date DEFAULT NULL,
  PRIMARY KEY (`ClassID`),
  KEY `C_Specialty_idx` (`Specialty`),
  CONSTRAINT `C_Specialty` FOREIGN KEY (`Specialty`) REFERENCES `specialty_year` (`specialty_yearid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES ('2015130101','软件工程1班','20151301',1,'2015-09-01'),('2015130201','网络工程1班','20151302',0,'2015-09-01'),('2015130301','计算机科学与技术1班','20151303',0,'2015-09-01'),('2016130101','软件工程1班','20161301',0,'2016-09-01');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `classroom` (
  `ClassroomID` varchar(7) NOT NULL,
  `ClassroomName` varchar(45) NOT NULL,
  `Type` varchar(45) DEFAULT NULL,
  `Size` int(11) DEFAULT NULL,
  `Equipment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ClassroomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES ('0101101','明德N101','多媒体教室',100,'电脑，投影仪'),('0101102','明德N102','多媒体教室',100,'电脑，投影仪'),('0101103','明德N103','多媒体教室',100,'电脑，投影仪'),('0101104','明德N104','多媒体教室',100,'电脑，投影仪'),('0101105','明德N105','多媒体教室',100,'电脑，投影仪'),('0101106','明德N106','多媒体教室',100,'电脑，投影仪'),('0101107','明德N107','多媒体教室',100,'电脑，投影仪'),('0101201','明德N201','多媒体教室',100,'电脑，投影仪'),('0101202','明德N202','多媒体教室',100,'电脑，投影仪'),('0101203','明德N203','多媒体教室',100,'电脑，投影仪'),('0101204','明德N204','多媒体教室',100,'电脑，投影仪'),('0101205','明德N205','多媒体教室',100,'电脑，投影仪'),('0101206','明德N206','多媒体教室',100,'电脑，投影仪'),('0101207','明德N207','多媒体教室',100,'电脑，投影仪'),('0101301','明德N301','多媒体教室',100,'电脑，投影仪'),('0101302','明德N302','多媒体教室',100,'电脑，投影仪'),('0101303','明德N302','多媒体教室',100,'电脑，投影仪'),('0101304','明德N304','多媒体教室',100,'电脑，投影仪'),('0101305','明德N305','多媒体教室',100,'电脑，投影仪'),('0101306','明德N306','多媒体教室',100,'电脑，投影仪'),('0101307','明德N306','多媒体教室',100,'电脑，投影仪');
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course` (
  `CourseID` char(5) NOT NULL,
  `CourseName` varchar(45) NOT NULL,
  `DeptID` char(2) NOT NULL,
  `Period` int(11) NOT NULL,
  `Credit` int(11) NOT NULL,
  `CourseType` varchar(8) NOT NULL,
  `Weeks` int(11) DEFAULT NULL,
  `Specialty` char(4) DEFAULT NULL,
  PRIMARY KEY (`CourseID`),
  KEY `Course_DeptID_idx` (`DeptID`),
  KEY `Course_SpecialtyID_idx` (`Specialty`),
  CONSTRAINT `Course_DeptID` FOREIGN KEY (`DeptID`) REFERENCES `dept` (`deptid`),
  CONSTRAINT `Course_Specialty` FOREIGN KEY (`Specialty`) REFERENCES `specialty` (`specialtyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('01001','英语','01',32,6,'必修课',NULL,'0101'),('01002','商务英语','01',32,6,'必修课',NULL,'0101'),('13001','C语言','13',32,3,'公共课',NULL,'1301'),('13002','数据结构','13',32,4,'必修课',NULL,'1301'),('13003','网络安全','13',32,4,'必修课',NULL,'1302'),('13004','汇编语言','13',32,4,'必修课',NULL,'1301'),('13005','Java','13',32,4,'必修课',NULL,'1301'),('13006','Oracle数据库','13',32,4,'必修课',NULL,'1301'),('13007','计算机网络','13',32,4,'必修课',NULL,'1301'),('13008','离散数学','13',32,4,'必修课',NULL,'1301'),('13009','操作系统','13',32,4,'必修课',NULL,'1301');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dept` (
  `DeptID` char(2) NOT NULL,
  `DeptName` varchar(45) NOT NULL,
  `Location` varchar(45) DEFAULT NULL,
  `Number` int(11) DEFAULT '0',
  PRIMARY KEY (`DeptID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES ('01','文学院',NULL,NULL),('02','大气科学学院',NULL,NULL),('03','地理科学学院',NULL,NULL),('04','海洋科学学院',NULL,NULL),('05','电子与信息工程学院',NULL,NULL),('06','物理学院',NULL,NULL),('07','传媒与艺术学院',NULL,NULL),('08','商学院',NULL,NULL),('09','法政学院',NULL,NULL),('10','数学与统计学院',NULL,NULL),('11','马克思主义学院',NULL,NULL),('12','体育学院',NULL,NULL),('13','计算机与软件学院',NULL,NULL);
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login` (
  `AccountNumber` varchar(20) NOT NULL,
  `Password` varchar(25) NOT NULL,
  `Role` char(1) NOT NULL,
  PRIMARY KEY (`AccountNumber`),
  KEY `UserLogin_Role_idx` (`Role`),
  CONSTRAINT `UserLogin_Role` FOREIGN KEY (`Role`) REFERENCES `role` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('01001','123','1'),('01002','123','1'),('13001','123','1'),('13002','123','1'),('13003','123','1'),('13004','123','1'),('20151301018','123','2'),('admin','admin','0'),('admin13','123','3');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `RoleID` char(1) NOT NULL,
  `RoleName` varchar(15) NOT NULL,
  `Permission` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('0','admin',NULL),('1','teacher',NULL),('2','student',NULL),('3','dept',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialty`
--

DROP TABLE IF EXISTS `specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `specialty` (
  `SpecialtyID` char(4) NOT NULL,
  `Dept` char(2) DEFAULT NULL,
  `SpecialtyName` varchar(45) NOT NULL,
  PRIMARY KEY (`SpecialtyID`),
  KEY `Specialty_DeptID_idx` (`Dept`),
  CONSTRAINT `Specialty_Dept` FOREIGN KEY (`Dept`) REFERENCES `dept` (`deptid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialty`
--

LOCK TABLES `specialty` WRITE;
/*!40000 ALTER TABLE `specialty` DISABLE KEYS */;
INSERT INTO `specialty` VALUES ('0101','01','英语'),('0102','01','日语'),('0103','01','法语'),('0104','01','对外汉语'),('0105','01','翻译'),('0201','02','大气科学'),('0202','02','大气科学（气候学方向）'),('0203','02','大气科学（气候资源方向）'),('0301','03','地理信息科学'),('0302','03','人文地理与城市规划'),('0303','03','自然地理与资源环境'),('0401','04','海洋技术'),('0402','04','海洋科学'),('0501','05','电子科学与技术'),('0502','05','电子信息工程'),('0503','05','通信工程'),('0504','05','信息工程'),('0601','06','材料物理'),('0602','06','物理学'),('0603','06','应用物理学'),('0701','07','动画'),('0702','07','数字媒体艺术'),('0703','07','艺术与科技'),('0801','08','财务管理'),('0802','08','工商管理'),('0803','08','国际经济与贸易'),('0804','08','会计学'),('0805','08','人力资源'),('0901','09','法学'),('0902','09','公共事业管理'),('0903','09','行政管理'),('1001','10','数学与应用数学'),('1002','10','统计学'),('1003','10','应用统计学'),('1101','11','马克思主义'),('1201','12','体育'),('1301','13','软件工程'),('1302','13','网络工程'),('1303','13','计算机科学与技术'),('1304','13','网络安全');
/*!40000 ALTER TABLE `specialty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialty_year`
--

DROP TABLE IF EXISTS `specialty_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `specialty_year` (
  `Specialty_YearID` char(8) NOT NULL,
  `Specialty_YearName` varchar(45) NOT NULL,
  `Specialty` char(4) DEFAULT NULL,
  `Number` int(11) DEFAULT '0',
  `AdmissionDate` date DEFAULT NULL,
  `Finish` char(1) DEFAULT '0' COMMENT '是否毕业0：未毕业 1：毕业',
  `Dept` char(2) DEFAULT NULL,
  `ClassNumber` smallint(2) DEFAULT '0',
  PRIMARY KEY (`Specialty_YearID`),
  KEY `SY_Specialty_idx` (`Specialty`),
  KEY `SY_Dept_idx` (`Dept`),
  CONSTRAINT `SY_Dept` FOREIGN KEY (`Dept`) REFERENCES `dept` (`deptid`),
  CONSTRAINT `SY_Specialty` FOREIGN KEY (`Specialty`) REFERENCES `specialty` (`specialtyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialty_year`
--

LOCK TABLES `specialty_year` WRITE;
/*!40000 ALTER TABLE `specialty_year` DISABLE KEYS */;
INSERT INTO `specialty_year` VALUES ('20141301','2014级软件工程','1301',0,'2014-09-01','0','13',0),('20150101','2015级英语专业','0101',0,'2015-09-01','0','01',0),('20151301','2015级软件工程','1301',1,'2015-09-01','0','13',1),('20151302','2015级网络工程','1302',0,'2015-09-01','0','13',1),('20151303','2015级计算机科学与技术','1303',0,'2015-09-01','0','13',1),('20151304','2015级网络安全','1304',0,'2015-09-01','0','13',0),('20161301','2016级软件工程','1301',0,'2016-09-01','0','13',1),('20161302','2016级网络工程','1302',0,'2016-09-01','0','13',0),('20161303','2016级计算机科学与技术','1303',0,'2016-09-01','0','13',0),('20161304','2016级网络安全','1304',0,'2016-09-01','0','13',0),('20170101','2017级英语专业','0101',0,'2017-09-01','0','01',0),('20171301','2017级软件工程','1301',0,'2017-09-01','0','13',0),('20171302','2017级网络工程','1302',0,'2017-09-01','0','13',0),('20171303','2017级计算机科学与技术','1303',0,'2017-09-01','0','13',0),('20171304','2017级网络安全','1304',0,'2017-09-01','0','13',0),('20181301','2018级软件工程','1301',0,'2018-09-01','0','13',0),('20181302','2018级网络工程','1302',0,'2018-09-01','0','13',0),('20181303','2018级计算机科学与技术','1303',0,'2018-09-01','0','13',0),('20181304','2018级网络安全','1304',0,'2018-09-01','0','13',0);
/*!40000 ALTER TABLE `specialty_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialty_year_course`
--

DROP TABLE IF EXISTS `specialty_year_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `specialty_year_course` (
  `Specialty_YearID` char(8) NOT NULL,
  `CourseID` char(5) NOT NULL,
  `Time` char(2) DEFAULT NULL,
  PRIMARY KEY (`Specialty_YearID`,`CourseID`),
  KEY `SYC_cID_idx` (`CourseID`),
  CONSTRAINT `SYC_cID` FOREIGN KEY (`CourseID`) REFERENCES `course` (`courseid`),
  CONSTRAINT `SYC_sycID` FOREIGN KEY (`Specialty_YearID`) REFERENCES `specialty_year` (`specialty_yearid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialty_year_course`
--

LOCK TABLES `specialty_year_course` WRITE;
/*!40000 ALTER TABLE `specialty_year_course` DISABLE KEYS */;
INSERT INTO `specialty_year_course` VALUES ('20150101','01001','10'),('20151301','13001','10'),('20151301','13002','11'),('20181301','13001','11'),('20181301','13002','11'),('20181301','13004','11'),('20181301','13005','11'),('20181301','13006','11'),('20181301','13007','11'),('20181301','13008','11'),('20181301','13009','11');
/*!40000 ALTER TABLE `specialty_year_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `StudentID` char(11) NOT NULL,
  `StudentName` varchar(15) NOT NULL,
  `Sex` char(2) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `ClassID` char(10) DEFAULT NULL,
  `Phone` char(11) DEFAULT NULL,
  `AccountNumber` varchar(20) DEFAULT NULL,
  `Specialty` char(4) DEFAULT NULL,
  `Dept` char(2) DEFAULT NULL,
  `EntranceYear` char(4) DEFAULT NULL,
  PRIMARY KEY (`StudentID`),
  UNIQUE KEY `AccountNumber_UNIQUE` (`AccountNumber`),
  KEY `Student_AccountNumber_idx` (`AccountNumber`),
  KEY `Student_Dept_idx` (`Dept`),
  KEY `Student_Class_idx` (`ClassID`),
  KEY `Student_Specialty_idx` (`Specialty`),
  CONSTRAINT `Student_AccountNumber` FOREIGN KEY (`AccountNumber`) REFERENCES `login` (`accountnumber`),
  CONSTRAINT `Student_Class` FOREIGN KEY (`ClassID`) REFERENCES `class` (`classid`),
  CONSTRAINT `Student_Dept` FOREIGN KEY (`Dept`) REFERENCES `dept` (`deptid`),
  CONSTRAINT `Student_Specialty` FOREIGN KEY (`Specialty`) REFERENCES `specialty` (`specialtyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('20151301018','Mr.张','男','1997-07-22','2015130101','176xxxx1111','20151301018','1301','13','2015');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student_course` (
  `StudentID` char(11) NOT NULL,
  `Teacher_CourseID` char(7) NOT NULL,
  `Grade` int(11) DEFAULT NULL,
  `Comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`StudentID`,`Teacher_CourseID`),
  KEY `SC_TeacherCourse_idx` (`Teacher_CourseID`),
  CONSTRAINT `SC_Student` FOREIGN KEY (`StudentID`) REFERENCES `student` (`studentid`),
  CONSTRAINT `SC_TeacherCourse` FOREIGN KEY (`Teacher_CourseID`) REFERENCES `teacher_course` (`teacher_courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES ('20151301018','1300101',99,NULL),('20151301018','1300102',55,NULL),('20151301018','1300201',NULL,NULL),('20151301018','1300202',NULL,NULL),('20151301018','1300401',NULL,NULL);
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teacher` (
  `TeacherID` char(5) NOT NULL,
  `TeacherName` varchar(15) NOT NULL,
  `Sex` char(2) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `DeptID` char(2) DEFAULT NULL,
  `Phone` char(11) DEFAULT NULL,
  `AccountNumber` varchar(20) DEFAULT NULL,
  `Degree` char(5) DEFAULT NULL,
  `Title` char(10) DEFAULT NULL,
  `Comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TeacherID`),
  UNIQUE KEY `AccountNumber_UNIQUE` (`AccountNumber`),
  KEY `Teacher_DeptID_idx` (`DeptID`),
  KEY `Teacher_AcountNumber_idx` (`AccountNumber`),
  CONSTRAINT `Teacher_AccountNumber` FOREIGN KEY (`AccountNumber`) REFERENCES `login` (`accountnumber`),
  CONSTRAINT `Teacher_DeptID` FOREIGN KEY (`DeptID`) REFERENCES `dept` (`deptid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('01001','Mrs.YU','女','1998-07-21','01','123456789','01001','博士后','教授',NULL),('01002','Mrs.LIU','女','1997-08-21','01','13465789','01002','博士后','副教授',NULL),('13001','Mr.王','男','1990-08-19','13','12345678900','13001','博士','教授',NULL),('13002','Mrs.何','女','1997-01-01','13','123','13002','博士后','教授',NULL),('13003','Mr.马','男','1991-06-05','13','123456789','13003','博士后','教授',NULL),('13004','Mr.萨','男','1992-01-01','13','123456','13004','博士后','教授',NULL);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_course`
--

DROP TABLE IF EXISTS `teacher_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teacher_course` (
  `Teacher_CourseID` char(7) NOT NULL,
  `TeacherID` char(5) DEFAULT NULL,
  `CourseID` char(5) DEFAULT NULL,
  `Number` int(11) DEFAULT '0',
  PRIMARY KEY (`Teacher_CourseID`),
  KEY `TC_Teacher_idx` (`TeacherID`),
  KEY `TC_Course_idx` (`CourseID`),
  CONSTRAINT `TC_Course` FOREIGN KEY (`CourseID`) REFERENCES `course` (`courseid`),
  CONSTRAINT `TC_teacher` FOREIGN KEY (`TeacherID`) REFERENCES `teacher` (`teacherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_course`
--

LOCK TABLES `teacher_course` WRITE;
/*!40000 ALTER TABLE `teacher_course` DISABLE KEYS */;
INSERT INTO `teacher_course` VALUES ('0100101','01001','01001',0),('1300101','13001','13002',1),('1300102','13001','13001',1),('1300103','13001','13008',0),('1300201','13002','13001',1),('1300202','13002','13002',1),('1300203','13002','13009',0),('1300301','13003','13004',0),('1300302','13003','13005',0),('1300401','13004','13006',1),('1300402','13004','13007',0);
/*!40000 ALTER TABLE `teacher_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `term`
--

DROP TABLE IF EXISTS `term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `term` (
  `TermID` char(5) NOT NULL,
  `TermName` varchar(45) NOT NULL,
  PRIMARY KEY (`TermID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `term`
--

LOCK TABLES `term` WRITE;
/*!40000 ALTER TABLE `term` DISABLE KEYS */;
INSERT INTO `term` VALUES ('20150','2015-2016学年第1学期'),('20151','2015-2016学年第2学期'),('20160','2016-2017学年第1学期'),('20161','2016-2017学年第2学期'),('20170','2017-2018学年第1学期'),('20172','2017-2018学年第2学期'),('20180','2018-2019学年第1学期'),('20181','2018-2019学年第2学期');
/*!40000 ALTER TABLE `term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time`
--

DROP TABLE IF EXISTS `time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `time` (
  `TimeID` char(2) NOT NULL,
  `TimeName` varchar(45) NOT NULL,
  PRIMARY KEY (`TimeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time`
--

LOCK TABLES `time` WRITE;
/*!40000 ALTER TABLE `time` DISABLE KEYS */;
INSERT INTO `time` VALUES ('11','Mon 1-2'),('12','Mon 3-4'),('13','Mon 5-6'),('14','Mon 7-8'),('21','Tue 1-2'),('22','Tue 3-4'),('23','Tue 5-6'),('24','Tue 7-8'),('31','Wed 1-2'),('32','Wed 3-4'),('33','Wed 5-6'),('34','Wed 7-8'),('41','Thu 1-2'),('42','Thu 3-4'),('43','Thu 5-6'),('44','Thu 7-8'),('51','Fri 1-2'),('52','Fri 3-4'),('53','Fri 5-6'),('54','Fri 7-8'),('61','Sat 1-2'),('62','Sat 3-4'),('63','Sat 5-6'),('64','Sat 7-8'),('71','Sun 1-2'),('72','Sun 3-4'),('73','Sun 5-6'),('74','Sun 7-8');
/*!40000 ALTER TABLE `time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timetable`
--

DROP TABLE IF EXISTS `timetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `timetable` (
  `Teacher_CourseID` char(7) NOT NULL,
  `Time` char(2) NOT NULL,
  `Term` char(5) DEFAULT NULL,
  `Classroom` varchar(7) NOT NULL,
  `Specialty_Year` char(8) NOT NULL,
  PRIMARY KEY (`Teacher_CourseID`,`Specialty_Year`,`Classroom`,`Time`),
  KEY `TT_Term_idx` (`Term`),
  KEY `TT_Time_idx` (`Time`),
  KEY `TT_Classroom_idx` (`Classroom`),
  KEY `TT_Specialty_Year_idx` (`Specialty_Year`),
  CONSTRAINT `TT_Classroom` FOREIGN KEY (`Classroom`) REFERENCES `classroom` (`classroomid`),
  CONSTRAINT `TT_Specialty_Year` FOREIGN KEY (`Specialty_Year`) REFERENCES `specialty_year` (`specialty_yearid`),
  CONSTRAINT `TT_TeacherCourse` FOREIGN KEY (`Teacher_CourseID`) REFERENCES `teacher_course` (`teacher_courseid`),
  CONSTRAINT `TT_Term` FOREIGN KEY (`Term`) REFERENCES `term` (`termid`),
  CONSTRAINT `TT_Time` FOREIGN KEY (`Time`) REFERENCES `time` (`timeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timetable`
--

LOCK TABLES `timetable` WRITE;
/*!40000 ALTER TABLE `timetable` DISABLE KEYS */;
INSERT INTO `timetable` VALUES ('1300101','14','20181','0101203','20181301'),('1300102','52','20181','0101302','20181301'),('1300103','33','20181','0101204','20181301'),('1300203','44','20181','0101306','20181301'),('1300301','24','20181','0101301','20181301'),('1300302','34','20181','0101201','20181301'),('1300401','32','20181','0101303','20181301'),('1300402','21','20181','0101307','20181301');
/*!40000 ALTER TABLE `timetable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ems'
--

--
-- Dumping routines for database 'ems'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-09 14:55:22
