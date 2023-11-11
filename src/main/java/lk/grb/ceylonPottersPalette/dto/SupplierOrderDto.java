package lk.grb.ceylonPottersPalette.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SupplierOrderDto {

    private String supplier_Order_Id;
    private String supplier_Id;
    private String item_Id;
    private int item_Qty;
    private double unit_Price;
    private double total_Price;
    private String date;
    private String time;
}
