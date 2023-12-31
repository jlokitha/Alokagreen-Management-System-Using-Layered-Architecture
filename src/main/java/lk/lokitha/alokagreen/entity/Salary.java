package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Salary {
    private String salaryId;
    private String employeeId;
    private double totalSalary;
    private int workedDayCount;
    private double bonus;
    private String date;
    private String time;
}
