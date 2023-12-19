package lk.lokitha.alokagreen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeDto {
    private String employee_Id;
    private String first_Name;
    private String last_Name;
    private String nic;
    private String house_No;
    private String street;
    private String city;
    private String mobile;
    private String email;
    private String role;
    private String date;
}
