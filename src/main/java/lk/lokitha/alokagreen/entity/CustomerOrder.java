package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerOrder {
    private String customerOrderId;
    private String customerId;
    private double totalAmount;
    private LocalDate date;
    private LocalTime time;
}
