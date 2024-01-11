package lk.ijse.alokagreen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class MaterialStockDto {

    private String stock_Id;
    private String material_Code;
    private int qty_On_Hand;
    private int qty;
    private double unit_Price;
    private String date;
    private String exp_Date;
    private String status;

}
