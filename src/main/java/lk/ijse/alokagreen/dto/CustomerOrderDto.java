package lk.ijse.alokagreen.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

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

    public CustomerOrderDto(String customer_Order_Id, String customer_Id, double total_Amount, String date, String time) {
        Customer_Order_Id = customer_Order_Id;
        this.customer_Id = customer_Id;
        this.total_Amount = total_Amount;
        this.date = date;
        this.time = time;
    }
}
