package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerOrder {
    private String customerOrderId;
    private String customerId;
    private double totalAmount;
    private Date date;
    private Time time;
}
