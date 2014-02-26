-- Test Data
INSERT INTO Address VALUES(6, '1242 1st Ave', 5414, 'Santa Fe');
INSERT INTO Address VALUES(3, '7 Market Street', 123456, 'Los Angeles');
INSERT INTO Address VALUES(9, '321 132. Ave', 432, 'New York');
INSERT INTO Address VALUES(12, '5434, 173 St', 3213, 'Boston');
INSERT INTO Address VALUES(15, '1, Old Town St', 8321, 'Seattle');
INSERT INTO Address VALUES(18, 'Hauptstrasse 2', 8000, 'Zürich');
INSERT INTO Address VALUES(21, 'Bahnhofgasse 6', 6000, 'Zug');
INSERT INTO Address VALUES(24, 'Feldweg 2', 5432, 'Dübendorf');
INSERT INTO Address VALUES(27, '1, rue des champs', 1000, 'Genève');
INSERT INTO Address VALUES(30, '1, baker street', 635123, 'London');
INSERT INTO Address VALUES(33, 'Seeweg 2', 56555, 'Berlin');

INSERT INTO BankCustomer VALUES(1, 'Paul Meier', '1980-10-10', 27);
INSERT INTO BankCustomer VALUES(2, 'Peter Huber', '1970-03-03', 24);
INSERT INTO BankCustomer VALUES(3, 'Alice Müller', '1960-04-04', 21);
INSERT INTO BankCustomer VALUES(4, 'Berta Bauer', '1975-03-02', 18);
INSERT INTO BankCustomer VALUES(5, 'Samuel Schneider', '1982-01-01', 30);
INSERT INTO BankCustomer VALUES(6, 'Petra Müller', '1974-04-04', 33);

INSERT INTO BankManager VALUES(1, 'John Smith', 3);
INSERT INTO BankManager VALUES(2, 'Doug Rich', 6);
INSERT INTO BankManager VALUES(3, 'Jane Baker', 9);
INSERT INTO BankManager VALUES(4, 'Diane Levy', 12);

INSERT INTO BankAccount VALUES(2, 1, 1000, 'CHF');
INSERT INTO BankAccount VALUES(6, 2, -200, 'CHF');
INSERT INTO BankAccount VALUES(8, 3, 100000, 'CHF');
INSERT INTO BankAccount VALUES(12, 3, -400, 'CHF');
INSERT INTO BankAccount VALUES(26, 5, -900, 'CHF');
INSERT INTO BankAccount VALUES(4, 1, 4000, 'EUR');
INSERT INTO BankAccount VALUES(10, 3, 7000, 'USD');
INSERT INTO BankAccount VALUES(14, 4, 80000, 'USD');
INSERT INTO BankAccount VALUES(16, 5, 1200, 'CHF');
INSERT INTO BankAccount VALUES(18, 5, 3000, 'EUR');
INSERT INTO BankAccount VALUES(20, 5, 0, 'USD');
INSERT INTO BankAccount VALUES(22, 5, 1000, 'JPY');
INSERT INTO BankAccount VALUES(24, 5, 70, 'GBP');
INSERT INTO BankAccount VALUES(28, 6, 1000000, 'EUR');

INSERT INTO CustomerManager VALUES(1, 2);
INSERT INTO CustomerManager VALUES(1, 3);
INSERT INTO CustomerManager VALUES(1, 4);
INSERT INTO CustomerManager VALUES(2, 1);
INSERT INTO CustomerManager VALUES(3, 1);
INSERT INTO CustomerManager VALUES(3, 4);
INSERT INTO CustomerManager VALUES(6, 4);
INSERT INTO CustomerManager VALUES(6, 1);
