package lk.grb.ceylonPottersPalette.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SupplierDto {

    private String supplier_Id;
    private String name;
    private String email;
    private String contact_No;
    private String time;
    private String date;
    private String user_Name;
}
