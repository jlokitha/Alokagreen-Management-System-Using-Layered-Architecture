package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierOrder {
    private String supplierOrderId;
    private String supplierId;
    private double totalAmount;
    private LocalDate date;
    private LocalTime time;
}
