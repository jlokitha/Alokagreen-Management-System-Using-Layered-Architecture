package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierOrder {
    private String supplierOrderId;
    private String supplierId;
    private double totalAmount;
    private Date date;
    private Time time;
}
