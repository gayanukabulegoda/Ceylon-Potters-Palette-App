package lk.grb.ceylonPottersPalette.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class ProductStockDto {

    private String product_Id;
    private String description;
    private int qty_On_Hand;
    private double unit_Price;
    private String category;
    private int qty;
}
