CREATE TABLE Students
(
  studentId INT AUTO_INCREMENT,
  stName VARCHAR(30) NOT NULL,
  email VARCHAR(40),
  gpa FLOAT DEFAULT 0.0,
  PRIMARY KEY (studentId),
  CONSTRAINT CHECK_GPA
  CHECK (gpa BETWEEN 0 AND 4.3)
);

CREATE TABLE Enrollments
(
  studentId INT,
  courseId INT,
  letterGrade CHAR(2),
  PRIMARY KEY(studentId, courseId),
  FOREIGN KEY(studentId)
    REFERENCES Students(studentId)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);

CREATE TABLE Courses
(
  courseId INT AUTO_INCREMENT,
  courseNumber VARCHAR(10) NOT NULL,
  courseName VARCHAR(50) NOT NULL,
  courseDescription VARCHAR(100) NOT NULL,
  creditHours INT DEFAULT 3,
  PRIMARY KEY(courseId),
  CONSTRAINT CHECK_CREDITHOURS
  CHECK (creditHours BETWEEN 0 AND 6)
);

INSERT INTO Courses(creditHours)
VALUE(10);

INSERT INTO Courses
VALUE(10, 'CS1103', 'Database Fundamentals', 'Learning the basics of databases', 6, '2021-04-21');
-- -------------------------------------------------------------

INSERT INTO Students
VALUE(3325572, 'Amy Olson', 'amyolson@gmail.com', 3.2);

INSERT INTO Students
VALUE(5532011, 'John Doe', 'johndoe@gmail.com', 2.1);

INSERT INTO Students
VALUE(5533222, 'Kristyn Webber', 'krisweb@gmail.com', 3.1);

INSERT INTO Courses
VALUE(2,'QWE1222','Marketing Fundamentals', 'basics of Marketing', 6, '2021-04-21');

INSERT INTO Courses
VALUE(3,'AE1222','Business Fundamentals', 'basics of Business',  6, '2021-04-21');

INSERT INTO Courses
VALUE();
