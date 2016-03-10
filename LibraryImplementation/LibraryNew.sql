create database if not exists Library;
use Library;

drop table if exists Book;
drop table if exists Reader;
drop table if exists TakeBook;
drop table if exists GetBook;


create table Reader
(
  rname char(20),
  raddress char (30),
  rphone integer,
  rbirsday date not null,
  rreadercard char not null,
  constraint reader primary key (rreadercard)
);

create table Book
(
bid integer not null,
btitle char(30),
bprice integer,
constraint book primary key (bid)
);

create table TakeBook
(
bid integer not null,
rreadercard integer not null,
day DATE not null
);

create table GetBook
(
bid integer not null,
rreadercard integer not null,
day DATE not null
);

INSERT INTO Reader VALUES("Natalia Kiselyk", "Naukova str", 0938258539, "1994-08-19", 0123456);
INSERT INTO Reader VALUES ("Anita Borg", "Promuslova str", 0897654567, "1964-01-30", 547652);
INSERT INTO Reader VALUES("Stephan Korolko", "Skovorodu str", 012345439, "1988-11-10", 056789);
INSERT INTO Reader VALUES("Oksana Frankiv", "Peremohy avenu", 0684356212, "1990-05-19", 7834562);
INSERT INTO Reader VALUES("Orest Vatamanuk", "Hryhorenka pr", 0634567321, "1984-11-10", 908789);
INSERT INTO Reader VALUES("Steve Jobs", "Independence avenu", 0667895432, "1973-12-12", 121212);
INSERT INTO Reader VALUES("Halyna Voronina", "Vyhovskogo str", 0934567298, "1991-09-09", 1234098);

INSERT INTO Book VALUES(1, "The Phantom of The Opera", 23); 
INSERT INTO Book VALUES(2, "Adeline", 122);
INSERT INTO Book VALUES(3, "Public Relations", 69);
INSERT INTO Book VALUES(4, "If I Stay", 14);
INSERT INTO Book VALUES(5, "Henry VII", 200);
INSERT INTO Book VALUES(6, "The Christmas Carol", 65); 
INSERT INTO Book VALUES(7, "The Picture of Dorian Grey", 90);

INSERT INTO TakeBook VALUES(1, 0123456, "2015-11-01");
 INSERT INTO TakeBook VALUES(4, 908789, "2015-09-09");
 INSERT INTO TakeBook VALUES(1, 121212, "2015-10-03"); 
INSERT INTO TakeBook VALUES(6, 0123456, "2015-01-09");
 INSERT INTO TakeBook VALUES(7, 1234098, "2014-08-31");
 INSERT INTO TakeBook VALUES(5, 121212, "2015-10-29"); 
 INSERT INTO TakeBook VALUES(4, 908789, "2012-08-31"); 
 INSERT INTO TakeBook VALUES(2, 7834562, "2015-03-11");
 INSERT INTO TakeBook VALUES(7, 547652, "2013-08-21"); 
 INSERT INTO TakeBook VALUES(2, 056789, "2015-05-12"); 
 INSERT INTO TakeBook VALUES(3, 1234098, "2015-09-15");
 INSERT INTO TakeBook VALUES(6, 7834562, "2012-02-09");
 INSERT INTO TakeBook VALUES(3, 056789, "2011-11-11");
 
INSERT INTO GetBook VALUES(1, 0123456, "2015-11-21"); 
INSERT INTO GetBook VALUES(4, 908789, "2015-09-27"); 
INSERT INTO GetBook VALUES(1, 121212, "2015-11-03"); 
INSERT INTO GetBook VALUES(6, 0123456, "2015-06-06");
INSERT INTO GetBook VALUES(7, 1234098, "2014-09-30");
