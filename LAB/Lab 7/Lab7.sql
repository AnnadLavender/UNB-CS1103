ALTER TABLE Students ADD scholarship INT;

DELIMITER $$
  CREATE PROCEDURE AcceptStudent
  (
    name VARCHAR(50),
    email VARCHAR(50),
    average FLOAT
  )
  BEGIN
    DECLARE scholarship INT;
    IF average<=100 AND average>90 THEN
      SET scholarship = 1500;
    ELSEIF average <=90 AND average >85 THEN
      SET scholarship = 1000;
    ELSEIF average < 85 AND average>=0 THEN
      SET scholarship = 0;
      ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'The mark is out of range';
    END IF;
    INSERT INTO Students(stName, email, scholarship)
    VALUE(name, email, scholarship);
  END$$

DELIMITER ;

delete from Students where gpa=0;
ALTER TABLE Students DROP COLUMN scholarship;
DROP PROCEDURE AcceptStudent;

CALL AcceptStudent('Kevin Smith','kevinsmith@gmail.com',95);
CALL AcceptStudent('Eddy Jones','eddyjones@gmail.com',80);
CALL AcceptStudent('Eric Thomas','ericthomas@gmail.com',91);
CALL AcceptStudent('Envy Walker','envywalker@gmail.com',101);
