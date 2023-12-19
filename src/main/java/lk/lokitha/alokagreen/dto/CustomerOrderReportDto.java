package lk.lokitha.alokagreen.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class CustomerOrderReportDto {

    private String OrderedDate;
    private String OrderId;
    private String OrderedTime;
    private String CustomerID;
    private String Name;
    private String Mobile;
    private String Email;
    private String total;

}
