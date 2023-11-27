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

    public boolean update(EmployeeSalaryDto employeeSalaryDto) throws SQLException {

        return SQLUtil.execute("UPDATE salary SET " +
                        "employee_Id=?," +
                        "worked_Day_Count=?," +
                        "salary=?," +
                        "bonus=?," +
                        "total_Payment=?" +
                        "WHERE salary_Id=?",
                employeeSalaryDto.getEmployee_Id(),
                employeeSalaryDto.getWorked_Day_Count(),
                employeeSalaryDto.getSalary(),
                employeeSalaryDto.getBonus(),
                employeeSalaryDto.getTotal_Payment(),
                employeeSalaryDto.getSalary_Id()
        );
    }

    public EmployeeSalaryDto getData(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM salary WHERE salary_Id=?", id);

        EmployeeSalaryDto employeeSalaryDto = new EmployeeSalaryDto();

        if(resultSet.next()){
            employeeSalaryDto.setSalary_Id(resultSet.getString(1));
            employeeSalaryDto.setEmployee_Id(resultSet.getString(2));
            employeeSalaryDto.setWorked_Day_Count(resultSet.getInt(3));
            employeeSalaryDto.setSalary(resultSet.getDouble(4));
            employeeSalaryDto.setBonus(resultSet.getDouble(5));
            employeeSalaryDto.setTotal_Payment(resultSet.getDouble(6));
            employeeSalaryDto.setDate(resultSet.getString(7));
            employeeSalaryDto.setTime(resultSet.getString(8));
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

    public ArrayList<String> getSelectedAllSalaryId(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT salary_Id FROM salary WHERE employee_Id = ? ORDER BY salary_Id desc", id);
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public ArrayList<String> getAllEmployeeId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT employee_Id FROM salary");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public double getSalaryTotal() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT SUM(total_Payment) FROM salary");

        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0.0;
    }

    public String getSalaryId(String employeeId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT salary_Id FROM salary WHERE employee_Id=?", employeeId);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
