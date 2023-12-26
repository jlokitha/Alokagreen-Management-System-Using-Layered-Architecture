package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Supplier {
    private String supplierId;
    private String companyName;
    private String companyEmail;
    private String companyMobile;
    private String companyLocation;
    private Time time;
    private Date date;
}
