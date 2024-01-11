package lk.ijse.alokagreen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeSalaryDto {

    private String salary_Id;
    private String employee_Id;
    private double total_Salary;
    private int worked_Days;
    private double bonus;
    private String date;
    private String time;

}
