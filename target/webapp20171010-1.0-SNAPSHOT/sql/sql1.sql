INSERT INTO roles (RoleId) VALUES (1);
INSERT INTO roles (RoleId) VALUES (2);
INSERT INTO roles (RoleId) VALUES (3);



INSERT INTO users (Name, Password, Email, UserRoleId)
VALUES ('oleksandr', 'password', 'oleksandr@gmail.com', 1);

INSERT INTO users (Name, Password, Email, UserRoleId)
VALUES ('mikola', 'password', 'mikola@gmail.com', 2);

INSERT INTO users (Name, Password, Email, UserRoleId)
VALUES ('ivan', 'password', 'ivan@gmail.com', 3);



SELECT *
FROM users;