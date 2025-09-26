CREATE DATABASE hrms;
USE hrms;

CREATE TABLE departments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department_id BIGINT,
    salary DECIMAL(10,2) NOT NULL,
    join_date DATE NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE payroll (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT NOT NULL,
    month VARCHAR(7) NOT NULL,
    basic_salary DECIMAL(10,2) NOT NULL,
    overtime DECIMAL(10,2) DEFAULT 0,
    total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

CREATE TABLE attendance (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT NOT NULL,
    date DATE NOT NULL,
    check_in TIME NOT NULL,
    check_out TIME,
    hours_worked DECIMAL(4,2) DEFAULT 0,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

INSERT INTO departments (name, description) VALUES 
('IT', 'Information Technology'),
('HR', 'Human Resources'),
('Finance', 'Finance Department');