package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Supplier {
    private String supplierId;
    private String companyName;
    private String companyEmail;
    private String companyMobile;
    private String companyLocation;
    private LocalTime time;
    private LocalDate date;
}
