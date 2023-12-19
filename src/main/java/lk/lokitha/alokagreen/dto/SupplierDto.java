package lk.lokitha.alokagreen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SupplierDto {

    private String supplier_Id;
    private String company_Name;
    private String company_Email;
    private String company_Mobile;
    private String company_Location;
    private String date;
    private String time;

}
