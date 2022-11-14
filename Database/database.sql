CREATE DATABASE CollegeModule;

USE CollegeModule;


CREATE TABLE Admin
(
username VARCHAR(50),
password VARCHAR(50)
);


CREATE TABLE Faculty
(
facultyId INT PRIMARY KEY auto_increment,
facultyFname VARCHAR(50),
facultyLname VARCHAR(50),
facultyAddress VARCHAR(50),
facultyState VARCHAR(50),
facultyPin VARCHAR(50),
mobile VARCHAR(50),
email VARCHAR(50),
username VARCHAR(50),
password VARCHAR(50)
);


CREATE TABLE Course
(
courseId INT PRIMARY KEY auto_increment,
courseName VARCHAR(50),
courseFee INT,
courseDesc VARCHAR(50)
);



CREATE TABLE Batch
(
batchId VARCHAR(50) PRIMARY KEY ,
courseId INT,
facultyId INT,
noOfStudents INT,
batchstartDate DATE,
duration VARCHAR(50),
FOREIGN KEY (courseId) references Course (courseId),
FOREIGN KEY (facultyId) references faculty (facultyId)
);


CREATE TABLE Courseplan
(
planId INT PRIMARY KEY auto_increment,
batchId VARCHAR(50),
daynumber INT,
topic VARCHAR(50),
planDate DATE,
status BOOLEAN,
FOREIGN KEY (batchId) references Batch (batchId)
);
