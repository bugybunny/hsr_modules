-- Foreign Keys
ALTER TABLE BankCustomer ADD FOREIGN KEY(Customer_AddressId) REFERENCES Address(AddressId);
ALTER TABLE BankManager ADD FOREIGN KEY(Manager_AddressId) REFERENCES Address(AddressId);
ALTER TABLE BankAccount ADD FOREIGN KEY(Account_CustomerId) REFERENCES BankCustomer(CustomerId);
ALTER TABLE CustomerManager ADD FOREIGN KEY(CustomerId) REFERENCES BankCustomer(CustomerId);
ALTER TABLE CustomerManager ADD FOREIGN KEY(ManagerId) REFERENCES BankManager(ManagerId);

ALTER SEQUENCE bankcustomer_customerid_seq RESTART WITH 100;
ALTER SEQUENCE bankaccount_accountid_seq RESTART WITH 100;
ALTER SEQUENCE bankmanager_managerid_seq RESTART WITH 100;
ALTER SEQUENCE address_addressid_seq RESTART WITH 100;