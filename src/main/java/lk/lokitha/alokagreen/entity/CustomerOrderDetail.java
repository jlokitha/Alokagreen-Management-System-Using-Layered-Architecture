package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerOrderDetail {
    private String CustomerOrderId;
    private String stockId;
    private int qty;
}
