-- CREATE DATABASE WelcomeServlet
-- GO

USE WelcomeServlet
GO

-- CREATE TABLE Registration(
--     username        VARCHAR(20)     NOT NULL,
--     password        VARCHAR(30)     NOT NULL,
--     lastName        NVARCHAR(100)   NOT NULL,
--     isAdmin         BIT             NOT NULL,
--     CONSTRAINT PK_Registration PRIMARY KEY (username),
-- )
-- GO

INSERT INTO Registration (username, password, lastName, isAdmin)
VALUES ('admin', 'admin123', 'admin', 1)
GO

INSERT INTO Registration (username, password, lastName, isAdmin)
VALUES ('tackedev', 'admin123', 'tackedev', 1)
GO

INSERT INTO Registration (username, password, lastName, isAdmin)
VALUES ('se150864', 'admin123', 'se150864', 0)
GO

INSERT INTO Registration (username, password, lastName, isAdmin)
VALUES ('se150865', 'admin123', 'se150865', 0)
GO

INSERT INTO Registration (username, password, lastName, isAdmin)
VALUES ('se150866', 'admin123', 'se150866', 0)
GO

INSERT INTO Registration (username, password, lastName, isAdmin)
VALUES ('se150868', 'admin123', 'se150868', 0)
GO

INSERT INTO Registration (username, password, lastName, isAdmin)
VALUES ('se150867', 'admin123', 'se150867', 0)
GO

INSERT INTO Registration (username, password, lastName, isAdmin)
VALUES ('se150870', 'admin123', 'se150870', 0)
GO

INSERT INTO Registration (username, password, lastName, isAdmin)
VALUES ('se150873', 'admin123', 'se150873', 0)
GO

-- CREATE TABLE Product (
--     sku             VARCHAR(30)     NOT NULL,
--     name            NVARCHAR(50)    NOT NULL,
--     price           MONEY           NOT NULL,
--     description     NVARCHAR(200)   NOT NULL,
--     quantity        INT             NOT NULL,
--     CONSTRAINT PK_Product PRIMARY KEY (sku),
-- )
-- GO

-- INSERT INTO Product (sku, name, price, description, quantity)
-- VALUES ('BOOK001', 'Java', 100000, 'Java fundamentel book', 10)
-- GO

-- INSERT INTO Product (sku, name, price, description, quantity)
-- VALUES ('BOOK002', 'OOP', 100000, 'OOP fundamentel book', 10)
-- GO

-- INSERT INTO Product (sku, name, price, description, quantity)
-- VALUES ('BOOK003', 'Servlet', 100000, 'Servlet fundamentel book', 10)
-- GO

-- INSERT INTO Product (sku, name, price, description, quantity)
-- VALUES ('BOOK004', 'JSP', 100000, 'JSP fundamentel book', 10)
-- GO

-- INSERT INTO Product (sku, name, price, description, quantity)
-- VALUES ('BOOK005', 'Netbeans', 100000, 'Netbeans fundamentel book', 10)
-- GO

-- INSERT INTO Product (sku, name, price, description, quantity)
-- VALUES ('BOOK006', 'JavaBeans', 100000, 'JavaBeans fundamentel book', 10)
-- GO

-- INSERT INTO Product (sku, name, price, description, quantity)
-- VALUES ('BOOK007', 'Tomcat', 100000, 'Tomcat fundamentel book', 10)
-- GO

-- INSERT INTO Product (sku, name, price, description, quantity)
-- VALUES ('BOOK008', 'JSTL', 100000, 'JSTL fundamentel book', 10)
-- GO

-- CREATE TABLE OrderDetail (
--     id              int             IDENTITY,
--     sku             VARCHAR(30)     NOT NULL,
--     price           MONEY           NOT NULL,
--     quantity        INT             NOT NULL,
--     total           MONEY           NOT NULL,
--     CONSTRAINT PK_OrderDetail PRIMARY KEY (id),
--     CONSTRAINT FK_OrderDetail_Product FOREIGN KEY (sku) 
--                                       REFERENCES Product(sku)
-- )
-- GO

-- CREATE TRIGGER TR_OrderDetail_Insert 
-- ON OrderDetail AFTER INSERT
-- AS 
-- BEGIN
--     DECLARE @sku VARCHAR(30);
--     DECLARE @orderQuantity INT;
--     SELECT @sku = sku, @orderQuantity = quantity 
--     FROM inserted;

--     UPDATE Product 
--     SET quantity = quantity - @orderQuantity
--     WHERE sku = @sku;
-- END
-- GO
