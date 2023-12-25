package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    private String customerId;
    private String name;
    private String mobile;
    private String email;
    private String address;
    private Date date;
    private Time time;
}
