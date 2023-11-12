package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.EmployeeAttendanceDto;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeAttendanceModel {

    public boolean save(EmployeeAttendanceDto employeeAttendanceDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO attendance VALUES (?,?,?)",
                employeeAttendanceDto.getEmployee_Id(),
                employeeAttendanceDto.getDate(),
                employeeAttendanceDto.getTime());
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
