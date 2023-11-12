package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.dto.EmployeeSalaryDto;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeSalaryModel {

    public boolean save(EmployeeSalaryDto employeeSalaryDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO salary VALUES (?,?,?,?,?,?,?,?)",
                employeeSalaryDto.getSalary_Id(),
                employeeSalaryDto.getEmployee_Id(),
                employeeSalaryDto.getWorked_Day_Count(),
                employeeSalaryDto.getSalary(),
                employeeSalaryDto.getBonus(),
                employeeSalaryDto.getTotal_Payment(),
                employeeSalaryDto.getDate(),
                employeeSalaryDto.getTime());
    }

    public EmployeeSalaryDto getData(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM salary WHERE salary_Id=?", id);

        EmployeeSalaryDto employeeSalaryDto = new EmployeeSalaryDto();

        if(resultSet.next()){
            employeeSalaryDto.setEmployee_Id(resultSet.getString(2));
            employeeSalaryDto.setTotal_Payment(resultSet.getDouble(6));
            employeeSalaryDto.setDate(resultSet.getString(7));
        }
        return employeeSalaryDto;
    }

    public ArrayList<String> getAllSalaryId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT salary_Id FROM salary ORDER BY salary_Id desc");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
