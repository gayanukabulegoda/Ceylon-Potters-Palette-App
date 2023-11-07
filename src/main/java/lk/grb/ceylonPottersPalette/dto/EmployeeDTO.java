package lk.grb.ceylonPottersPalette.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class EmployeeDTO {

    private String employee_Id;
    private String first_Name;
    private String last_Name;
    private String nic;
    private String house_No;
    private String street;
    private String city;
    private String contact_No;
    private String email;
    private String role;
}
