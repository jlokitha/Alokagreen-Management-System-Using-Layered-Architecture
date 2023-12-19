package lk.lokitha.alokagreen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.ref.PhantomReference;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SpoiledReportDto {

    private String report_Id;
    private String product_Code;
    private int spoiled_Qty;
    private String date;
    private String time;

}
