package lk.lokitha.alokagreen.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SupplierOrderTm {

    private String supplier_Order_Id;
    private String supplier_Id;
    private double total_Amount;
    private String date;
    private String time;

}
