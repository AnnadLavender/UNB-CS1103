DROP TABLE IF EXISTS schools;
CREATE TABLE Schools
(
  schoolId INT NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  province varchar(20) DEFAULT NULL,
  language char(2) DEFAULT NULL,
  level varchar(10) DEFAULT NULL,
  PRIMARY KEY (schoolId)
);

-- Add Schools records:
INSERT INTO Schools(name, province, language, level)
VALUES
(
  'Westisle Composite High School','Prince Edward Island','EN','High'
),
(
  'Yarmouth Consolidated High School','Nova Scotia','EN','High'
),
(
  'École Grande Rivière Secondary School','New Brunswick','FR','High'
),
(
  'École Marie-Gaétane High School','New Brunswick','FR','High'
),
(
  'Cambridge Narrows Community School','New Brunswick','EN','High'
),
(
  'Houlton High School ','Maine','EN','High'
);

-- For the School record with a schoolId of 2, update the province to ‘Manitoba'.
UPDATE Schools
SET province = 'Manitoba'
WHERE schoolId = 2;

-- Delete the record whose province value is ‘Manitoba’.
DELETE FROM Schools
WHERE schoolId = 2;
SELECT * FROM Schools;
