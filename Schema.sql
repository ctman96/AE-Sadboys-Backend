CREATE DATABASE  IF NOT EXISTS `recordr` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `recordr`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: recordr
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `classhierarchy`
--

DROP TABLE IF EXISTS `classhierarchy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classhierarchy` (
  `Id` INT(11) NOT NULL,
  `ParentId` INT(11) NOT NULL,
  `Rel` INT(11) NOT NULL,
  `ChildId` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_ClassHierarchy_ParentId` (`ParentId`),
  KEY `FK_ClassHierarchy_ChildId` (`ChildId`),
  CONSTRAINT `FK_ClassHierarchy_ParentId`
    FOREIGN KEY (`ParentId`)
    REFERENCES `recordr`.`classifications` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ClassHierarchy_ChildId`
    FOREIGN KEY (`ChildId`)
    REFERENCES `recordr`.`classifications` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `classifications`
--

DROP TABLE IF EXISTS `classifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classifications` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `KeyWord` char(1) CHARACTER SET utf8mb4 NOT NULL DEFAULT 'F',
  `UpdatedAt` datetime(6) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `IX_Tags_Name` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `containers`
--

DROP TABLE IF EXISTS `containers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `containers` (
  `Id` int(11) NOT NULL,
  `Number` varchar(30) CHARACTER SET utf8mb4 NOT NULL,
  `Title` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `ConsignmentCode` varchar(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `CreatedAt` datetime(6) NOT NULL,
  `UpdatedAt` datetime(6) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customattributelookups`
--

DROP TABLE IF EXISTS `customattributelookups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customattributelookups` (
  `Id` int(11) NOT NULL,
  `SetId` int(11) NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customattributes`
--

DROP TABLE IF EXISTS `customattributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customattributes` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `LookupId` int(11) DEFAULT NULL,
  KEY `Ffk_customattributeslookup` (`LookupId`),
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_customattributeslookup`
    FOREIGN KEY (`LookupId`)
    REFERENCES `recordr`.`customattributelookups` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customattributevalues`
--

DROP TABLE IF EXISTS `customattributevalues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customattributevalues` (
  `Id` int(11) NOT NULL,
  `AttrId` int(11) NOT NULL,
  `RecordId` int(11) NOT NULL,
  `Value` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_CustomAttributeValues_Attributes` (`AttrId`),
  KEY `FK_CustomAttributeValues_Records` (`RecordId`),
  CONSTRAINT `FK_CustomAttributeValues_Attributes`
    FOREIGN KEY (`AttrId`)
    REFERENCES `recordr`.`customattributes` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CustomAttributeValues_Records`
    FOREIGN KEY (`RecordId`)
    REFERENCES `recordr`.`records` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `labelcolours`
--

DROP TABLE IF EXISTS `labelcolours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `labelcolours` (
  `Key` varchar(10) CHARACTER SET utf8mb4 NOT NULL,
  `Colour` char(6) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`Key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locations` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `Code` char(10) CHARACTER SET utf8mb4 NOT NULL,
  `Locked` CHAR(1) CHARACTER SET utf8mb4 NOT NULL DEFAULT 'F',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `AK_Locations_Name` (`Name`),
  KEY `IX_Locations_Name` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes` (
  `TableId` int(11) NOT NULL,
  `RowId` int(11) NOT NULL,
  `Chunk` int(11) NOT NULL,
  `Text` longtext CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`TableId`,`RowId`,`Chunk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notetables`
--

DROP TABLE IF EXISTS `notetables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notetables` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recordclassifications`
--

DROP TABLE IF EXISTS `recordclassifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recordclassifications` (
  `RecordId` int(11) NOT NULL,
  `ClassId` int(11) NOT NULL,
  `Ordinal` int(11) NOT NULL,
  UNIQUE KEY `AK_RecordClassifications_RecordClass` (`RecordId`,`ClassId`),
  KEY `FK_RecordClassifications_Classifications` (`ClassId`),
  CONSTRAINT `FK_RecordClassifications_Classifications`
    FOREIGN KEY (`ClassId`)
    REFERENCES `recordr`.`classifications` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_RecordClassifications_Records`
    FOREIGN KEY (`RecordId`)
    REFERENCES `recordr`.`records` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `records`
--

DROP TABLE IF EXISTS `records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `records` (
  `Id` int(11) NOT NULL,
  `Number` varchar(30) CHARACTER SET utf8mb4 NOT NULL,
  `Title` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `ScheduleId` int(11) DEFAULT NULL,
  `TypeId` int(11) NOT NULL,
  `ConsignmentCode` varchar(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `StateId` int(11) NOT NULL DEFAULT '1',
  `ContainerId` int(11) DEFAULT NULL,
  `LocationId` int(11) DEFAULT NULL,
  `CreatedAt` datetime(6) NOT NULL,
  `UpdatedAt` datetime(6) NOT NULL,
  `ClosedAt` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_Records_Schedule` (`ScheduleId`),
  KEY `FK_Records_RecordTypes` (`TypeId`),
  KEY `FK_Records_Locations` (`LocationId`),
  KEY `FK_Records_RecordStates` (`StateId`),
  KEY `FK_Records_Containers` (`ContainerId`),
  CONSTRAINT `FK_Records_Containers`
    FOREIGN KEY (`ContainerId`)
    REFERENCES `recordr`.`containers` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Records_Locations`
    FOREIGN KEY (`LocationId`)
    REFERENCES `recordr`.`locations` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Records_RecordStates`
    FOREIGN KEY (`StateId`)
    REFERENCES `recordr`.`recordstates` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Records_RecordTypes`
    FOREIGN KEY (`TypeId`)
    REFERENCES `recordr`.`recordtypes` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Records_Schedule`
    FOREIGN KEY (`ScheduleId`)
    REFERENCES `recordr`.`retentionschedules` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recordstates`
--

DROP TABLE IF EXISTS `recordstates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recordstates` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `AK_RecordStates_Name` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recordtypes`
--

DROP TABLE IF EXISTS `recordtypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recordtypes` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `NumberPattern` varchar(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `DefaultScheduleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `IX_RecordTypes_Name` (`Name`),
  KEY `FK_RecordTypes_RetentionSchedules` (`DefaultScheduleId`),
  CONSTRAINT `FK_RecordTypes_RetentionSchedules`
    FOREIGN KEY (`DefaultScheduleId`)
    REFERENCES `recordr`.`retentionschedules` (`Id`)
    ON DELETE NO ACTION)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `retentionschedules`
--

DROP TABLE IF EXISTS `retentionschedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `retentionschedules` (
  `Id` int(11) NOT NULL,
  `Name` varchar(200) CHARACTER SET utf8mb4 NOT NULL,
  `Code` varchar(30) CHARACTER SET utf8mb4 NOT NULL,
  `Years` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `IX_RetentionSchedules_Code` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `AK_Roles_Name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userlocations`
--

DROP TABLE IF EXISTS `userlocations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userlocations` (
  `UserId` int(11) NOT NULL,
  `LocationId` int(11) NOT NULL,
  PRIMARY KEY (`UserId`,`LocationId`),
  KEY `FK_UserLocations_Locations` (`LocationId`),
  CONSTRAINT `FK_UserLocations_Locations`
    FOREIGN KEY (`LocationId`)
    REFERENCES `recordr`.`locations` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_UserLocations_Users`
    FOREIGN KEY (`UserId`)
    REFERENCES `recordr`.`users` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userroles`
--

DROP TABLE IF EXISTS `userroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userroles` (
  `UserId` int(11) NOT NULL,
  `RoleId` int(11) NOT NULL,
  PRIMARY KEY (`UserId`,`RoleId`),
  KEY `FK_UserRoles_Roles` (`RoleId`),
  CONSTRAINT `FK_UserRoles_Roles`
    FOREIGN KEY (`RoleId`)
    REFERENCES `recordr`.`roles` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_UserRoles_Users`
    FOREIGN KEY (`UserId`)
    REFERENCES `recordr`.`users` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `Id` int(11) NOT NULL,
  `UserId` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `FirstName` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `LastName` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `AK_Users_UserId` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-16 15:45:16
