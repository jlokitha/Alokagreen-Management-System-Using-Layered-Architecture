package lk.lokitha.alokagreen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Supplier {
    private String supplierId;
    private String companyName;
    private String companyEmail;
    private String companyMobile;
    private String companyLocation;
    private String  time;
    private String date;
}
