DROP TABLE IF EXISTS Student;
DROP TABLE IF EXISTS StudentCourses;

Create Table Student
 (
 	Student_Id Number(5) Primary Key,
 	Student_Name Varchar2(35) Not Null,
 	Student_Score Number(5, 2) Not Null,
 	City Varchar2(45) Not Null,
 	State Varchar2(45) Not Null,
 	Pin Varchar2(10) Not Null
 );
Create Table StudentCourses
(
	Course_Id Number(4) Primary Key,
	Course_Name Varchar2(40) Not Null,
	Course_Fees Number(5) Not Null,
	Student_Id Number(5)
);

INSERT INTO Student (Student_Id, Student_Name, Student_Score, City, State, Pin)
VALUES (1, 'Makarand Bhoir', 60, 'Mumbai', 'Maharashtra', '400706');

INSERT INTO Student (Student_Id, Student_Name, Student_Score, City, State, Pin)
VALUES (2, 'Mahesh Shinde', 87, 'Pune', 'Maharashtra', '411224');

INSERT INTO Student (Student_Id, Student_Name, Student_Score, City, State, Pin)
VALUES (3, 'Gaurav Gupta', 50, 'Mumbai', 'Maharashtra', '400059');

INSERT INTO StudentCourses(Course_Id, Course_Name, Course_Fees, Student_Id)
VALUES(1111, 'Java', 20000, 1);

INSERT INTO StudentCourses(Course_Id, Course_Name, Course_Fees, Student_Id)
VALUES(1114, 'Azure Developer', 20000, 1);

INSERT INTO StudentCourses(Course_Id, Course_Name, Course_Fees, Student_Id)
VALUES(1112, 'Azure Developer', 25000, 2);

INSERT INTO StudentCourses(Course_Id, Course_Name, Course_Fees, Student_Id)
VALUES(1113, 'AWS Developer', 27000, 3);
