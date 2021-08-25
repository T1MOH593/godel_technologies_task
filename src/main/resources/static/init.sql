set search_path = "public";

create table if not exists employee (
    employee_id serial PRIMARY KEY ,
    first_name varchar(30) ,
    last_name varchar(30) ,
    department_id integer ,
    job_title varchar(50) ,
    gender varchar(30) ,
    date_of_birth date
);

INSERT INTO public.employee (first_name, last_name, department_id, job_title,  gender,  date_of_birth)
                    VALUES  ('Vlad',    'Andrievsky',     1,         'it',     'male',  '2000-05-08'),
                            ('Sveta',   'Svetikova',      2,         'hr',    'female', '2001-06-09'),
                            ('Ivan',    'Ivanov',         3,       'builder', 'female', '2002-07-08'),
                            ('Petr',    'Petrov',         1,      'developer', 'male',  '1999-05-08');