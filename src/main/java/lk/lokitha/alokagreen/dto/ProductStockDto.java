package lk.lokitha.alokagreen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ProductStockDto {

    private String stock_Id;
    private String product_Code;
    private int qty_On_Hand;
    private int qty;
    private String date;
    private String exp_Date;
    private String status;

}
