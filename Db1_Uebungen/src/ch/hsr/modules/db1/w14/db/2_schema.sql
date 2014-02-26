--Tables and Types

CREATE TABLE BankCustomer(
  CustomerId SERIAL NOT NULL PRIMARY KEY,
  Name TEXT NOT NULL,
  Birthdate DATE,
  Customer_AddressId INTEGER);

CREATE TABLE Address(
  AddressId SERIAL NOT NULL PRIMARY KEY,
  Street TEXT NOT NULL,
  Zip INTEGER,
  City TEXT NOT NULL);

CREATE TABLE BankAccount(
  AccountId SERIAL NOT NULL PRIMARY KEY,
  Account_CustomerId INTEGER NOT NULL,
  Balance DOUBLE PRECISION NOT NULL,
  Currency TEXT NOT NULL DEFAULT 'CHF'
);

CREATE TABLE BankManager(
  ManagerId SERIAL NOT NULL PRIMARY KEY,
  Name TEXT NOT NULL,
  Manager_AddressId INTEGER
);

CREATE TABLE CustomerManager(
  CustomerId INTEGER NOT NULL,
  ManagerId INTEGER NOT NULL,
  PRIMARY KEY(CustomerId, ManagerId)
);
