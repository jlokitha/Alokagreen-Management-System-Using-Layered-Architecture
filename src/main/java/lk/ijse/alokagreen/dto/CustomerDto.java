package lk.ijse.alokagreen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerDto {
    private String customer_Id;
    private String name;
    private String mobile;
    private String email;
    private String address;
    private String date;
    private String time;
}
