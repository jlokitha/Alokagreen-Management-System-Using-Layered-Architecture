package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialStock {
    private String stockId;
    private String materialCode;
    private int qtyOnHand;
    private int qty;
    private double unitPrice;
    private String date;
    private String expDate;
    private String status;
}
