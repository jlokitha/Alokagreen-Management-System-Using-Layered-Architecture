package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductStock {
    private String stockId;
    private String productCode;
    private int qtyOnHand;
    private int qty;
    private LocalDate date;
    private LocalDate expDate;
    private String status;
}
