package by.vlad.task.godel_technologies_task.mapper;

import by.vlad.task.godel_technologies_task.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setDepartmentId(rs.getInt("department_id"));
        employee.setJobTitle(rs.getString("job_title"));
        employee.setGender(rs.getString("gender"));
        employee.setDateOfBirth(rs.getDate("date_of_birth"));
        return employee;
    }
}
