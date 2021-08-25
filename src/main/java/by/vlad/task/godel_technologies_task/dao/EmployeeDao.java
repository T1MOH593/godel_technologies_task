package by.vlad.task.godel_technologies_task.dao;

import by.vlad.task.godel_technologies_task.entity.Employee;
import by.vlad.task.godel_technologies_task.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL = "insert into employee(first_name, last_name, department_id, job_title, gender, date_of_birth) " +
            "values(?, ?, ?, ?, ?, ?) ";

    private static final String UPDATE_SQL = "update employee set first_name = ?," +
                                                                "last_name = ?," +
                                                                "department_id = ?," +
                                                                "job_title = ?," +
                                                                "gender = ?," +
                                                                "date_of_birth = ?" +
                                                "where employee_id = ?";

    private KeyHolder keyHolder = new GeneratedKeyHolder();

    public List<Employee> findAll() {
        return jdbcTemplate.query(
                "select * from employee",
                new EmployeeMapper()
        );
    }

    public Optional<Employee> findById(Integer id) {
        return jdbcTemplate.query("select * from employee where employee_id = ?",
                                                                new EmployeeMapper(),
                                                                id).stream().findAny();
    }

    public Employee save(Employee employee) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"employee_id"});
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3, employee.getDepartmentId());
            ps.setString(4, employee.getJobTitle());
            ps.setString(5, employee.getGender());
            ps.setDate(6, employee.getDateOfBirth());

            return ps;
        }, keyHolder);

        employee.setEmployeeId(keyHolder.getKey().intValue());
        return employee;
    }

    public Employee update(Employee employee) {
        jdbcTemplate.update(UPDATE_SQL, employee.getFirstName(), employee.getLastName(), employee.getDepartmentId(),
                employee.getJobTitle(), employee.getGender(), employee.getDateOfBirth(), employee.getEmployeeId());
        return employee;
    }

    public void deleteById(Integer id) {
        jdbcTemplate.update("delete from employee where employee_id = ?", id);
    }
}
