package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.EmployeeAttendanceDto;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.dto.EmployeeSalaryDto;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeAttendanceModel {

    public boolean save(EmployeeAttendanceDto employeeAttendanceDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO attendance VALUES (?,?,?,?)",
                employeeAttendanceDto.getAttendance_Id(),
                employeeAttendanceDto.getEmployee_Id(),
                employeeAttendanceDto.getDate(),
                employeeAttendanceDto.getTime());
    }

    public boolean update(EmployeeAttendanceDto employeeAttendanceDto) throws SQLException {

        return SQLUtil.execute("UPDATE attendance SET " +
                        "employee_Id=?" +
                        "WHERE attendance_Id=?",
                employeeAttendanceDto.getEmployee_Id(),
                employeeAttendanceDto.getAttendance_Id()
        );
    }

    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM attendance WHERE attendance_Id=?", id);
    }

    public EmployeeAttendanceDto getData(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM attendance WHERE attendance_Id=?", id);

        EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();

        if(resultSet.next()){
            employeeAttendanceDto.setEmployee_Id(resultSet.getString(2));
            employeeAttendanceDto.setDate(resultSet.getString(3));
            employeeAttendanceDto.setTime(resultSet.getString(4));
        }
        return employeeAttendanceDto;
    }

    public ArrayList<String> getAllAttendanceId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT attendance_Id FROM attendance ORDER BY attendance_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
            System.out.println(resultSet.getString(1));
        }
        return list;
    }

    public String workedDayCount(String id, String date) throws SQLException {
        String sql = "SELECT COUNT(*) AS work_days FROM attendance WHERE employee_Id = ? AND date LIKE ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);
        pstm.setString(2,date);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
