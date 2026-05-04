package com.cohert.controller;

import com.cohert.dto.EmployeeDTO;
import com.cohert.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/getmsg")
    public String getMessage(){
        return "getting data from get method";
    }

    @GetMapping(path="/empid")
    public EmployeeDTO getEmployeeId(@PathVariable Long empid){
        return employeeService.getEmployeeById(empid);
    }



    @GetMapping("/allemp")
    public List<EmployeeDTO> getAllEmployee( ){
        return employeeService.getAll();
    }

    //USing Response Entitities are commnented.
//    @GetMapping
//    public ResponseEntity<List<EmployeeDTO>> getAllEmployees
//            (@RequestParam (required = false,name = "inut age")Integer minage,
//             @RequestParam(required = false)String sortBy){
//        return ResponseEntity.ok(employeeService.getAll());
//    }



    @PostMapping
    public EmployeeDTO createUser(@RequestBody EmployeeDTO inputEmp){
        return employeeService.createEmployee(inputEmp);
    }
// Saving user using Response method
//    @PostMapping
//    public ResponseEntity<EmployeeDTO> createUsers(@RequestBody @Valid EmployeeDTO inputEmployees){
//        EmployeeDTO saveEmp = employeeService.createEmployee(inputEmployees);
//        return  new ResponseEntity<>(saveEmp, HttpStatus.CREATED);
//    }

    @PutMapping(path="/update/{empid}")
    public EmployeeDTO updateByid(@RequestBody EmployeeDTO employeeDTO,@PathVariable Long empid){
        return employeeService.updateByid(empid,employeeDTO);
    }

    // USing Resposne Entity
//    @PutMapping
//    public ResponseEntity<EmployeeDTO> updateEmpByid(@RequestBody EmployeeDTO employeeDTO,@PathVariable Long empid)
//    {
//        return ResponseEntity.ok(employeeService.updateByid(empid, employeeDTO));
//    }

    @DeleteMapping(path ="/delete/{empid}")
    public boolean deteleteEmp(@PathVariable  Long empid){
        return employeeService.deleteEmp(empid);
    }

//    @DeleteMapping
//    public ResponseEntity<Boolean> deleteEmpById(@PathVariable Long empid){
//        boolean deleted = employeeService.deleteEmp(empid);
//        if(deleted) return  ResponseEntity.ok(true);
//        return ResponseEntity.notFound().build();
//    }

    @PatchMapping(path="/update/{empid}")
    public EmployeeDTO updateEmployeePartially(@RequestBody Map<String, Object> updates,
                                      @PathVariable Long empid){
        return  employeeService.updateEmployeePartially(empid,updates);
    }

//    @PatchMapping
//    public ResponseEntity<EmployeeDTO> updateEmpPartially(@RequestBody Map<String,Object>updated,
//                                                          @PathVariable Long empid){
//        return ResponseEntity.ok(employeeService.updateEmployeePartially(empid,updated));
//    }
}
