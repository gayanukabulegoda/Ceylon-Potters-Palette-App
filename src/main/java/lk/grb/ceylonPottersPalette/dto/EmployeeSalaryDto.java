package lk.grb.ceylonPottersPalette.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeSalaryDto {

    private String employee_Id;
    private int worked_Day_Count;
    private double salary;
    private double bonus;
    private double total_Payment;
    private String date;
    private String time;
}
