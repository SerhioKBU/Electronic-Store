 SET FOREIGN_KEY_CHECKS=0;
 SET FOREIGN_KEY_CHECKS=1;

Insert Into account(login, password) value ('Serhio1982', 'qwerty');
# Insert Into account(login, password) value ('Batman', '1234');

Insert Into user(accountId, UserName, Email) value (1, 'Sergey', 'serhii_2021@meta.ua');
Insert Into user(accountId, UserName, Email) value ((SELECT `id` FROM `account` WHERE `login` = 'Batman'), 'Olya', 'ola@meta.ua');

Insert Into category(categoryName) value ('iPhone');
Insert Into category(categoryName) value ('TV');
Insert Into category(categoryName) value ('Laptop');
Insert Into category(categoryName) value ('PS4');

# Insert Into product(CategoryId, name, cost) value (2, 'Samsung', 750);
# Insert Into product(CategoryId, name, cost) value (2, 'Philips', 480);

Insert Into role(roleName) value ('admin');
Insert Into role(roleName) value ('user');

Insert Into basket(UserId, CategoryId) value (1, 1);
Insert Into basket(UserId, CategoryId) value (1, 2);