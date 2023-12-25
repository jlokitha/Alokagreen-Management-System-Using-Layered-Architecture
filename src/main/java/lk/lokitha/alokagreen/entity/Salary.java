package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Salary {
    private String salaryId;
    private String employeeId;
    private double totalSalary;
    private int workedDayCount;
    private double bonus;
    private Date date;
    private Time time;
}
