create database bootcamp;
connect bootcamp;
create table exercise(id int(11) primary key auto_increment, title varchar(255), description text);
create table user_group(id int(11) primary key auto_increment, name varchar(255));
create table users(id BIGINT(20) primary key auto_increment, username varchar(255), email varchar(255) UNIQUE, password varchar(60), group_id int,
foreign key(group_id) references user_group(id));
create table solution(id int(11) primary key auto_increment, created datetime, updated datetime, description text, exercise_id int, users_id BIGINT(20),
foreign key(exercise_id) references exercise(id), foreign key(users_id) references users(id));