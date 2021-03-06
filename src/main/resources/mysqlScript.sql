drop database walletapp;
create database walletapp;
use walletapp;

create table Customer(customerId int primary key auto_increment, 
	firstName varchar(20) not null, 
	lastName varchar(20), 
	emailId varchar(20) unique,
	password varchar(20) not null, contactNo varchar(20));


 Create table Address(addressId int primary key auto_increment,
	 addressLine1 varchar(20) not null, 
	 addressLine2 varchar(20),
	 city varchar(20), 
	 state varchar(20), 
	 pincode varchar(10),
	 customerId int references Customer(customerId));

create table Account(accno int primary key auto_increment, 
	accountType varchar(20) not null, 
	openingDate date, 
	balance numeric(9,2),
	description varchar(25),
    customerId int references Customer(customerId));
    
create table transaction(transcationId int primary key auto_increment,
    transactionType varchar(10) not null,
    transactionDateTime datetime,
    descreption varchar(25),
    fromAccount int references Account(accno),
    toAccount int references Account(accno));

