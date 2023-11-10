package lk.grb.ceylonPottersPalette.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class ItemStockDto {

    private String item_Id;
    private String description;
    private double unit_Price;
    private int qty_On_Hand;
}
