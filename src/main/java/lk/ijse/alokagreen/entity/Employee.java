package lk.ijse.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String date;
}
