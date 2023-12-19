package lk.lokitha.alokagreen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ProductDto {

    private String product_Code;
    private String description;
    private double unit_Price;

}
