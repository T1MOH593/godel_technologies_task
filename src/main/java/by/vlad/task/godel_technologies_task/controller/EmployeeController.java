package by.vlad.task.godel_technologies_task.controller;

import by.vlad.task.godel_technologies_task.dto.EmployeeDto;
import by.vlad.task.godel_technologies_task.entity.Employee;
import by.vlad.task.godel_technologies_task.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity getAllEmployees() {
        return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployee(@PathVariable Integer id) {
        Optional<EmployeeDto> maybeEmployee = employeeService.getEmployeeById(id);
        return maybeEmployee.map(employee -> new ResponseEntity(employee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("There is no employee with this id", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/employees")
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        EmployeeDto employeeDto = employeeService.saveEmployee(employee);
        return new ResponseEntity(employeeDto, HttpStatus.OK);
    }

    @PutMapping("/employees")
    public ResponseEntity updateEmployee(@RequestBody Employee employee) {
        if (employee.getEmployeeId() == null) {
            return new ResponseEntity("There is no employee with this id", HttpStatus.NOT_FOUND);
        }
        EmployeeDto employeeDto = employeeService.saveEmployee(employee);
        if (employeeDto.getEmployeeId() == null) {
            return new ResponseEntity("There is no employee with this id", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(employeeDto, HttpStatus.OK);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Integer id) {
        if (!employeeService.getEmployeeById(id).isPresent()) {
            return new ResponseEntity("There is no employee with this id", HttpStatus.NOT_FOUND);
        } else {
            employeeService.deleteEmployee(id);
            return new ResponseEntity("employee with this id was deleted", HttpStatus.OK);
        }
    }
}
