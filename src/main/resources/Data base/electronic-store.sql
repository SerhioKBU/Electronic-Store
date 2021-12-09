drop table IF EXISTS product;
drop table IF EXISTS category;
drop table IF EXISTS users;
drop table IF EXISTS basket;

create table Category(
                         Id int primary key AUTO_INCREMENT,
                         Name varchar(50)
);

create table Product(
                        Id int primary key AUTO_INCREMENT,
                        CategoryId int,
                        Name varchar(50),
                        Description varchar(150),
                        Cost int,
                        FOREIGN KEY (CategoryId)  REFERENCES Category(Id)
);

create table Users (
                      Id int primary key AUTO_INCREMENT,
                      accountId int,
                      UserName varchar(50),
                      Email varchar(50),
                      FOREIGN KEY (accountId)  REFERENCES users(Id)
);

create table Basket(
                       Id int primary key AUTO_INCREMENT,
                       UserId int,
                       ProductId int,
                       FOREIGN KEY (UserId)  REFERENCES users(Id),
                       FOREIGN KEY (ProductId)  REFERENCES Product(Id)
);

create table transaction(
                            Id int primary key AUTO_INCREMENT,
                            UserId int,
                            summa int,
                            FOREIGN KEY (UserId)  REFERENCES users(Id)
);

create table account(
                            Id int primary key AUTO_INCREMENT,
                            login varchar(50),
                            password varchar(50),
                            create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            roleId int,
                            FOREIGN KEY (roleId) REFERENCES role(Id)


);

create table role(
                            Id int primary key AUTO_INCREMENT,
                            name ENUM('admin', 'user') NOT NULL
)
