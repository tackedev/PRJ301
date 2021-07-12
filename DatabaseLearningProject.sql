CREATE DATABASE WelcomeServlet
GO

USE WelcomeServlet
GO

CREATE TABLE Registration(
    username        VARCHAR(20)     NOT NULL,
    password        VARCHAR(30)     NOT NULL,
    lastName        NVARCHAR(100)   NOT NULL,
    isAdmin         BIT             NOT NULL,
    CONSTRAINT PK_Registration PRIMARY KEY (username),
)
GO

CREATE TABLE Product (
    sku             VARCHAR(30)     NOT NULL,
    name            NVARCHAR(50)    NOT NULL,
    price           MONEY           NOT NULL,
    description     NVARCHAR(200)   NOT NULL,
    quantity        INT             NOT NULL,
    CONSTRAINT PK_Product PRIMARY KEY (sku),
)
GO

CREATE TABLE Orders (
    id              INT             IDENTITY,
    total           MONEY           NOT NULL,
    CONSTRAINT PK_Orders PRIMARY KEY (id),
)
GO

CREATE TABLE OrderDetail (
    id              INT             IDENTITY,
    ordersId        INT             NOT NULL,
    sku             VARCHAR(30)     NOT NULL,
    price           MONEY           NOT NULL,
    quantity        INT             NOT NULL,
    total           MONEY           NOT NULL,
    CONSTRAINT PK_OrderDetail PRIMARY KEY (id),
    CONSTRAINT FK_OrderDetail_Product FOREIGN KEY (sku) 
                                      REFERENCES Product(sku),
    CONSTRAINT FK_OrderDetail_Orders FOREIGN KEY (ordersId)
                                     REFERENCES Orders(id)
)
GO

CREATE TRIGGER TR_OrderDetail_Insert 
ON OrderDetail AFTER INSERT
AS 
BEGIN
    DECLARE @ordersId INT;
    DECLARE @sku VARCHAR(30);
    DECLARE @orderDetailQuantity INT;
    DECLARE @orderDetailTotal MONEY;

    SELECT @ordersId = ordersId, @sku = sku, @orderDetailQuantity = quantity, @orderDetailTotal = total
    FROM INSERTED;

    IF ((SELECT quantity FROM Product WHERE sku = @sku) >= @orderDetailQuantity)
    BEGIN
        UPDATE Product 
        SET quantity = quantity - @orderDetailQuantity
        WHERE sku = @sku;

        UPDATE Orders 
        SET total = total + @orderDetailTotal
        WHERE id = @ordersId;
    END
    ELSE
        ROLLBACK TRAN;
END
GO

INSERT INTO Registration (username, password, lastName, isAdmin)
VALUES 
('admin', 'admin123', 'admin', 1),
('tackedev', 'admin123', 'tackedev', 1),
('se150864', 'admin123', 'se150864', 0),
('se150865', 'admin123', 'se150865', 0),
('se150866', 'admin123', 'se150866', 0),
('se150868', 'admin123', 'se150868', 0),
('se150867', 'admin123', 'se150867', 0),
('se150870', 'admin123', 'se150870', 0),
('se150873', 'admin123', 'se150873', 0)
GO

INSERT INTO Product (sku, name, price, description, quantity)
VALUES 
('BOOK001', 'Java', 80000, 'Java fundamentel book', 10),
('BOOK002', 'OOP', 70000, 'OOP fundamentel book', 10),
('BOOK003', 'Servlet', 120000, 'Servlet fundamentel book', 10),
('BOOK004', 'JSP', 200000, 'JSP fundamentel book', 10),
('BOOK005', 'Netbeans', 50000, 'Netbeans fundamentel book', 10),
('BOOK006', 'JavaBeans', 125000, 'JavaBeans fundamentel book', 10),
('BOOK007', 'Tomcat', 150000, 'Tomcat fundamentel book', 10),
('BOOK008', 'JSTL', 75000, 'JSTL fundamentel book', 10)
GO

--Test trigger
-- INSERT INTO OrderDetail (sku, price, quantity, total)
-- VALUES ('BOOK001', 80000, 1, 80000)
-- GO

-- Insert Into Orders (customer) 
-- Output Inserted.id
-- Values ('Admin')
-- GO

-- Insert Into OrderDetail (ordersId, sku, price, quantity, total) 
-- Values (1, 'BOOK001', 80000, 1, 80000)
-- GO