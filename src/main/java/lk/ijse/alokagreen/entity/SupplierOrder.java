package lk.ijse.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierOrder {
    private String supplierOrderId;
    private String supplierId;
    private double totalAmount;
    private String date;
    private String time;
}
