-- create database
DROP DATABASE IF EXISTS TestingSystem;
CREATE DATABASE TestingSystem;
USE TestingSystem;

-- create table: Department
DROP TABLE IF EXISTS Department;
CREATE TABLE Department(
	DepartmentID 			TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    DepartmentName 			NVARCHAR(30) NOT NULL UNIQUE KEY,
    TotalMember				TINYINT  DEFAULT 1,
    author_id				TINYINT UNSIGNED,
    CreateDate				DATETIME DEFAULT NOW(),
    ModifiedDate			DATETIME DEFAULT NOW()
);

-- create table: Account
DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account`(
	AccountID				TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Email					VARCHAR(50) NOT NULL UNIQUE KEY, 
    Username				VARCHAR(50) NOT NULL UNIQUE KEY, 
    `password`				VARCHAR(800) NOT NULL,
    FirstName				NVARCHAR(50) NOT NULL,
    LastName				NVARCHAR(50) NOT NULL,	
    DepartmentID 			TINYINT UNSIGNED,	
    `role` 					ENUM('Admin','Employee','Manager') NOT NULL DEFAULT 'Employee',
    CreateDate				DATETIME DEFAULT NOW(),
    
        
    FOREIGN KEY(DepartmentID) REFERENCES Department(DepartmentID)  ON DELETE SET NULL
);

-- create table: RefreshTokenCode
DROP TABLE IF EXISTS `Token`;
CREATE TABLE `Token`(
	TokenID					TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	RefreshTokenCode		VARCHAR(200) NOT NULL,
    Username				VARCHAR(50),
    CreateDate				DATETIME DEFAULT NOW(),
    
	FOREIGN KEY(Username) 	REFERENCES `Account`(Username)  ON DELETE SET NULL
);

