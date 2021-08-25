# godel_technologies_task

to run this service run PostgreSQL server, create database employeedb, run init.sql, set username and password in application.properties and finally run method main in class GodelTechnologiesTaskApplication

GET  /employees returns all employees
GET  /employees/{id} returns employee with this id
POST /employees takes employee with empty employeeId and saves it, returns saved employee. All fields except employeeId must be filled 
PUT  /employees takes employee with non-empty employeeId and updates it, returns updated employee. All fields must be filled
DELETE /employees/{id} removes employee with this id.
