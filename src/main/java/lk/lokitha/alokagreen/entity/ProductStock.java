package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductStock {
    private String stockId;
    private String productCode;
    private int qtyOnHand;
    private int qty;
    private String date;
    private String expDate;
    private String status;
}
