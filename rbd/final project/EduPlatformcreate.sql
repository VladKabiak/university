-- tables
-- Table: Assignment
CREATE TABLE Assignment (
    id integer  NOT NULL,
    title varchar2(255)  NOT NULL,
    description clob  NOT NULL,
    deadline date  NOT NULL,
    Course_id integer  NOT NULL,
    CONSTRAINT Assignment_pk PRIMARY KEY (id)
) ;

-- Table: Course
CREATE TABLE Course (
    id integer  NOT NULL,
    title varchar2(255)  NOT NULL,
    description clob  NOT NULL,
    CONSTRAINT Course_pk PRIMARY KEY (id)
) ;

-- Table: Enrollment
CREATE TABLE Enrollment (
    Course_id integer  NOT NULL,
    User_id integer  NOT NULL,
    CONSTRAINT Course_User_pk PRIMARY KEY (Course_id,User_id)
) ;

-- Table: Instructor
CREATE TABLE Instructor (
    id integer  NOT NULL,
    name varchar2(255)  NOT NULL,
    department varchar2(255)  NOT NULL,
    Course_id integer  NOT NULL,
    CONSTRAINT Instructor_pk PRIMARY KEY (id)
) ;

-- Table: Lecture
CREATE TABLE Lecture (
    id integer  NOT NULL,
    title varchar2(255)  NOT NULL,
    content clob  NOT NULL,
    Course_id integer  NOT NULL,
    CONSTRAINT Lecture_pk PRIMARY KEY (id)
) ;

-- Table: Quiz
CREATE TABLE Quiz (
    id integer  NOT NULL,
    title varchar2(255)  NOT NULL,
    questions varchar2(255)  NOT NULL,
    Lecture_id integer  NOT NULL,
    CONSTRAINT Quiz_pk PRIMARY KEY (id)
) ;

-- Table: Resource
CREATE TABLE "Resource" (
    id integer  NOT NULL,
    title varchar2(255)  NOT NULL,
    url varchar2(255)  NOT NULL,
    Course_id integer  NOT NULL,
    CONSTRAINT Resource_pk PRIMARY KEY (id)
) ;

-- Table: User
CREATE TABLE "User" (
    id integer  NOT NULL,
    username varchar2(255)  NOT NULL,
    password varchar2(255)  NOT NULL,
    email varchar2(255)  NOT NULL,
    first_name varchar2(255)  NOT NULL,
    last_name varchar2(255)  NOT NULL,
    CONSTRAINT User_pk PRIMARY KEY (id)
) ;

-- foreign keys
-- Reference: Assignment_Course (table: Assignment)
ALTER TABLE Assignment ADD CONSTRAINT Assignment_Course
    FOREIGN KEY (Course_id)
    REFERENCES Course (id);

-- Reference: Course_User_Course (table: Enrollment)
ALTER TABLE Enrollment ADD CONSTRAINT Course_User_Course
    FOREIGN KEY (Course_id)
    REFERENCES Course (id);

-- Reference: Course_User_User (table: Enrollment)
ALTER TABLE Enrollment ADD CONSTRAINT Course_User_User
    FOREIGN KEY (User_id)
    REFERENCES "User" (id);

-- Reference: Instructor_Course (table: Instructor)
ALTER TABLE Instructor ADD CONSTRAINT Instructor_Course
    FOREIGN KEY (Course_id)
    REFERENCES Course (id);

-- Reference: Lecture_Course (table: Lecture)
ALTER TABLE Lecture ADD CONSTRAINT Lecture_Course
    FOREIGN KEY (Course_id)
    REFERENCES Course (id);

-- Reference: Quiz_Lecture (table: Quiz)
ALTER TABLE Quiz ADD CONSTRAINT Quiz_Lecture
    FOREIGN KEY (Lecture_id)
    REFERENCES Lecture (id);

-- Reference: Resource_Course (table: Resource)
ALTER TABLE "Resource" ADD CONSTRAINT Resource_Course
    FOREIGN KEY (Course_id)
    REFERENCES Course (id);

-- sequences
-- Sequence: Assignment_seq
CREATE SEQUENCE Assignment_seq
      INCREMENT BY 1
      NOMINVALUE
      NOMAXVALUE
      START WITH 1
      NOCACHE
      NOCYCLE;

-- Sequence: Course_seq
CREATE SEQUENCE Course_seq
      INCREMENT BY 1
      NOMINVALUE
      NOMAXVALUE
      START WITH 1
      NOCACHE
      NOCYCLE;

-- Sequence: Instructor_seq
CREATE SEQUENCE Instructor_seq
      INCREMENT BY 1
      NOMINVALUE
      NOMAXVALUE
      START WITH 1
      NOCACHE
      NOCYCLE;

-- Sequence: Lecture_seq
CREATE SEQUENCE Lecture_seq
      INCREMENT BY 1
      NOMINVALUE
      NOMAXVALUE
      START WITH 1
      NOCACHE
      NOCYCLE;

-- Sequence: Quiz_seq
CREATE SEQUENCE Quiz_seq
      INCREMENT BY 1
      NOMINVALUE
      NOMAXVALUE
      START WITH 1
      NOCACHE
      NOCYCLE;

-- Sequence: Resource_seq
CREATE SEQUENCE Resource_seq
      INCREMENT BY 1
      NOMINVALUE
      NOMAXVALUE
      START WITH 1
      NOCACHE
      NOCYCLE;

-- Sequence: User_seq
CREATE SEQUENCE User_seq
      INCREMENT BY 1
      NOMINVALUE
      NOMAXVALUE
      START WITH 1
      NOCACHE
      NOCYCLE;

