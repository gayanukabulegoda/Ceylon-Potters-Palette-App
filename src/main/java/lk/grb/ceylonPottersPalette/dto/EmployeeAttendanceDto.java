package lk.grb.ceylonPottersPalette.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeAttendanceDto {

    private String employee_Id;
    private String date;
    private String time;
}
