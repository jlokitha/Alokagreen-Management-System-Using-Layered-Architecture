package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpoiledProductReport {
    private String reportId;
    private String productCode;
    private int spoiledQty;
    private LocalDate date;
    private LocalTime time;
}
