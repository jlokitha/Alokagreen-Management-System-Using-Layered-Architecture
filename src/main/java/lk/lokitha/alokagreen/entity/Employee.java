package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String nic;
    private String houseNo;
    private String street;
    private String city;
    private String mobile;
    private String email;
    private String role;
    private LocalDate date;
}
