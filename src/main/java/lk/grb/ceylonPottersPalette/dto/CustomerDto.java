package lk.grb.ceylonPottersPalette.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class CustomerDto {

    private String customer_Id;
    private String name;
    private String contact_No;
    private String email;
    private String date;
    private String time;
    private String user_Name;
}
