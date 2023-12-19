package lk.lokitha.alokagreen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data

public class CustomerOrderDto {

    private String Customer_Order_Id;
    private String customer_Id;
    private double total_Amount;
    private String date;
    private String time;
    private Map<String, String> items;

    public CustomerOrderDto() {
        items = new HashMap<>();
    }
}
