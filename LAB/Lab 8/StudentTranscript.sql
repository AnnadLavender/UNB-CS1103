DELIMITER $$
CREATE PROCEDURE studentTranscript
(
  stId INT
)
BEGIN
  IF EXISTS(SELECT * FROM Students WHERE studentId = stId) THEN
    IF NOT EXISTS(SELECT * FROM Enrollments WHERE studentId = stId) THEN
      SELECT '';
    ELSE
      SELECT Courses.courseId, courseNumber, courseName,letterGrade
      FROM Students LEFT JOIN Enrollments ON Students.studentId = Enrollments.studentId LEFT JOIN Courses ON Enrollments.courseId = Courses.courseId
      WHERE Students.studentId = stId;
    END IF;
  ELSE
    SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Student ID not found';
  END IF;
END $$

DELIMITER ;
