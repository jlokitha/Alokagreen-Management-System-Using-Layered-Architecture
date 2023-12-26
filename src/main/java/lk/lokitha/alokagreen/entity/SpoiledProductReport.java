package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpoiledProductReport {
    private String reportId;
    private String productCode;
    private int spoiledQty;
    private Date date;
    private Time time;
}
