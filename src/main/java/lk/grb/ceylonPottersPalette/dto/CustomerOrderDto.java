package lk.grb.ceylonPottersPalette.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerOrderDto {

   private String customer_Order_Id;
   private String customer_Id;
   private String product_Id;
   private int product_Qty;
   private double total_Price;
   private String date;
   private String time;

}
