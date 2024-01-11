package lk.ijse.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpoiledProductReport {
    private String reportId;
    private String productCode;
    private int spoiledQty;
    private String date;
    private String time;
}
