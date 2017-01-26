pwd : Kiran86
port : 3306
username : root
Databse : pegasus
pwd : Kiran86

C:\Users\Kiran>mysql -u root -p

CREATE TABLE user(
    user_id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(40) NOT NULL unique,
    email_id varchar(50),
    created_date DATE,
	password varchar(10),
    PRIMARY KEY ( user_id,username )
    );
	


CREATE TABLE post(
    post_id INT NOT NULL AUTO_INCREMENT,
    Title VARCHAR(60) ,
    username VARCHAR(40) ,
    posted_date DATE,
	posted_data Mediumtext,
	PRIMARY KEY ( post_id ),
	FOREIGN KEY (username) REFERENCES user(username)
    );



CREATE TABLE tag(
    tag_id INT NOT NULL AUTO_INCREMENT,
    post_id int(11) ,
    Tag_name varchar(20),
	PRIMARY KEY ( tag_id ),
	FOREIGN KEY (post_id) REFERENCES post(post_id)
    );


select table_name from information_schema.tables where table_schema='<pegasus>';

insert into user(username,email_id,created_date,password) values('manikanta','mmhd3@mail.umkc.edu',CURRENT_TIMESTAMP(),12345);
insert into user(username,email_id,created_date,password) values('eswar','eswar2596@gmail.com',CURRENT_TIMESTAMP(),12345);
insert into user(username,email_id,created_date,password) values('sravan','sravanvarma03@gmail.com',CURRENT_TIMESTAMP(),12345);
insert into user(username,email_id,created_date,password) values('dheeraj','dheerajkanukuntla@gmail.com',CURRENT_TIMESTAMP(),12345);
insert into user(username,email_id,created_date,password) values('vamshi','mmhd3@mail.umkc.edu',CURRENT_TIMESTAMP(),12345);
insert into user(username,email_id,created_date,password) values('david','eswar2596@gmail.com',CURRENT_TIMESTAMP(),12345);
insert into user(username,email_id,created_date,password) values('john','sravanvarma03@gmail.com',CURRENT_TIMESTAMP(),12345);
insert into user(username,email_id,created_date,password) values('edward','dheerajkanukuntla@gmail.com',CURRENT_TIMESTAMP(),12345);
insert into user(username,email_id,created_date,password) values('michel','mmhd3@mail.umkc.edu',CURRENT_TIMESTAMP(),12345);
insert into user(username,email_id,created_date,password) values('kalamm','eswar2596@gmail.com',CURRENT_TIMESTAMP(),12345);

select post_id from tag where post_id is not null limit 20;


create table tmp (sno varchar(10),username varchar(40),foreign key(username)references user_1(username));