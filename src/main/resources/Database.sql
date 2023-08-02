CREATE TABLE `students` (
  `code` varchar(15) NOT NULL,
  `age` int(3) NOT NULL,
  `city` varchar(80) NOT NULL,
  `dateCreated` datetime NOT NULL,
  `dateUpdated` datetime DEFAULT NULL,
  `name` varchar(150) NOT NULL,
  `timeZone` varchar(80) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `exams` (
  `code` varchar(10) NOT NULL,
  `dateCreated` datetime NOT NULL,
  `dateUpdated` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `questions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `examCode` varchar(10) NOT NULL,
  `numberQuestion` int(2) NOT NULL,
  `question` varchar(255) NOT NULL,
  `score` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `questions_examCode_IDX` (`examCode`,`numberQuestion`) USING BTREE,
  CONSTRAINT `questions_FK` FOREIGN KEY (`examCode`) REFERENCES `exams` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `aswers_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) NOT NULL,
  `correct` tinyint(1) NOT NULL,
  `enumeration` varchar(1) NOT NULL,
  `questionId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `aswers_options_questionId_IDX` (`questionId`,`enumeration`) USING BTREE,
  CONSTRAINT `aswers_options_FK` FOREIGN KEY (`questionId`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `student_schedule` (
  `studentCode` varchar(15) NOT NULL,
  `examCode` varchar(10) NOT NULL,
  `dateCreated` datetime NOT NULL,
  `dateUpdated` datetime NOT NULL,
  `examDate` datetime NOT NULL,
  `timeZone` varchar(255) NOT NULL,
  PRIMARY KEY (`studentCode`,`examCode`),
  KEY `student_schedule_FK_1` (`examCode`),
  CONSTRAINT `student_schedule_FK` FOREIGN KEY (`studentCode`) REFERENCES `students` (`code`),
  CONSTRAINT `student_schedule_FK_1` FOREIGN KEY (`examCode`) REFERENCES `exams` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `students_aswers` (
  `questionId` bigint(20) NOT NULL,
  `studentCode` varchar(15) NOT NULL,
  `answerOptionId` bigint(20) NOT NULL,
  `dateCreated` datetime NOT NULL,
  `dateUpdated` datetime NOT NULL,
  PRIMARY KEY (`questionId`,`studentCode`),
  KEY `students_aswers_FK` (`studentCode`),
  KEY `students_aswers_FK_2` (`answerOptionId`),
  CONSTRAINT `students_aswers_FK` FOREIGN KEY (`studentCode`) REFERENCES `students` (`code`),
  CONSTRAINT `students_aswers_FK_1` FOREIGN KEY (`questionId`) REFERENCES `questions` (`id`),
  CONSTRAINT `students_aswers_FK_2` FOREIGN KEY (`answerOptionId`) REFERENCES `aswers_options` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create view vw_exam_score_students as
select s.code studentCode, s.name studentName, e.code examCode, e.name examName, q.numberQuestion, q.question, 
q.score questionScore, ao.enumeration enumerationAnswer, ao.answer, ao.correct, 
case ao.correct when 1 then q.score else 0 end scoreReceived
from exams e 
inner join questions q 
	on e.code  = q.examCode 
inner join aswers_options ao 
	on ao.questionId = q.id
inner join students_aswers sa 
	on sa.questionId  = q.id 
	and sa.answerOptionId = ao.id
inner join students s
	on s.code = sa.studentCode;