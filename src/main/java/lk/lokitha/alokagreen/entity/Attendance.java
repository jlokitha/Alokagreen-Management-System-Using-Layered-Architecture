package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attendance {
    private String attendanceId;
    private String employeeId;
    private LocalDate date;
    private LocalTime time;
}
