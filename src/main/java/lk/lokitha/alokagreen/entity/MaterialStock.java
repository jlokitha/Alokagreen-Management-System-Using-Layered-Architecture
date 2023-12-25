package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaterialStock {
    private String stockId;
    private String materialCode;
    private int qtyOnHand;
    private int qty;
    private double unitPrice;
    private Date date;
    private Date expDate;
    private String status;
}
