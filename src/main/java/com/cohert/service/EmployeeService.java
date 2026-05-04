package com.cohert.service;

import com.cohert.dto.EmployeeDTO;
import com.cohert.entity.Employee;
import com.cohert.repo.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private  final EmployeeRepo employeeRepo;
    private final ModelMapper modelMapper;

    EmployeeService(EmployeeRepo employeeRepo, ModelMapper modelMapper){
        this.employeeRepo =employeeRepo;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long empid){

        ModelMapper modelMapper=new ModelMapper();
        return employeeRepo.findById(empid)
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAll() {
       List<Employee> employees = employeeRepo.findAll();
      return employees
               .stream()
               .map(employee -> modelMapper.map(employee,EmployeeDTO.class))
               .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO inputEmp) {
        Employee toSaveEmp = modelMapper.map(inputEmp,Employee.class);
       Employee savedmployee = employeeRepo.save(toSaveEmp);
       return modelMapper.map(savedmployee,EmployeeDTO.class);
    }

    public EmployeeDTO updateByid(Long empid, EmployeeDTO employeeDTO) {
        Employee employee= modelMapper.map(employeeDTO,Employee.class);
        employee.setEmpid(empid);
        Employee saveemployee = employeeRepo.save(employee);
        return modelMapper.map(saveemployee,EmployeeDTO.class);
    }

    public boolean  deleteEmp(Long empid){
        boolean exists = employeeRepo.existsById(empid);
        if(!exists) return false;
        employeeRepo.deleteById(empid);
        return true;
    }


    public EmployeeDTO updateEmployeePartially(Long empid, Map<String, Object> updates)
    {
            boolean exists = employeeRepo.existsById(empid);
            if(!exists) return  null;
            Employee employee = employeeRepo.findById(empid).get();
            updates.forEach((field,value) ->
        {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(Employee.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employee, value);
        });
            return modelMapper.map(employeeRepo.save (employee),EmployeeDTO.class);
    }
}
