package by.vlad.task.godel_technologies_task.service;

import by.vlad.task.godel_technologies_task.dao.EmployeeDao;
import by.vlad.task.godel_technologies_task.dto.EmployeeDto;
import by.vlad.task.godel_technologies_task.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ModelMapper modelMapper;

    public List<EmployeeDto> getAllEmployees() {
        return employeeDao.findAll().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDto> getEmployeeById(Integer id) {
        return employeeDao.findById(id)
                .map(employee -> modelMapper.map(employee, EmployeeDto.class));
    }

    public EmployeeDto saveEmployee(Employee employee) {
        if (employee.getEmployeeId() == null) {
            return modelMapper.map(employeeDao.save(employee), EmployeeDto.class);
        } else if (employeeDao.findById(employee.getEmployeeId()).isPresent()) {
            return modelMapper.map(employeeDao.update(employee), EmployeeDto.class);
        } else {
            return new EmployeeDto();
        }
    }

    public void deleteEmployee(Integer id) {
        employeeDao.deleteById(id);
    }
}
