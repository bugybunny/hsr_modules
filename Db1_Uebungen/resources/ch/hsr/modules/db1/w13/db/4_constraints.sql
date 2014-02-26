-- Foreign Keys
ALTER TABLE BankCustomer ADD FOREIGN KEY(Customer_AddressId) REFERENCES Address(AddressId);
ALTER TABLE BankManager ADD FOREIGN KEY(Manager_AddressId) REFERENCES Address(AddressId);
ALTER TABLE BankAccount ADD FOREIGN KEY(Account_CustomerId) REFERENCES BankCustomer(CustomerId);
ALTER TABLE CustomerManager ADD FOREIGN KEY(CustomerId) REFERENCES BankCustomer(CustomerId);
ALTER TABLE CustomerManager ADD FOREIGN KEY(ManagerId) REFERENCES BankManager(ManagerId);
