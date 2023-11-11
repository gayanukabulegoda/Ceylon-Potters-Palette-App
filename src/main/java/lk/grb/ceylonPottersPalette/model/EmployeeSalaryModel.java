package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.dto.EmployeeSalaryDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.SQLException;

public class EmployeeSalaryModel {

    public boolean save(EmployeeSalaryDto employeeSalaryDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO salary VALUES (?,?,?,?,?,?)",
                employeeSalaryDto.getEmployee_Id(),
                employeeSalaryDto.getWorked_Day_Count(),
                employeeSalaryDto.getSalary(),
                employeeSalaryDto.getBonus(),
                employeeSalaryDto.getTotal_Payment(),
                employeeSalaryDto.getDate(),
                employeeSalaryDto.getTime());
    }

    public String workedDayCount(String date, String id) throws SQLException {
        return SQLUtil.execute("SELECT count(employee_Id) FROM attendance WHERE employee_Id=? AND date LIKE ", id);
    }
}
