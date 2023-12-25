package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    private String customerId;
    private String name;
    private String mobile;
    private String email;
    private String address;
    private LocalDate date;
    private LocalTime time;
}
