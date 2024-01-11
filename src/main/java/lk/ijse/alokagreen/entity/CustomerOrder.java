package lk.ijse.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerOrder {
    private String customerOrderId;
    private String customerId;
    private double totalAmount;
    private String date;
    private String time;
}
