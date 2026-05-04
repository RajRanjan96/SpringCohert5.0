package com.cohert.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private  Long empid;
   private String name;
   private String mail;
   private Integer age;
   private LocalDate dateOfJoining;
   private Boolean isActive;


}
