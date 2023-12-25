package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Salary {
    private String salaryId;
    private String employeeId;
    private double totalSalary;
    private int workedDayCount;
    private double bonus;
    private LocalDate date;
    private LocalTime time;
}
