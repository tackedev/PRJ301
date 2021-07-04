-- CREATE DATABASE MobileShop
-- GO

-- USE MobileShop
-- GO

-- CREATE TABLE tbl_Mobile (
--     mobileId            VARCHAR(10)     NOT NULL,
--     description         VARCHAR(250)   NOT NULL,
--     price               FLOAT,
--     mobileName          VARCHAR(20)     NOT NULL,
--     yearOfProduction    INT,
--     quantity            INT,
--     notSale             BIT,
--     CONSTRAINT PK_tbl_Mobile PRIMARY KEY (mobileId),
-- )
-- GO

-- CREATE TABLE tbl_User (
--     userId              VARCHAR(20)    NOT NULL,
--     password            INT             NOT NULL,
--     fullName            VARCHAR(50)    NOT NULL,
--     role                INT,
--     CONSTRAINT PK_tbl_User PRIMARY KEY (userId),
-- )
-- GO

INSERT INTO tbl_Mobile
VALUES ('Mobile001', 'Mobile 1', 1000000, 'Mobile1', 2020, 10, 0);
GO

INSERT INTO tbl_Mobile
VALUES ('Mobile002', 'Mobile 2', 2000000, 'Mobile2', 2020, 10, 0);
GO

INSERT INTO tbl_Mobile
VALUES ('Mobile003', 'Mobile 3', 3000000, 'Mobile3', 2020, 10, 0);
GO

INSERT INTO tbl_Mobile
VALUES ('Mobile004', 'Mobile 4', 4000000, 'Mobile4', 2020, 10, 0);
GO

INSERT INTO tbl_Mobile
VALUES ('Mobile005', 'Mobile 5', 5000000, 'Mobile5', 2020, 10, 0);
GO

INSERT INTO tbl_Mobile
VALUES ('Mobile006', 'Mobile 6', 6000000, 'Mobile6', 2020, 10, 0);
GO

INSERT INTO tbl_Mobile
VALUES ('Mobile007', 'Mobile 7', 7000000, 'Mobile7', 2020, 10, 0);
GO

INSERT INTO tbl_User 
VALUES ('user000', 123, 'Nguyen Van 0', 1)
GO

INSERT INTO tbl_User 
VALUES ('user001', 123, 'Nguyen Van 1', 2)
GO

INSERT INTO tbl_User 
VALUES ('user002', 123, 'Nguyen Van 2', 2)
GO

INSERT INTO tbl_User 
VALUES ('user003', 123, 'Nguyen Van 3', 2)
GO

INSERT INTO tbl_User 
VALUES ('user004', 123, 'Nguyen Van 4', 0)
GO

INSERT INTO tbl_User 
VALUES ('user005', 123, 'Nguyen Van 5', 0)
GO

INSERT INTO tbl_User 
VALUES ('user006', 123, 'Nguyen Van 6', 0)
GO

INSERT INTO tbl_User 
VALUES ('user007', 123, 'Nguyen Van 7', 0)
GO

INSERT INTO tbl_User 
VALUES ('user008', 123, 'Nguyen Van 8', 0)
GO

INSERT INTO tbl_User 
VALUES ('user009', 123, 'Nguyen Van 9', 0)
GO

INSERT INTO tbl_User 
VALUES ('user010', 123, 'Nguyen Van 10', 0)
GO