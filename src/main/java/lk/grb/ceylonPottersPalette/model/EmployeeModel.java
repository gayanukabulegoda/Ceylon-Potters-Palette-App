package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.dto.EmployeeDTO;
import lk.grb.ceylonPottersPalette.utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {

    public boolean save(EmployeeDTO employeeDTO) throws SQLException {
        return SQLUtill.execute("INSERT INTO employee VALUES (?,?,?,?,?,?,?,?,?,?)",
                employeeDTO.getEmployee_Id(),
                employeeDTO.getFirst_Name(),
                employeeDTO.getLast_Name(),
                employeeDTO.getNic(),
                employeeDTO.getHouse_No(),
                employeeDTO.getStreet(),
                employeeDTO.getCity(),
                employeeDTO.getContact_No(),
                employeeDTO.getEmail(),
                employeeDTO.getRole());
    }

    public EmployeeDTO getData(String id) throws SQLException {
        ResultSet set = SQLUtill.execute("SELECT * FROM employee WHERE employee_Id=?", id);

        EmployeeDTO employeeDTO = new EmployeeDTO();

        if (set.next()) {
            employeeDTO.setEmployee_Id(set.getString(1));
            employeeDTO.setFirst_Name(set.getString(2));
            employeeDTO.setLast_Name(set.getString(3));
            employeeDTO.setNic(set.getString(4));
            employeeDTO.setHouse_No(set.getString(5));
            employeeDTO.setStreet(set.getString(6));
            employeeDTO.setCity(set.getString(7));
            employeeDTO.setContact_No(set.getString(8));
            employeeDTO.setEmail(set.getString(9));
            employeeDTO.setRole(set.getString(10));
        }
        return employeeDTO;
    }

    public boolean update(EmployeeDTO employeeDTO) throws SQLException {
        return SQLUtill.execute("UPDATE employee SET " +
                        "first_Name=?," +
                        "last_Name=?," +
                        "nic=? ," +
                        "house_No=? ," +
                        "street=? ," +
                        "city=?," +
                        "contact_No=?," +
                        "email=?," +
                        "role=?" +
                        "WHERE employee_Id=?",
                employeeDTO.getFirst_Name(),
                employeeDTO.getLast_Name(),
                employeeDTO.getNic(),
                employeeDTO.getHouse_No(),
                employeeDTO.getStreet(),
                employeeDTO.getCity(),
                employeeDTO.getContact_No(),
                employeeDTO.getEmail(),
                employeeDTO.getRole(),
                employeeDTO.getEmployee_Id()
        );
    }

    public boolean delete(String id) throws SQLException {
        return SQLUtill.execute("DELETE FROM employee WHERE employee_Id=?", id);
    }

    public ArrayList<String> getAllEmployeeId() throws SQLException {
        ResultSet resultSet = SQLUtill.execute("SELECT employee_Id FROM employee ORDER BY LENGTH(employee_Id),employee_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
