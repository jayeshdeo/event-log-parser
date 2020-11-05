DROP TABLE Event IF EXISTS;
CREATE TABLE Event(
  Id VARCHAR(50) UNIQUE,
  Duration VARCHAR(50),
  Type VARCHAR(50),
  Host VARCHAR(50),
  Alert TINYINT);