/* 이름: demo_madang.sql */
/* 설명 */
 
/* root 계정으로 접속, madang 데이터베이스 생성, madang 계정 생성 */
/* MySQL Workbench에서 초기화면에서 +를 눌러 root connection을 만들어 접속한다. */
DROP DATABASE IF EXISTS  madang;
DROP USER IF EXISTS  madang@localhost;
create user madang@localhost identified WITH mysql_native_password  by 'madang';
create database madang;
grant all privileges on madang.* to madang@localhost with grant option;
commit;

/* madang DB 자료 생성 */
/* 이후 실습은 MySQL Workbench에서 초기화면에서 +를 눌러 madang connection을 만들어 접속하여 사용한다. */
 
USE madang;

create table student(
   studentId    integer primary key,
   studentName   varchar(40),
   studentPhone varchar(40),
    studentEmail varchar(40),
    majorId integer,
    professorId integer,
    tuitionAccount varchar(40),
    minorId integer
);

create table professor(
   professorId integer primary key,
    professorName varchar(40),
    professorAddress integer,
   professorPhone varchar(40),
   professorEmail varchar(40),
    departmentId integer
);

create table department(
    departmentId integer primary key,
    departmentName varchar(40),
    departmentPhone varchar(40),
    departmentAddress integer,
   professorId integer
);

create table class(
   classId integer primary key,
    divideNum integer,
    className varchar(40),
   professorId integer, 
    departmentId integer,
    classDay varchar(40),
    period integer,
    credits integer,
    progressingTime integer,
    classAddress integer
);

create table classHistory(
   studentId integer,
    classId integer,
   professorId integer,
   midScore integer,
    finalScore integer, 
   ectScore integer, 
   scoreSum integer,
   grade varchar(40)
);

create table tuition(
   studentId integer primary key,
    tuitionYear year, 
    tuitionSemester integer,
    tuitionSum integer,
    paySum integer,
   lastPaymentDate date
);

create table club(
   clubId integer primary key,
   clubName varchar(40),
   numberOfStudents integer,
   clubAddress integer,
   professorId integer,
   presidentStudentId integer
);

create table clubStudentList(
   clubId integer,
   studentId integer
);

/*student table data*/
INSERT INTO student VALUES(1,'강민아', '01034233645', 'dafds@naver.com', 1, 1,'43432345234', 2 );
INSERT INTO student VALUES(2,'이혜지', '01078564431', 'gjkfngk@naver.com', 1, 1,'34656534534', 0);
INSERT INTO student VALUES(3,'한정연', '01012345453', '4gfhdk@naver.com', 1, 2,'56443665245', 0);
INSERT INTO student VALUES(4,'황서인', '01076554432', 'grepie@naver.com', 1, 2,'34326362232', 1);
INSERT INTO student VALUES(5,'장혜진', '01033316252', 'rqopwuei@naver.com', 2, 3,'65521332212', 0);
INSERT INTO student VALUES(6,'서강준', '01065632342', 'epruej@naver.com', 2, 3,'76643545492', 0);
INSERT INTO student VALUES(7,'유영환', '01015434654', 'rhjyytse@naver.com', 2, 4,'56521765341', 0);
INSERT INTO student VALUES(8,'이연우', '01021352330', 'thwwg@naver.com', 2, 4,'65424223113', 0);
INSERT INTO student VALUES(9,'신수현', '01012563122', 'hsgfde@naver.com', 3, 5,'54321212557', 23 );
INSERT INTO student VALUES(10,'홍원표', '01056113212', 'sdfvtr@naver.com', 3, 5,'55644541522', 1);
INSERT INTO student VALUES(11,'유지태', '01012007894', 'gherszdf@naver.com', 3, 6,'66643232112', 0);
INSERT INTO student VALUES(12,'김민재', '01034219526', 'gffeas@naver.com', 3, 6,'67875234532', 17 );
INSERT INTO student VALUES(13,'이정윤', '01045615910', 'hngf@naver.com', 4, 7,'78835121455', 2);
INSERT INTO student VALUES(14,'박현정', '01022363461', 'ewraz@naver.com', 4, 7,'76432123245', 0);
INSERT INTO student VALUES(15,'강상욱', '01069515351', 'fghyth@naver.com', 4, 8,'57812123764', 0);
INSERT INTO student VALUES(16,'박주아', '01087977896', 'hgwer@naver.com', 4, 8,'13142521533', 0);
INSERT INTO student VALUES(17,'김예리', '01064563232', 'gfhgsfdzxc@naver.com', 5, 9,'13419774245', 0);
INSERT INTO student VALUES(18,'권예담', '01079851235', 'kjht@naver.com', 5, 9,'72153625234', 0);
INSERT INTO student VALUES(19,'강민경', '01045663121', 'uyfgv@naver.com', 5, 10,'31414867653', 0);
INSERT INTO student VALUES(20,'윤세영', '01013212316', 'kjmnggg@naver.com', 5, 10, '23546765978', 0);
INSERT INTO student VALUES(21,'이범석', '01022332364', 'ynfgc@naver.com', 6, 11,'09856566236', 0);
INSERT INTO student VALUES(22,'이선율', '01023153226', 'mntrds@naver.com', 6, 11,'95327236012', 1 );
INSERT INTO student VALUES(23,'최준오', '01056652325', 'yrtsdgx@naver.com', 6, 12,'43012304210', 0);
INSERT INTO student VALUES(24,'백인헌', '01012353897', 'fgsdr@naver.com', 6, 12,'65062605475', 0);
INSERT INTO student VALUES(25,'윤종석', '01059262555', 'fswe@naver.com', 7, 13,'30174100102', 5);
INSERT INTO student VALUES(26,'김영웅', '01055619521', 'nbgfds@naver.com', 7, 13,'10043420349', 12 );
INSERT INTO student VALUES(27,'이서웅', '01002394754', 'dafds@naver.com',7, 14,'43432345234', 2 );
INSERT INTO student VALUES(28,'박수헌', '01010239471', 'gjkfngk@naver.com', 7, 14,'34656534534',0 );
INSERT INTO student VALUES(29,'최아준', '01015204862', '4gfhdk@naver.com', 8, 15,'56443665245', 0 );
INSERT INTO student VALUES(30,'황우솔', '01094793031', 'grepie@naver.com', 8, 15,'34326362232', 1);
INSERT INTO student VALUES(31,'김은담', '01040237902', 'rqopwuei@naver.com', 9, 16,'65521332212',0 );
INSERT INTO student VALUES(32,'유정흠', '01040293749', 'epruej@naver.com', 9, 16,'76643545492', 0 );
INSERT INTO student VALUES(33,'이찬세', '01049208903', 'rhjyytse@naver.com', 9, 17,'56521765341',0 );
INSERT INTO student VALUES(34,'유서인', '01025845792', 'thwwg@naver.com', 9, 17,'65424223113', 0 );
INSERT INTO student VALUES(35,'김수인', '01011948763', 'hsgfde@naver.com', 10, 18,'54321212557', 0 );
INSERT INTO student VALUES(36,'이여운', '01054883762', 'sdfvtr@naver.com', 10, 18,'55644541522', 11);
INSERT INTO student VALUES(37,'윤서하', '01048846323', 'gherszdf@naver.com', 10, 19,'66643232112', 0);
INSERT INTO student VALUES(38,'임승헌', '01049938736', 'gffeas@naver.com', 10, 19,'67875234532', 0 );
INSERT INTO student VALUES(39,'고여흔', '01040039823', 'hngf@naver.com', 11, 20,'78835121455', 2);
INSERT INTO student VALUES(40,'최이솔', '01040938372', 'ewraz@naver.com', 11, 20,'76432123245', 0);
INSERT INTO student VALUES(41,'김예욱', '01064390792', 'fghyth@naver.com', 11, 21,'57812123764', 0);
INSERT INTO student VALUES(42,'박준웅', '01030299837', 'hgwer@naver.com', 11, 21,'13142521533',0 );
INSERT INTO student VALUES(43,'박찬휘', '01010029732', 'gfhgsfdzxc@naver.com', 22, 2,'13419774245', 0);
INSERT INTO student VALUES(44,'신찬율', '01048847631', 'kjht@naver.com',12, 22,'72153625234', 0 );
INSERT INTO student VALUES(45,'홍찬유', '01048833612', 'uyfgv@naver.com', 12, 23,'31414867653', 0 );
INSERT INTO student VALUES(46,'이찬유', '01043873613', 'kjmnggg@naver.com', 12, 23, '23546765978', 6);
INSERT INTO student VALUES(47,'최찬형', '01038387100', 'ynfgc@naver.com', 12, 24,'09856566236', 0 );
INSERT INTO student VALUES(48,'임슬옹', '01048923873', 'mntrds@naver.com', 12, 24,'95327236012', 1 );
INSERT INTO student VALUES(49,'박서후', '01030039273', 'yrtsdgx@naver.com', 13, 25,'43012304210',0);
INSERT INTO student VALUES(50,'윤서헌', '01040049382', 'fgsdr@naver.com', 13, 25,'65062605475', 0);
INSERT INTO student VALUES(51,'이영후', '01030003827', 'fswe@naver.com', 13, 26,'30174100102',0);
INSERT INTO student VALUES(52,'박윤우', '01030772613', 'nbgfds@naver.com', 13, 26,'10043420349', 1 );
INSERT INTO student VALUES(53,'유은교', '01049398372', 'dafds@naver.com', 14, 27,'43432345234', 21 );
INSERT INTO student VALUES(54,'최은영', '01030020387', 'gjkfngk@naver.com', 14, 27,'34656534534', 0 );
INSERT INTO student VALUES(55,'정다빈', '01039938663', '4gfhdk@naver.com', 14, 28,'56443665245',0 );
INSERT INTO student VALUES(56,'유은혜', '01048858843', 'grepie@naver.com', 14, 28,'34326362232', 15);
INSERT INTO student VALUES(57,'김예지', '01040012863', 'rqopwuei@naver.com', 15, 29,'65521332212', 0 );
INSERT INTO student VALUES(58,'서혜주', '01040293774', 'epruej@naver.com', 15, 29,'76643545492', 0 );
INSERT INTO student VALUES(59,'김혜슬', '01040039827', 'rhjyytse@naver.com', 15, 30,'56521765341',0 );
INSERT INTO student VALUES(60,'김영주', '01059079655', 'thwwg@naver.com', 15, 30,'65424223113',0 );
INSERT INTO student VALUES(61,'김영아', '01029384654', 'hsgfde@naver.com', 16, 31,'54321212557', 0 );
INSERT INTO student VALUES(62,'최오윤', '01049834757', 'sdfvtr@naver.com', 16, 31,'55644541522', 8);
INSERT INTO student VALUES(63,'김여원', '01059857463', 'gherszdf@naver.com', 32, 6,'66643232112', 19);
INSERT INTO student VALUES(64,'이여원', '01029834762', 'gffeas@naver.com',16, 32,'67875234532', 0 );
INSERT INTO student VALUES(65,'박영채', '01049584623', 'hngf@naver.com', 17, 33,'78835121455', 2);
INSERT INTO student VALUES(66,'민설아', '01029384628', 'ewraz@naver.com', 17, 33,'76432123245', 0 );
INSERT INTO student VALUES(67,'박시아', '01049852634', 'fghyth@naver.com', 17, 34,'57812123764', 0 );
INSERT INTO student VALUES(68,'박신영', '01049883473', 'hgwer@naver.com', 17, 34,'13142521533', 0);
INSERT INTO student VALUES(69,'권수애', '01048937479', 'gfhgsfdzxc@naver.com', 18, 35,'13419774245', 0);
INSERT INTO student VALUES(70,'정슬비', '01069991023', 'kjht@naver.com', 18, 35,'72153625234', 0 );
INSERT INTO student VALUES(71,'신송빈', '01042938472', 'uyfgv@naver.com', 18, 36,'31414867653', 20 );
INSERT INTO student VALUES(72,'송자현', '01029384278', 'kjmnggg@naver.com', 18, 36, '23546765978',0);
INSERT INTO student VALUES(73,'김제니', '01029384623', 'ynfgc@naver.com', 19, 37,'09856566236',0 );
INSERT INTO student VALUES(74,'최조안', '01029384723', 'mntrds@naver.com', 19, 37,'95327236012', 1 );
INSERT INTO student VALUES(75,'백시아', '01069969684', 'yrtsdgx@naver.com', 19, 38,'43012304210', 0);
INSERT INTO student VALUES(76,'박주원', '01049598473', 'fgsdr@naver.com', 19, 38,'65062605475',0);
INSERT INTO student VALUES(77,'강대열', '01023984759', 'fswe@naver.com', 20, 39,'30174100102', 0);
INSERT INTO student VALUES(78,'신승용', '01059845792', 'nbgfds@naver.com', 20, 39,'10043420349', 1 );
INSERT INTO student VALUES(79,'나윤채', '01034233645', 'dafds@naver.com', 20, 40,'43432345234', 24 );
INSERT INTO student VALUES(80,'황성은', '01078564431', 'gjkfngk@naver.com', 20, 40,'34656534534', 0 );
INSERT INTO student VALUES(81,'김소은', '01012345453', '4gfhdk@naver.com', 21, 41,'56443665245', 0 );
INSERT INTO student VALUES(82,'유재아', '01076554432', 'grepie@naver.com', 21, 41,'34326362232', 13);
INSERT INTO student VALUES(83,'박동인', '01033316252', 'rqopwuei@naver.com', 21, 42,'65521332212',0 );
INSERT INTO student VALUES(84,'이동해', '01065632342', 'epruej@naver.com', 21, 42,'76643545492', 0 );
INSERT INTO student VALUES(85,'유리안', '01015434654', 'rhjyytse@naver.com', 22, 43,'56521765341', 0 );
INSERT INTO student VALUES(86,'이로훈', '01021352330', 'thwwg@naver.com', 22, 43,'65424223113',0 );
INSERT INTO student VALUES(87,'최태영', '01012563122', 'hsgfde@naver.com', 22, 44,'54321212557', 0 );
INSERT INTO student VALUES(88,'황태율', '01056113212', 'sdfvtr@naver.com', 22, 44,'55644541522', 11);
INSERT INTO student VALUES(89,'이연서', '01012007894', 'gherszdf@naver.com', 23, 45,'66643232112',0);
INSERT INTO student VALUES(90,'김민채', '01034219526', 'gffeas@naver.com', 23, 45,'67875234532',0 );
INSERT INTO student VALUES(91,'나민희', '01045615910', 'hngf@naver.com', 23, 46,'78835121455', 22);
INSERT INTO student VALUES(92,'유주아', '01022363461', 'ewraz@naver.com', 23, 46,'76432123245', 0);
INSERT INTO student VALUES(93,'김동운', '01069515351', 'fghyth@naver.com', 24, 47,'57812123764', 0);
INSERT INTO student VALUES(94,'곽동혜', '01087977896', 'hgwer@naver.com', 24, 47,'13142521533', 0 );
INSERT INTO student VALUES(95,'김환희', '01064563232', 'gfhgsfdzxc@naver.com', 24, 48,'13419774245', 4 );
INSERT INTO student VALUES(96,'이준', '01079851235', 'kjht@naver.com', 24, 48,'72153625234', 0 );
INSERT INTO student VALUES(97,'이예찬', '01045663121', 'uyfgv@naver.com', 25, 49,'31414867653', 0 );
INSERT INTO student VALUES(98,'윤이황', '01013212316', 'kjmnggg@naver.com', 25, 49, '23546765978', 3);
INSERT INTO student VALUES(99,'이하준', '01022332364', 'ynfgc@naver.com',25, 50,'09856566236', 0 );
INSERT INTO student VALUES(100,'박효성', '01023153226', 'mntrds@naver.com', 25, 50,'95327236012', 1 );

/*professor table data*/
INSERT INTO professor VALUE(1,'박기호', 401,'01031404502','asdfq1@naver.com', 1);
INSERT INTO professor VALUE(2,'박태순', 402,'01034124357','rtwqgq@naver.com', 1);
INSERT INTO professor VALUE(3,'유영환', 403,'01010409401','gsdasf@naver.com', 2);
INSERT INTO professor VALUE(4,'나중채', 404,'01076904401','fpwejq@naver.com', 2);
INSERT INTO professor VALUE(5,'장윤', 405,'01040377891','fgsd@naver.com', 3);
INSERT INTO professor VALUE(6,'양효식', 406,'01001724971','jhjhr@naver.com', 3);
INSERT INTO professor VALUE(7,'문현준', 407,'01001825473','gfsav@naver.com', 4);
INSERT INTO professor VALUE(8,'권오진', 408,'01084714792','gsdfgw@naver.com', 4);
INSERT INTO professor VALUE(9,'김재호', 409,'01048924682','sdfgwg@naver.com', 5);
INSERT INTO professor VALUE(10,'문주희', 410,'01037926692','nsfdsvc@naver.com', 5);
INSERT INTO professor VALUE(11,'하진용', 411,'01055824911','jhjty@naver.com', 6);
INSERT INTO professor VALUE(12,'한종기', 412,'01010324001','utumdt@naver.com', 6);
INSERT INTO professor VALUE(13,'임태규', 413,'01010298346','thsgrge@naver.com', 7);
INSERT INTO professor VALUE(14,'심순미', 414,'01027402901','wegsfb@naver.com', 7);
INSERT INTO professor VALUE(15,'신학동', 415,'01040537292','ntgsrega@naver.com', 8);
INSERT INTO professor VALUE(16,'홍근표', 416,'01050493871','htrawbv@naver.com', 8);
INSERT INTO professor VALUE(17,'김용휘', 417,'01014705947','gweqef@naver.com', 9);
INSERT INTO professor VALUE(18,'이영수', 418,'01031234523','asdfq1@naver.com', 9);
INSERT INTO professor VALUE(19,'박기환', 429,'01059984734','rtwqgq@naver.com', 10);
INSERT INTO professor VALUE(20,'유성진', 420,'01049837474','gsdasf@naver.com', 10);
INSERT INTO professor VALUE(21,'김민수', 421,'01076904401','fpwejq@naver.com', 11);
INSERT INTO professor VALUE(22,'양진호', 422,'01040377891','fgsd@naver.com', 11);
INSERT INTO professor VALUE(23,'임성수', 423,'01001724971','jhjhr@naver.com', 12);
INSERT INTO professor VALUE(24,'이경수', 424,'01001825473','gfsav@naver.com', 12);
INSERT INTO professor VALUE(25,'유영환', 425,'01084714792','gsdfgw@naver.com', 13);
INSERT INTO professor VALUE(26,'김판임', 426,'01048924682','sdfgwg@naver.com', 13);
INSERT INTO professor VALUE(27,'박준영', 427,'01037926692','nsfdsvc@naver.com', 14);
INSERT INTO professor VALUE(28,'신승현', 428,'01055824911','jhjty@naver.com', 14);
INSERT INTO professor VALUE(29,'김우진', 429,'01010324001','utumdt@naver.com', 15);
INSERT INTO professor VALUE(30,'김민준', 430,'01010298346','thsgrge@naver.com', 15);
INSERT INTO professor VALUE(31,'박서준', 431,'01027402901','wegsfb@naver.com', 16);
INSERT INTO professor VALUE(32,'이영호', 432,'01040537292','ntgsrega@naver.com', 16);
INSERT INTO professor VALUE(33,'김정식', 433,'01050493871','htrawbv@naver.com', 17);
INSERT INTO professor VALUE(34,'신영환', 434,'01014705947','gweqef@naver.com', 17);
INSERT INTO professor VALUE(35,'이정호', 435,'01043750928','asdfq1@naver.com', 18);
INSERT INTO professor VALUE(36,'김영식', 436,'01023904857','rtwqgq@naver.com', 18);
INSERT INTO professor VALUE(37,'박상철', 437,'01023947057','gsdasf@naver.com', 19);
INSERT INTO professor VALUE(38,'이종수', 438,'01023098475','fpwejq@naver.com', 19);
INSERT INTO professor VALUE(39,'이병철', 439,'01044783984','fgsd@naver.com', 20);
INSERT INTO professor VALUE(40,'박성수', 440,'01049938743','jhjhr@naver.com', 20);
INSERT INTO professor VALUE(41,'김민재', 441,'01020938473','gfsav@naver.com', 21);
INSERT INTO professor VALUE(42,'김주원', 442,'01030084723','gsdfgw@naver.com', 21);
INSERT INTO professor VALUE(43,'김정숙', 443,'01040048372','sdfgwg@naver.com', 22);
INSERT INTO professor VALUE(44,'이미경', 444,'01049937462','nsfdsvc@naver.com', 22);
INSERT INTO professor VALUE(45,'박서윤', 445,'01040394872','jhjty@naver.com', 23);
INSERT INTO professor VALUE(46,'유서연', 446,'01049948372','utumdt@naver.com', 23);
INSERT INTO professor VALUE(47,'이수진', 447,'01040298364','thsgrge@naver.com', 24);
INSERT INTO professor VALUE(48,'하서현', 448,'01049827434','wegsfb@naver.com', 24);
INSERT INTO professor VALUE(49,'신민지', 449,'01056969996','ntgsrega@naver.com', 25);
INSERT INTO professor VALUE(50,'유서윤', 450,'01033232334','htrawbv@naver.com', 25);

/*department table data*/
INSERT INTO department VALUE(0,'없음', NULL,NULL,NULL);
INSERT INTO department VALUE(1,'컴퓨터공학과','02-3408-4801','501','1');
INSERT INTO department VALUE(2,'전자전기공학과','02-3408-3212','502','3');
INSERT INTO department VALUE(3,'기계공학과','02-3408-6423','503','5');
INSERT INTO department VALUE(4,'화학공학과','02-3408-6756','504','7');
INSERT INTO department VALUE(5,'건축공학과','02-3408-1231','505','9');
INSERT INTO department VALUE(6,'경영학과','02-3408-4081','506','11');
INSERT INTO department VALUE(7,'생명공학과','02-3408-3019','507','13');
INSERT INTO department VALUE(8,'국어국문학과','02-3408-4801','508','15');
INSERT INTO department VALUE(9,'행정학과','02-3408-3212','509','17');
INSERT INTO department VALUE(10,'미디어커뮤니케이션학과','02-3408-6423','510','19');
INSERT INTO department VALUE(11,'외식경영학과','02-3408-6756','511','21');
INSERT INTO department VALUE(12,'법학과','02-3408-1231','512','23');
INSERT INTO department VALUE(13,'수학통계학과','02-3408-4081','513','25');
INSERT INTO department VALUE(14,'경제학과','02-3408-3019','514','27');
INSERT INTO department VALUE(15,'생명시스템학과','02-3408-4801','515','29');
INSERT INTO department VALUE(16,'지구자원시스템공학과','02-3408-3212','516','31');
INSERT INTO department VALUE(17,'정보보호학과','02-3408-6423','517','33');
INSERT INTO department VALUE(18,'소프트웨어학과','02-3408-6756','518','35');
INSERT INTO department VALUE(19,'패션디자인학과','02-3408-1231','519','37');
INSERT INTO department VALUE(20,'음악과','02-3408-4081','520','39');
INSERT INTO department VALUE(21,'체육학과','02-3408-3019','521','41');
INSERT INTO department VALUE(22,'국방시스템공학과','02-3408-4801','522','43');
INSERT INTO department VALUE(23,'항공시스템공학과','02-3408-3212','523','45');
INSERT INTO department VALUE(24,'영화예술학과','02-3408-6423','524','47');
INSERT INTO department VALUE(25,'회화과','02-3408-6423','525','49');

/*class table data*/
/*강의번호 분반 과목이름 교수번호 학과번호 요일 강의교시 학점 진행시간 강의실주소 */
INSERT INTO class VALUES(1, 1, '과목1', 1, 1, '월요일', 1, 4, 1, 201);
INSERT INTO class VALUES(2, 1, '과목2', 1, 1, '화요일', 1, 4, 1, 202);
INSERT INTO class VALUES(3, 1, '과목3', 1, 1, '수요일', 1, 3, 1, 203);
INSERT INTO class VALUES(4, 1, '과목4', 1, 1, '목요일', 1, 4, 1, 204);
INSERT INTO class VALUES(5, 1, '과목5', 2, 1, '금요일', 1, 3, 1, 205);
INSERT INTO class VALUES(6, 1, '과목6', 2, 1, '월요일', 1, 3, 1, 206);
INSERT INTO class VALUES(7, 1, '과목7', 2, 1, '화요일', 1, 3, 1, 207);
INSERT INTO class VALUES(8, 1, '과목8', 2, 1, '수요일', 1, 3, 1, 208);
INSERT INTO class VALUES(9, 1, '과목9', 3, 2, '목요일', 1, 3, 1, 209);
INSERT INTO class VALUES(10, 1, '과목10', 3, 2, '수요일', 1, 4, 1, 210);
INSERT INTO class VALUES(11, 1, '과목11', 3, 2, '목요일', 1, 3, 1, 211);
INSERT INTO class VALUES(12, 1, '과목12', 3, 2, '금요일', 1, 3, 1, 212);
INSERT INTO class VALUES(13, 1, '과목13', 4, 2, '화요일', 1, 4, 1, 213);
INSERT INTO class VALUES(14, 1, '과목14', 4, 2, '수요일', 1, 3, 1, 214);
INSERT INTO class VALUES(15, 1, '과목15', 4, 2, '목요일', 1, 3, 1, 215);
INSERT INTO class VALUES(16, 1, '과목16', 4, 2, '금요일', 2, 4, 1, 216);
INSERT INTO class VALUES(17, 1, '과목17', 5, 3, '화요일', 1, 3, 1, 217);
INSERT INTO class VALUES(18, 1, '과목18', 5, 3, '수요일', 1, 4, 1, 218);
INSERT INTO class VALUES(19, 1, '과목19', 5, 3, '목요일', 2, 3, 1, 219);
INSERT INTO class VALUES(20, 1, '과목20', 5, 3, '금요일', 3, 1, 1, 220);
INSERT INTO class VALUES(21, 1, '과목21', 6, 3, '월요일', 3, 2, 1, 221);
INSERT INTO class VALUES(22, 1, '과목22', 6, 3, '금요일', 1, 2, 1, 222);
INSERT INTO class VALUES(23, 1, '과목23', 6, 3, '월요일', 2, 4, 1, 223);
INSERT INTO class VALUES(24, 1, '과목24', 6, 3, '화요일', 3, 3, 1, 224);
INSERT INTO class VALUES(25, 1, '과목25', 7, 4, '금요일', 1, 2, 1, 225);
INSERT INTO class VALUES(26, 1, '과목26', 7, 4, '월요일', 1, 4, 1, 226);
INSERT INTO class VALUES(27, 1, '과목27', 7, 4, '화요일', 1, 3, 1, 227);
INSERT INTO class VALUES(28, 1, '과목28', 7, 4, '수요일', 2, 2, 1, 228);
INSERT INTO class VALUES(29, 1, '과목29', 8, 4, '목요일', 2, 3, 1, 229);
INSERT INTO class VALUES(30, 1, '과목30', 8, 4, '월요일', 1, 4, 1, 201);
INSERT INTO class VALUES(31, 1, '과목31', 8, 4, '화요일', 1, 4, 1, 202);
INSERT INTO class VALUES(32, 1, '과목32', 8, 4, '수요일', 1, 3, 1, 203);
INSERT INTO class VALUES(33, 1, '과목33', 9, 5, '목요일', 1, 4, 1, 204);
INSERT INTO class VALUES(34, 1, '과목34', 9, 5, '금요일', 1, 3, 1, 205);
INSERT INTO class VALUES(35, 1, '과목35', 9, 5, '월요일', 1, 3, 1, 215);
INSERT INTO class VALUES(36, 1, '과목36', 9, 5, '화요일', 1, 3, 1, 216);
INSERT INTO class VALUES(37, 1, '과목37', 10, 5, '수요일', 1, 3, 1, 217);
INSERT INTO class VALUES(38, 1, '과목38', 10, 5, '목요일', 1, 3, 1, 218);
INSERT INTO class VALUES(39, 1, '과목39', 10, 5, '수요일', 1, 4, 1, 227);
INSERT INTO class VALUES(40, 1, '과목40', 10,5, '목요일', 1, 3, 1, 228);
INSERT INTO class VALUES(41, 1, '과목41', 11, 6, '금요일', 1, 3, 1, 229);
INSERT INTO class VALUES(42, 1, '과목42', 11, 6, '화요일', 1, 4, 1, 241);
INSERT INTO class VALUES(43, 1, '과목43', 11, 6, '수요일', 1, 3, 1, 242);
INSERT INTO class VALUES(44, 1, '과목44', 11, 6, '목요일', 1, 3, 1, 243);
INSERT INTO class VALUES(45, 1, '과목45', 12, 6, '금요일', 2, 4, 1, 244);
INSERT INTO class VALUES(46, 1, '과목46', 12, 6, '화요일', 1, 3, 1, 251);
INSERT INTO class VALUES(47, 1, '과목47', 12, 6, '수요일', 1, 4, 1, 252);
INSERT INTO class VALUES(48, 1, '과목48', 12, 6, '목요일', 2, 3, 1, 253);
INSERT INTO class VALUES(49, 1, '과목49', 13, 7, '금요일', 3, 1, 1, 254);
INSERT INTO class VALUES(50, 1, '과목50', 13, 7, '월요일', 3, 2, 1, 255);
INSERT INTO class VALUES(51, 1, '과목51', 13, 7, '금요일', 1, 2, 1, 259);
INSERT INTO class VALUES(52, 1, '과목52', 13, 7, '월요일', 2, 4, 1, 260);
INSERT INTO class VALUES(53, 1, '과목53', 14, 7, '화요일', 3, 3, 1, 261);
INSERT INTO class VALUES(54, 1, '과목54', 14, 7, '금요일', 1, 2, 1, 264);
INSERT INTO class VALUES(55, 1, '과목55', 14, 7, '월요일', 1, 4, 1, 265);
INSERT INTO class VALUES(56, 1, '과목56', 14, 7, '화요일', 1, 3, 1, 266);
INSERT INTO class VALUES(57, 1, '과목57', 15, 8, '수요일', 2, 2, 1, 267);
INSERT INTO class VALUES(58, 1, '과목58', 15, 8, '목요일', 2, 3, 1, 268);
INSERT INTO class VALUES(59, 1, '과목59', 15, 8, '월요일', 1, 4, 1, 201);
INSERT INTO class VALUES(60, 1, '과목60', 15, 8, '화요일', 1, 4, 1, 202);
INSERT INTO class VALUES(61, 1, '과목61', 16, 8, '수요일', 1, 3, 1, 203);
INSERT INTO class VALUES(62, 1, '과목62',16, 8, '목요일', 1, 4, 1, 204);
INSERT INTO class VALUES(63, 1, '과목63', 16, 8, '금요일', 1, 3, 1, 205);
INSERT INTO class VALUES(64, 1, '과목64', 16, 8, '월요일', 1, 3, 1, 215);
INSERT INTO class VALUES(65, 1, '과목65', 17, 9, '화요일', 1, 3, 1, 216);
INSERT INTO class VALUES(66, 1, '과목66', 17, 9, '수요일', 1, 3, 1, 217);
INSERT INTO class VALUES(67, 1, '과목67', 17, 9, '목요일', 1, 3, 1, 218);
INSERT INTO class VALUES(68, 1, '과목68', 17, 9, '수요일', 1, 4, 1, 227);
INSERT INTO class VALUES(69, 1, '과목69', 18, 9, '목요일', 1, 3, 1, 228);
INSERT INTO class VALUES(70, 1, '과목70', 18, 9, '금요일', 1, 3, 1, 229);
INSERT INTO class VALUES(71, 1, '과목71', 18, 9, '화요일', 1, 4, 1, 241);
INSERT INTO class VALUES(72, 1, '과목72', 18, 9, '수요일', 1, 3, 1, 242);
INSERT INTO class VALUES(73, 1, '과목73', 19, 10, '목요일', 1, 3, 1, 243);
INSERT INTO class VALUES(74, 1, '과목74', 19, 10, '금요일', 2, 4, 1, 244);
INSERT INTO class VALUES(75, 1, '과목75', 19, 10, '화요일', 1, 3, 1, 251);
INSERT INTO class VALUES(76, 1, '과목76', 19, 10, '수요일', 1, 4, 1, 252);
INSERT INTO class VALUES(77, 1, '과목77', 20, 10, '목요일', 2, 3, 1, 253);
INSERT INTO class VALUES(78, 1, '과목78', 20, 10, '금요일', 3, 1, 1, 254);
INSERT INTO class VALUES(79, 1, '과목79', 20,10, '월요일', 3, 2, 1, 255);
INSERT INTO class VALUES(80, 1, '과목80', 20, 10, '금요일', 1, 2, 1, 259);
INSERT INTO class VALUES(81, 1, '과목81', 21, 11, '월요일', 2, 4, 1, 260);
INSERT INTO class VALUES(82, 1, '과목82', 21, 11, '화요일', 3, 3, 1, 261);
INSERT INTO class VALUES(83, 1, '과목83', 21, 11, '금요일', 1, 2, 1, 264);
INSERT INTO class VALUES(84, 1, '과목84', 21, 11, '월요일', 1, 4, 1, 265);
INSERT INTO class VALUES(85, 1, '과목85', 22, 11, '화요일', 1, 3, 1, 266);
INSERT INTO class VALUES(86, 1, '과목86', 22, 11, '수요일', 2, 2, 1, 267);
INSERT INTO class VALUES(87, 1, '과목87', 22, 11, '목요일', 2, 3, 1, 268);
INSERT INTO class VALUES(88 ,1, '과목88', 22, 11, '월요일', 1, 4, 1, 201);
INSERT INTO class VALUES(89, 1, '과목89', 23, 12, '화요일', 1, 4, 1, 202);
INSERT INTO class VALUES(90, 1, '과목90', 23, 12, '수요일', 1, 3, 1, 203);
INSERT INTO class VALUES(91, 1, '과목91', 23, 12, '목요일', 1, 4, 1, 204);
INSERT INTO class VALUES(92, 1, '과목92', 23, 12, '금요일', 1, 3, 1, 205);
INSERT INTO class VALUES(93, 1, '과목93', 24, 12, '월요일', 1, 3, 1, 215);
INSERT INTO class VALUES(94, 1, '과목94', 24, 12, '화요일', 1, 3, 1, 216);
INSERT INTO class VALUES(95, 1, '과목95', 24, 12, '수요일', 1, 3, 1, 217);
INSERT INTO class VALUES(96, 1, '과목96', 24, 12, '목요일', 1, 3, 1, 218);
INSERT INTO class VALUES(97, 1, '과목97', 25, 13, '수요일', 1, 4, 1, 227);
INSERT INTO class VALUES(98, 1, '과목98', 25, 13, '목요일', 1, 3, 1, 228);
INSERT INTO class VALUES(99, 1, '과목99', 25, 13, '금요일', 1, 3, 1, 229);
INSERT INTO class VALUES(100, 1, '과목100', 25, 13, '화요일', 1, 4, 1, 241);
INSERT INTO class VALUES(101, 1, '과목101', 26, 13, '월요일', 1, 4, 1, 201);
INSERT INTO class VALUES(102, 1, '과목102', 26, 13, '화요일', 1, 4, 1, 202);
INSERT INTO class VALUES(103, 1, '과목103', 26, 13, '수요일', 1, 3, 1, 203);
INSERT INTO class VALUES(104, 1, '과목104', 26, 13, '목요일', 1, 4, 1, 204);
INSERT INTO class VALUES(105, 1, '과목105', 27, 14, '금요일', 1, 3, 1, 205);
INSERT INTO class VALUES(106, 1, '과목106', 27, 14, '월요일', 1, 3, 1, 206);
INSERT INTO class VALUES(107, 1, '과목107', 27, 14, '화요일', 1, 3, 1, 207);
INSERT INTO class VALUES(108, 1, '과목108', 27, 14, '수요일', 1, 3, 1, 208);
INSERT INTO class VALUES(109, 1, '과목109', 28 ,14, '목요일', 1, 3, 1, 209);
INSERT INTO class VALUES(110, 1, '과목110', 28, 14, '수요일', 1, 4, 1, 210);
INSERT INTO class VALUES(111, 1, '과목111', 28, 14, '목요일', 1, 3, 1, 211);
INSERT INTO class VALUES(112, 1, '과목112', 28, 14, '금요일', 1, 3, 1, 212);
INSERT INTO class VALUES(113, 1, '과목113', 29, 15, '화요일', 1, 4, 1, 213);
INSERT INTO class VALUES(114, 1, '과목114', 29, 15, '수요일', 1, 3, 1, 214);
INSERT INTO class VALUES(115, 1, '과목115', 29, 15, '목요일', 1, 3, 1, 215);
INSERT INTO class VALUES(116, 1, '과목116', 29, 15, '금요일', 2, 4, 1, 216);
INSERT INTO class VALUES(117, 1, '과목117', 30, 15, '화요일', 1, 3, 1, 217);
INSERT INTO class VALUES(118, 1, '과목118', 30, 15, '수요일', 1, 4, 1, 218);
INSERT INTO class VALUES(119, 1, '과목119', 30, 15, '목요일', 2, 3, 1, 219);
INSERT INTO class VALUES(120, 1, '과목120', 30, 15, '금요일', 3, 1, 1, 220);
INSERT INTO class VALUES(121, 1, '과목121', 31, 16, '월요일', 3, 2, 1, 221);
INSERT INTO class VALUES(122, 1, '과목122', 31, 16, '금요일', 1, 2, 1, 222);
INSERT INTO class VALUES(123, 1, '과목123', 31, 16, '월요일', 2, 4, 1, 223);
INSERT INTO class VALUES(124, 1, '과목124', 31, 16, '화요일', 3, 3, 1, 224);
INSERT INTO class VALUES(125, 1, '과목125', 32, 16, '금요일', 1, 2, 1, 225);
INSERT INTO class VALUES(126, 1, '과목126', 32, 16, '월요일', 1, 4, 1, 226);
INSERT INTO class VALUES(127, 1, '과목127', 32, 16, '화요일', 1, 3, 1, 227);
INSERT INTO class VALUES(128, 1, '과목128', 32, 16, '수요일', 2, 2, 1, 228);
INSERT INTO class VALUES(129, 1, '과목129', 33, 17, '목요일', 2, 3, 1, 229);
INSERT INTO class VALUES(130, 1, '과목130', 33, 17, '월요일', 1, 4, 1, 201);
INSERT INTO class VALUES(131, 1, '과목131', 33, 17, '화요일', 1, 4, 1, 202);
INSERT INTO class VALUES(132, 1, '과목132', 33, 17, '수요일', 1, 3, 1, 203);
INSERT INTO class VALUES(133, 1, '과목133', 34, 17, '목요일', 1, 4, 1, 204);
INSERT INTO class VALUES(134, 1, '과목134', 34, 17, '금요일', 1, 3, 1, 205);
INSERT INTO class VALUES(135, 1, '과목135', 34, 17, '월요일', 1, 3, 1, 215);
INSERT INTO class VALUES(136, 1, '과목136', 34, 17, '화요일', 1, 3, 1, 216);
INSERT INTO class VALUES(137, 1, '과목137', 35,18, '수요일', 1, 3, 1, 217);
INSERT INTO class VALUES(138, 1, '과목138', 35, 18, '목요일', 1, 3, 1, 218);
INSERT INTO class VALUES(139, 1, '과목139', 35, 18, '수요일', 1, 4, 1, 227);
INSERT INTO class VALUES(140, 1, '과목140', 35, 18, '목요일', 1, 3, 1, 228);
INSERT INTO class VALUES(141, 1, '과목141', 36, 18, '금요일', 1, 3, 1, 229);
INSERT INTO class VALUES(142, 1, '과목142', 36, 18, '화요일', 1, 4, 1, 241);
INSERT INTO class VALUES(143, 1, '과목143', 36, 18, '수요일', 1, 3, 1, 242);
INSERT INTO class VALUES(144, 1, '과목144', 36, 18, '목요일', 1, 3, 1, 243);
INSERT INTO class VALUES(145, 1, '과목145', 37, 19, '금요일', 2, 4, 1, 244);
INSERT INTO class VALUES(146, 1, '과목146', 37, 19, '화요일', 1, 3, 1, 251);
INSERT INTO class VALUES(147, 1, '과목147', 37, 19, '수요일', 1, 4, 1, 252);
INSERT INTO class VALUES(148, 1, '과목148', 37, 19, '목요일', 2, 3, 1, 253);
INSERT INTO class VALUES(149, 1, '과목149', 38, 19, '금요일', 3, 1, 1, 254);
INSERT INTO class VALUES(150, 1, '과목150', 38, 19, '월요일', 3, 2, 1, 255);
INSERT INTO class VALUES(151, 1, '과목151', 38, 19, '금요일', 1, 2, 1, 259);
INSERT INTO class VALUES(152, 1, '과목152', 38, 19, '월요일', 2, 4, 1, 260);
INSERT INTO class VALUES(153, 1, '과목153', 39, 20, '화요일', 3, 3, 1, 261);
INSERT INTO class VALUES(154, 1, '과목154', 39, 20, '금요일', 1, 2, 1, 264);
INSERT INTO class VALUES(155, 1, '과목155', 39, 20, '월요일', 1, 4, 1, 265);
INSERT INTO class VALUES(156, 1, '과목156', 39, 20, '화요일', 1, 3, 1, 266);
INSERT INTO class VALUES(157, 1, '과목157', 40, 20, '수요일', 2, 2, 1, 267);
INSERT INTO class VALUES(158, 1, '과목158', 40, 20, '목요일', 2, 3, 1, 268);
INSERT INTO class VALUES(159, 1, '과목159', 40, 20, '월요일', 1, 4, 1, 201);
INSERT INTO class VALUES(160, 1, '과목160', 40, 20, '화요일', 1, 4, 1, 202);
INSERT INTO class VALUES(161, 1, '과목161', 41, 21, '수요일', 1, 3, 1, 203);
INSERT INTO class VALUES(162, 1, '과목162', 41, 21, '목요일', 1, 4, 1, 204);
INSERT INTO class VALUES(163, 1, '과목163', 41, 21, '금요일', 1, 3, 1, 205);
INSERT INTO class VALUES(164, 1, '과목164', 41, 21, '월요일', 1, 3, 1, 215);
INSERT INTO class VALUES(165, 1, '과목165', 42, 21, '화요일', 1, 3, 1, 216);
INSERT INTO class VALUES(166, 1, '과목166', 42, 21, '수요일', 1, 3, 1, 217);
INSERT INTO class VALUES(167, 1, '과목167', 42, 21, '목요일', 1, 3, 1, 218);
INSERT INTO class VALUES(168, 1, '과목168', 42, 21, '수요일', 1, 4, 1, 227);
INSERT INTO class VALUES(169, 1, '과목169', 43, 22, '목요일', 1, 3, 1, 228);
INSERT INTO class VALUES(170, 1, '과목170', 43, 22, '금요일', 1, 3, 1, 229);
INSERT INTO class VALUES(171, 1, '과목171', 43, 22, '화요일', 1, 4, 1, 241);
INSERT INTO class VALUES(172, 1, '과목172', 43, 22, '수요일', 1, 3, 1, 242);
INSERT INTO class VALUES(173, 1, '과목173', 44, 22, '목요일', 1, 3, 1, 243);
INSERT INTO class VALUES(174, 1, '과목174', 44, 22, '금요일', 2, 4, 1, 244);
INSERT INTO class VALUES(175, 1, '과목175', 44, 22, '화요일', 1, 3, 1, 251);
INSERT INTO class VALUES(176, 1, '과목176', 44, 22, '수요일', 1, 4, 1, 252);
INSERT INTO class VALUES(177, 1, '과목177', 45, 23, '목요일', 2, 3, 1, 253);
INSERT INTO class VALUES(178, 1, '과목178', 45, 23, '금요일', 3, 1, 1, 254);
INSERT INTO class VALUES(179, 1, '과목179', 45, 23, '월요일', 3, 2, 1, 255);
INSERT INTO class VALUES(180, 1, '과목180', 45, 23, '금요일', 1, 2, 1, 259);
INSERT INTO class VALUES(181, 1, '과목181', 46, 23, '월요일', 2, 4, 1, 260);
INSERT INTO class VALUES(182, 1, '과목182', 46, 23, '화요일', 3, 3, 1, 261);
INSERT INTO class VALUES(183, 1, '과목183', 46, 23, '금요일', 1, 2, 1, 264);
INSERT INTO class VALUES(184, 1, '과목184', 46, 23, '월요일', 1, 4, 1, 265);
INSERT INTO class VALUES(185, 1, '과목185', 47, 24, '화요일', 1, 3, 1, 266);
INSERT INTO class VALUES(186, 1, '과목186', 47, 24, '수요일', 2, 2, 1, 267);
INSERT INTO class VALUES(187, 1, '과목187', 47, 24, '목요일', 2, 3, 1, 268);
INSERT INTO class VALUES(188 ,1, '과목188', 47, 24, '월요일', 1, 4, 1, 201);
INSERT INTO class VALUES(189, 1, '과목189', 48, 24, '화요일', 1, 4, 1, 202);
INSERT INTO class VALUES(190, 1, '과목190', 48, 24, '수요일', 1, 3, 1, 203);
INSERT INTO class VALUES(191, 1, '과목191', 48, 24, '목요일', 1, 4, 1, 204);
INSERT INTO class VALUES(192, 1, '과목192', 48, 24, '금요일', 1, 3, 1, 205);
INSERT INTO class VALUES(193, 1, '과목193', 49, 25, '월요일', 1, 3, 1, 215);
INSERT INTO class VALUES(194, 1, '과목194', 49, 25, '화요일', 1, 3, 1, 216);
INSERT INTO class VALUES(195, 1, '과목195', 49, 25, '수요일', 1, 3, 1, 217);
INSERT INTO class VALUES(196, 1, '과목196', 49, 25, '목요일', 1, 3, 1, 218);
INSERT INTO class VALUES(197, 1, '과목197', 50, 25, '수요일', 1, 4, 1, 227);
INSERT INTO class VALUES(198, 1, '과목198', 50, 25, '목요일', 1, 3, 1, 228);
INSERT INTO class VALUES(199, 1, '과목199', 50, 25, '금요일', 1, 3, 1, 229);
INSERT INTO class VALUES(200, 1, '과목200', 50, 25, '화요일', 1, 4, 1, 241);


/*classHistory table data*/
/*학생번호 강의번호 교수번호 중간 기말 기타 총점 학점*/
INSERT INTO classHistory VALUES('1', 1, 1, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('1', 2, 1, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('2', 3, 1, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('2', 4, 1, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('3', 5, 2, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('3', 6, 2, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('4', 7, 2, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('4', 8, 2, 100, 100, 100, 300, 'A');
INSERT INTO classHistory VALUES('5', 9, 3, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('5', 10, 3, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('6', 11, 3, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('6', 12, 3, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('7', 13, 4, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('7', 14, 4, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('8', 15, 4, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('8', 16, 4, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('9', 17, 5, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('9', 18, 5, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('10', 19, 5, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('10', 20, 5, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('11', 21, 6, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('11', 22, 6, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('12', 23, 6, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('12', 24, 6, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('13', 25, 7, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('13', 26, 7, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('14', 27, 7, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('14', 28, 7, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('15', 29, 8, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('15', 30, 8, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('16', 31, 8, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('16', 32, 8, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('17', 33, 9, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('17', 34, 9, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('18', 35, 9, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('18', 36, 9, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('19', 37, 10, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('19', 38, 10, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('20', 39, 10, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('20', 40, 10, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('21', 41, 11, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('21', 42, 11, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('22', 43, 11, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('22', 44, 11, 100, 100, 100, 300, 'A');
INSERT INTO classHistory VALUES('23', 45, 12, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('23', 46, 12, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('24', 47, 12, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('24', 48, 12, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('25', 49, 13, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('25', 50, 13, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('26', 51, 13, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('26', 52, 13, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('27', 53, 14, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('27', 54, 14, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('28', 55, 14, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('28', 56, 14, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('29', 57, 15, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('29', 58, 15, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('30', 59, 15, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('30', 60, 15, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('31', 61, 16, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('31', 62, 16, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('32', 63, 16, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('32', 64, 16, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('33', 65, 17, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('33', 66, 17, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('34', 67, 17, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('34', 68, 17, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('35', 69, 18, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('35', 70, 18, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('36', 71, 18, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('36', 72, 18, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('37', 73, 19, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('37', 74, 19, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('38', 75, 19, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('38', 76, 19, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('39', 77, 20, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('39', 78, 20, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('40', 79, 20, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('40', 80, 20, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('41', 81, 21, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('41', 82, 21, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('42', 83, 21, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('42', 84, 21, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('43', 85, 22, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('43', 86, 22, 100, 100, 100, 300, 'A');
INSERT INTO classHistory VALUES('44', 87, 22, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('44', 88, 22, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('45', 89, 23, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('45', 90, 23, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('46', 91, 23, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('46', 92, 23, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('47', 93, 24, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('47', 94, 24, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('48', 95, 24, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('48', 96, 24, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('49', 97, 25, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('49', 98, 25, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('50', 99, 25, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('50', 100, 25, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('51', 101, 26, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('51', 102, 26, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('52', 103, 26, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('52', 104, 26, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('53', 105, 27, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('53', 106, 27, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('54', 107, 27, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('54', 108, 27, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('55', 109, 28, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('55', 110, 28, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('56', 111, 28, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('56', 112, 28, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('57', 113, 29, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('57', 114, 29, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('58', 115, 29, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('58', 116, 29, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('59', 117, 30, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('59', 118, 30, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('60', 119, 30, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('60', 120, 30, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('61', 121, 31, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('61', 122, 31, 100, 100, 100, 300, 'A');
INSERT INTO classHistory VALUES('62', 123, 31, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('62', 124, 31, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('63', 125, 32, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('63', 126, 32, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('64', 127, 32, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('64', 128, 32, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('65', 129, 33, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('65', 130, 33, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('66', 131, 33, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('66', 132, 33, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('67', 133, 34, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('67', 134, 34, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('68', 135, 34, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('68', 136, 34, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('69', 137, 35, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('69', 138, 35, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('70', 139, 35, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('70', 140, 35, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('71', 141, 36, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('71', 142, 36, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('72', 143, 36, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('72', 144, 36, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('73', 145, 37, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('73', 146, 37, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('74', 147, 37, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('74', 148, 37, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('75', 149, 38, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('75', 150, 38, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('76', 151, 38, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('76', 152, 38, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('77', 153, 39, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('77', 154, 39, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('78', 155, 39, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('78', 156, 39, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('79', 157, 40, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('79', 158, 40, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('80', 159, 40, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('80', 160, 40, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('81', 161, 41, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('81', 162, 41, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('82', 163, 41, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('82', 164, 41, 100, 100, 100, 300, 'A');
INSERT INTO classHistory VALUES('83', 165, 42, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('83', 166, 42, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('84', 167, 42, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('84', 168, 42, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('85', 169, 43, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('85', 170, 43, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('86', 171, 43, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('86', 172, 43, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('87', 173, 44, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('87', 174, 44, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('88', 175, 44, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('88', 176, 44, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('89', 177, 45, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('89', 178, 45, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('90', 179, 45, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('90', 180, 45, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('91', 181, 46, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('91', 182, 46, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('92', 183, 46, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('92', 184, 46, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('93', 185, 47, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('93', 186, 47, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('94', 187, 47, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('94', 188, 47, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('95', 189, 48, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('95', 190, 48, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('96', 191, 48, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('96', 192, 48, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('97', 193, 49, 60, 60, 60, 180, 'D');
INSERT INTO classHistory VALUES('97', 194, 49, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('98', 195, 49, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('98', 196, 49, 90, 90, 90, 270, 'A');
INSERT INTO classHistory VALUES('99', 197, 50, 70, 70, 70, 210, 'C');
INSERT INTO classHistory VALUES('99', 198, 50, 80, 80, 80, 240, 'B');
INSERT INTO classHistory VALUES('100', 199, 50, 50, 50, 50, 150, 'F');
INSERT INTO classHistory VALUES('100', 200, 50, 100, 100, 100, 300, 'A');

/* tuition table data*/
/*학생번호 년도 학기 등록금 등록금총합 마지막으로지불한날짜*/
INSERT INTO tuition VALUES(1, '2021', 7, 4000000, 28000000,'2021-02-03');
INSERT INTO tuition VALUES(2, '2021', 7, 4000000, 28000000,'2021-02-13');
INSERT INTO tuition VALUES(3, '2021', 7, 4000000, 28000000,'2021-02-22');
INSERT INTO tuition VALUES(4, '2021', 6, 4000000, 24000000,'2021-02-21');
INSERT INTO tuition VALUES(5, '2021', 5, 4000000, 20000000,'2021-02-11');
INSERT INTO tuition VALUES(6, '2021', 4, 4000000, 16000000,'2021-02-15');
INSERT INTO tuition VALUES(7, '2021', 5, 4000000, 20000000,'2021-02-12');
INSERT INTO tuition VALUES(8, '2021', 4, 4000000, 16000000,'2021-02-14');
INSERT INTO tuition VALUES(9, '2021', 5, 4000000, 20000000,'2021-02-01');
INSERT INTO tuition VALUES(10, '2021', 5, 4000000, 20000000,'2021-02-04');
INSERT INTO tuition VALUES(11, '2021', 4, 4000000, 16000000,'2021-02-23');
INSERT INTO tuition VALUES(12, '2021', 4, 4000000, 16000000,'2021-02-13');
INSERT INTO tuition VALUES(13, '2021', 3, 4000000, 12000000,'2021-02-06');
INSERT INTO tuition VALUES(14, '2021', 7, 4000000, 28000000,'2021-02-03');
INSERT INTO tuition VALUES(15, '2021', 7, 4000000, 28000000,'2021-02-08');
INSERT INTO tuition VALUES(16, '2021', 5, 4000000, 20000000,'2021-02-03');
INSERT INTO tuition VALUES(17, '2021', 7, 4000000, 28000000,'2021-02-11');
INSERT INTO tuition VALUES(18, '2021', 7, 4000000, 28000000,'2021-02-12');
INSERT INTO tuition VALUES(19, '2021', 4, 4000000, 16000000,'2021-02-20');
INSERT INTO tuition VALUES(20, '2021', 5, 4000000, 20000000,'2021-02-15');
INSERT INTO tuition VALUES(21, '2021', 6, 4000000, 24000000,'2021-02-07');
INSERT INTO tuition VALUES(22, '2021', 6, 4000000, 24000000,'2021-02-01');
INSERT INTO tuition VALUES(23, '2021', 5, 4000000, 20000000,'2021-02-05');
INSERT INTO tuition VALUES(24, '2021', 5, 4000000, 20000000,'2021-02-19');
INSERT INTO tuition VALUES(25, '2021', 3, 4000000, 12000000,'2021-02-17');
INSERT INTO tuition VALUES(26, '2021', 3, 4000000, 12000000,'2021-02-26');
INSERT INTO tuition VALUES(27, '2021', 7, 4000000, 28000000,'2021-02-03');
INSERT INTO tuition VALUES(28, '2021', 7, 4000000, 28000000,'2021-02-13');
INSERT INTO tuition VALUES(29, '2021', 7, 4000000, 28000000,'2021-02-22');
INSERT INTO tuition VALUES(30, '2021', 6, 4000000, 24000000,'2021-02-21');
INSERT INTO tuition VALUES(31, '2021', 5, 4000000, 20000000,'2021-02-11');
INSERT INTO tuition VALUES(32, '2021', 4, 4000000, 16000000,'2021-02-15');
INSERT INTO tuition VALUES(33, '2021', 5, 4000000, 20000000,'2021-02-12');
INSERT INTO tuition VALUES(34, '2021', 4, 4000000, 16000000,'2021-02-14');
INSERT INTO tuition VALUES(35, '2021', 5, 4000000, 20000000,'2021-02-01');
INSERT INTO tuition VALUES(36, '2021', 5, 4000000, 20000000,'2021-02-04');
INSERT INTO tuition VALUES(37, '2021', 4, 4000000, 16000000,'2021-02-23');
INSERT INTO tuition VALUES(38, '2021', 4, 4000000, 16000000,'2021-02-13');
INSERT INTO tuition VALUES(39, '2021', 3, 4000000, 12000000,'2021-02-06');
INSERT INTO tuition VALUES(40, '2021', 7, 4000000, 28000000,'2021-02-03');
INSERT INTO tuition VALUES(41, '2021', 7, 4000000, 28000000,'2021-02-08');
INSERT INTO tuition VALUES(42, '2021', 5, 4000000, 20000000,'2021-02-03');
INSERT INTO tuition VALUES(43, '2021', 7, 4000000, 28000000,'2021-02-11');
INSERT INTO tuition VALUES(44, '2021', 7, 4000000, 28000000,'2021-02-12');
INSERT INTO tuition VALUES(45, '2021', 4, 4000000, 16000000,'2021-02-20');
INSERT INTO tuition VALUES(46, '2021', 5, 4000000, 20000000,'2021-02-15');
INSERT INTO tuition VALUES(47, '2021', 6, 4000000, 24000000,'2021-02-07');
INSERT INTO tuition VALUES(48, '2021', 6, 4000000, 24000000,'2021-02-01');
INSERT INTO tuition VALUES(49, '2021', 5, 4000000, 20000000,'2021-02-05');
INSERT INTO tuition VALUES(50, '2021', 5, 4000000, 20000000,'2021-02-19');
INSERT INTO tuition VALUES(51, '2021', 3, 4000000, 12000000,'2021-02-17');
INSERT INTO tuition VALUES(52, '2021', 3, 4000000, 12000000,'2021-02-26');
INSERT INTO tuition VALUES(53, '2021', 7, 4000000, 28000000,'2021-02-03');
INSERT INTO tuition VALUES(54, '2021', 7, 4000000, 28000000,'2021-02-13');
INSERT INTO tuition VALUES(55, '2021', 7, 4000000, 28000000,'2021-02-22');
INSERT INTO tuition VALUES(56, '2021', 6, 4000000, 24000000,'2021-02-21');
INSERT INTO tuition VALUES(57, '2021', 5, 4000000, 20000000,'2021-02-11');
INSERT INTO tuition VALUES(58, '2021', 4, 4000000, 16000000,'2021-02-15');
INSERT INTO tuition VALUES(59, '2021', 5, 4000000, 20000000,'2021-02-12');
INSERT INTO tuition VALUES(60, '2021', 4, 4000000, 16000000,'2021-02-14');
INSERT INTO tuition VALUES(61, '2021', 5, 4000000, 20000000,'2021-02-01');
INSERT INTO tuition VALUES(62, '2021', 5, 4000000, 20000000,'2021-02-04');
INSERT INTO tuition VALUES(63, '2021', 4, 4000000, 16000000,'2021-02-23');
INSERT INTO tuition VALUES(64, '2021', 4, 4000000, 16000000,'2021-02-13');
INSERT INTO tuition VALUES(65, '2021', 3, 4000000, 12000000,'2021-02-06');
INSERT INTO tuition VALUES(66, '2021', 7, 4000000, 28000000,'2021-02-03');
INSERT INTO tuition VALUES(67, '2021', 7, 4000000, 28000000,'2021-02-08');
INSERT INTO tuition VALUES(68, '2021', 5, 4000000, 20000000,'2021-02-03');
INSERT INTO tuition VALUES(69, '2021', 7, 4000000, 28000000,'2021-02-11');
INSERT INTO tuition VALUES(70, '2021', 7, 4000000, 28000000,'2021-02-12');
INSERT INTO tuition VALUES(71, '2021', 4, 4000000, 16000000,'2021-02-20');
INSERT INTO tuition VALUES(72, '2021', 5, 4000000, 20000000,'2021-02-15');
INSERT INTO tuition VALUES(73, '2021', 6, 4000000, 24000000,'2021-02-07');
INSERT INTO tuition VALUES(74, '2021', 6, 4000000, 24000000,'2021-02-01');
INSERT INTO tuition VALUES(75, '2021', 5, 4000000, 20000000,'2021-02-05');
INSERT INTO tuition VALUES(76, '2021', 5, 4000000, 20000000,'2021-02-19');
INSERT INTO tuition VALUES(77, '2021', 3, 4000000, 12000000,'2021-02-17');
INSERT INTO tuition VALUES(78, '2021', 3, 4000000, 12000000,'2021-02-26');
INSERT INTO tuition VALUES(79, '2021', 7, 4000000, 28000000,'2021-02-03');
INSERT INTO tuition VALUES(80, '2021', 7, 4000000, 28000000,'2021-02-13');
INSERT INTO tuition VALUES(81, '2021', 7, 4000000, 28000000,'2021-02-22');
INSERT INTO tuition VALUES(82, '2021', 6, 4000000, 24000000,'2021-02-21');
INSERT INTO tuition VALUES(83, '2021', 5, 4000000, 20000000,'2021-02-11');
INSERT INTO tuition VALUES(84, '2021', 4, 4000000, 16000000,'2021-02-15');
INSERT INTO tuition VALUES(85, '2021', 5, 4000000, 20000000,'2021-02-12');
INSERT INTO tuition VALUES(86, '2021', 4, 4000000, 16000000,'2021-02-14');
INSERT INTO tuition VALUES(87, '2021', 5, 4000000, 20000000,'2021-02-01');
INSERT INTO tuition VALUES(88, '2021', 5, 4000000, 20000000,'2021-02-04');
INSERT INTO tuition VALUES(89, '2021', 4, 4000000, 16000000,'2021-02-23');
INSERT INTO tuition VALUES(90, '2021', 4, 4000000, 16000000,'2021-02-13');
INSERT INTO tuition VALUES(91, '2021', 3, 4000000, 12000000,'2021-02-06');
INSERT INTO tuition VALUES(92, '2021', 7, 4000000, 28000000,'2021-02-03');
INSERT INTO tuition VALUES(93, '2021', 7, 4000000, 28000000,'2021-02-08');
INSERT INTO tuition VALUES(94, '2021', 5, 4000000, 20000000,'2021-02-03');
INSERT INTO tuition VALUES(95, '2021', 7, 4000000, 28000000,'2021-02-11');
INSERT INTO tuition VALUES(96, '2021', 7, 4000000, 28000000,'2021-02-12');
INSERT INTO tuition VALUES(97, '2021', 4, 4000000, 16000000,'2021-02-20');
INSERT INTO tuition VALUES(98, '2021', 5, 4000000, 20000000,'2021-02-15');
INSERT INTO tuition VALUES(99, '2021', 6, 4000000, 24000000,'2021-02-07');
INSERT INTO tuition VALUES(100, '2021', 6, 4000000, 24000000,'2021-02-01');

/*club table data*/
INSERT INTO club VALUE(1,'동아리1',2,601,1,1);
INSERT INTO club VALUE(2,'동아리2',2,602,3,4);
INSERT INTO club VALUE(3,'동아리3',2,603,5,8);
INSERT INTO club VALUE(4,'동아리4',2,604,7,12);
INSERT INTO club VALUE(5,'동아리5',2,605,9,16);
INSERT INTO club VALUE(6,'동아리6',2,606,11,20);
INSERT INTO club VALUE(7,'동아리7',2,607,13,24);
INSERT INTO club VALUE(8,'동아리8',2,608,15,28);
INSERT INTO club VALUE(9,'동아리9',2,601,17,32);
INSERT INTO club VALUE(10,'동아리10',2,602,19,36);
INSERT INTO club VALUE(11,'동아리11',2,603,21,40);
INSERT INTO club VALUE(12,'동아리12',2,604,23,44);
INSERT INTO club VALUE(13,'동아리13',2,605,25,48);
INSERT INTO club VALUE(14,'동아리14',2,606,27,52);
INSERT INTO club VALUE(15,'동아리15',2,607,29,56);
INSERT INTO club VALUE(16,'동아리16',2,608,31,60);
INSERT INTO club VALUE(17,'동아리17',2,601,33,64);
INSERT INTO club VALUE(18,'동아리18',2,602,35,68);
INSERT INTO club VALUE(19,'동아리19',2,603,37,72);
INSERT INTO club VALUE(20,'동아리20',2,604,39,76);
INSERT INTO club VALUE(21,'동아리21',2,605,41,80);
INSERT INTO club VALUE(22,'동아리22',2,606,43,84);
INSERT INTO club VALUE(23,'동아리23',2,607,45,88);
INSERT INTO club VALUE(24,'동아리24',2,608,47,92);
INSERT INTO club VALUE(25,'동아리25',2,601,49,96);

/*clubStudentList table data*/
INSERT INTO clubStudentList VALUE('1','1');
INSERT INTO clubStudentList VALUE('1','3');
INSERT INTO clubStudentList VALUE('2','4');
INSERT INTO clubStudentList VALUE('2','5');
INSERT INTO clubStudentList VALUE('3','8');
INSERT INTO clubStudentList VALUE('3','9');
INSERT INTO clubStudentList VALUE('4','12');
INSERT INTO clubStudentList VALUE('4','13');
INSERT INTO clubStudentList VALUE('5','16');
INSERT INTO clubStudentList VALUE('5','17');
INSERT INTO clubStudentList VALUE('6','20');
INSERT INTO clubStudentList VALUE('6','21');
INSERT INTO clubStudentList VALUE('7','24');
INSERT INTO clubStudentList VALUE('7','25');
INSERT INTO clubStudentList VALUE('8','28');
INSERT INTO clubStudentList VALUE('8','29');
INSERT INTO clubStudentList VALUE('9','32');
INSERT INTO clubStudentList VALUE('9','33');
INSERT INTO clubStudentList VALUE('10','36');
INSERT INTO clubStudentList VALUE('10','37');
INSERT INTO clubStudentList VALUE('11','40');
INSERT INTO clubStudentList VALUE('11','41');
INSERT INTO clubStudentList VALUE('12','44');
INSERT INTO clubStudentList VALUE('12','45');
INSERT INTO clubStudentList VALUE('13','48');
INSERT INTO clubStudentList VALUE('13','49');
INSERT INTO clubStudentList VALUE('14','52');
INSERT INTO clubStudentList VALUE('14','53');
INSERT INTO clubStudentList VALUE('15','56');
INSERT INTO clubStudentList VALUE('15','57');
INSERT INTO clubStudentList VALUE('16','60');
INSERT INTO clubStudentList VALUE('16','61');
INSERT INTO clubStudentList VALUE('17','64');
INSERT INTO clubStudentList VALUE('17','65');
INSERT INTO clubStudentList VALUE('18','68');
INSERT INTO clubStudentList VALUE('18','69');
INSERT INTO clubStudentList VALUE('19','72');
INSERT INTO clubStudentList VALUE('19','73');
INSERT INTO clubStudentList VALUE('20','76');
INSERT INTO clubStudentList VALUE('20','77');
INSERT INTO clubStudentList VALUE('21','80');
INSERT INTO clubStudentList VALUE('21','81');
INSERT INTO clubStudentList VALUE('22','84');
INSERT INTO clubStudentList VALUE('22','85');
INSERT INTO clubStudentList VALUE('23','88');
INSERT INTO clubStudentList VALUE('23','89');
INSERT INTO clubStudentList VALUE('24','92');
INSERT INTO clubStudentList VALUE('24','93');
INSERT INTO clubStudentList VALUE('25','96');
INSERT INTO clubStudentList VALUE('25','97');


commit;