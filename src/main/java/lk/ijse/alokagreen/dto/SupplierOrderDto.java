package lk.ijse.alokagreen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@AllArgsConstructor
@Data

public class SupplierOrderDto {

    private String supplier_Order_Id;
    private String supplier_Id;
    private double total_Amount;
    private String date;
    private String time;
    private ArrayList<String[]> items;

    public SupplierOrderDto() {
        items = new ArrayList<>();
    }
}
