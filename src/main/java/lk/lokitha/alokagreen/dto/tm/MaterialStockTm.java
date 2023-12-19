package lk.lokitha.alokagreen.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class MaterialStockTm {

    private String stock_Id;
    private String desc;
    private String qty;
    private String exp_Date;
    private String status;

}
