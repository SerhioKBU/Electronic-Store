# SET FOREIGN_KEY_CHECKS=0;
# SET FOREIGN_KEY_CHECKS=1;

Insert Into account(login, password) value ('Serhio1982', 'qwerty');
Insert Into account(login, password) value ('Batman', '1234');

Insert Into users(accountId, UserName, Email) value (10, 'Sergey', 'serhii_2021@meta.ua');
Insert Into users(accountId, UserName, Email) value ((SELECT `id` FROM `account` WHERE `login` = 'Batman'), 'Olya', 'ola@meta.ua');

Insert Into category(name) value ('Cells phone');
Insert Into category(name) value ('TV');
Insert Into category(name) value ('Laptops');
Insert Into category(name) value ('Toys');

Insert Into product(CategoryId, name, cost) value (2, 'Samsung', 750);
Insert Into product(CategoryId, name, cost) value (2, 'Philips', 480);

Insert Into basket(UserId, ProductId) value (1, 1);
Insert Into basket(UserId, ProductId) value (1, 2);