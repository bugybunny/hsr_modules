INSERT INTO vendingmachine (
        vendingmachineid, owner, address, machineActive, acceptedpaymentmethods, return5centpieces,
        return10centpieces, return20centpieces, return50centpieces,
        return1francpieces, return2francpieces, return5francpieces)
    VALUES (1, 'Dallmayr', 'HSR Hochschule für Technik Rapperswil, Oberseestrasse 10', 'on',
        ARRAY['cash', 'debit card', 'credit card']::paymentmethods[],
        555, 1010, 2020, 5050, 11,22, 55);

INSERT INTO vendingmachine (
        vendingmachineid, owner, address, machineActive, acceptedpaymentmethods, return5centpieces,
        return10centpieces, return20centpieces, return50centpieces,
        return1francpieces, return2francpieces, return5francpieces)
    VALUES (2, 'Selecta GmBH', 'HSR Hochschule für Technik Rapperswil, Oberseestrasse 10', 'on',
        ARRAY['cash', 'debit card', 'credit card']::paymentmethods[],
        555, 1010, 2020, 5050, 11,22, 55);

INSERT INTO vendingmachine (
        vendingmachineid, owner, address, machineActive, acceptedpaymentmethods, return5centpieces,
        return10centpieces, return20centpieces, return50centpieces,
        return1francpieces, return2francpieces, return5francpieces)
    VALUES (3, 'Selecta GmBH', 'Rapperswilstrasse 10', 'on',
        ARRAY['cash', 'debit card', 'cash card']::paymentmethods[],
        555, 1010, 2020, 5050, 11,22, 55);

INSERT INTO vendingmachine (
        vendingmachineid, owner, address, machineActive, acceptedpaymentmethods, return5centpieces,
        return10centpieces, return20centpieces, return50centpieces,
        return1francpieces, return2francpieces, return5francpieces)
    VALUES (4, 'Selecta GmBH', 'Rapperswilstrasse 7', 'off',
        ARRAY['cash', 'debit card', 'cash card']::paymentmethods[],
        555, 1010, 2020, 5050, 11,22, 55);

INSERT INTO vendingmachine (
        vendingmachineid, owner, address, machineActive, acceptedpaymentmethods, return5centpieces,
        return10centpieces, return20centpieces, return50centpieces,
        return1francpieces, return2francpieces, return5francpieces)
    VALUES (5, 'Selecta GmBH', 'Rapperswilstrasse 6', 'on',
        ARRAY['cash', 'debit card', 'cash card']::paymentmethods[],
        555, 1010, 2020, 5050, 11,22, 55);

INSERT INTO vendingmachine (
        vendingmachineid, owner, address, machineActive, acceptedpaymentmethods, return5centpieces,
        return10centpieces, return20centpieces, return50centpieces,
        return1francpieces, return2francpieces, return5francpieces)
    VALUES (6, 'Selecta GmBH', 'Rapperswilstrasse 5', 'on',
        ARRAY['cash', 'debit card', 'cash card']::paymentmethods[],
        555, 1010, 2020, 5050, 11,22, 55);

INSERT INTO vendingmachine (
        vendingmachineid, owner, address, machineActive, acceptedpaymentmethods, return5centpieces,
        return10centpieces, return20centpieces, return50centpieces,
        return1francpieces, return2francpieces, return5francpieces)
    VALUES (7, 'Selecta GmBH', 'Rapperswilstrasse 4', 'on',
        ARRAY['cash', 'debit card', 'cash card']::paymentmethods[],
        555, 1010, 2020, 5050, 11,22, 55);
