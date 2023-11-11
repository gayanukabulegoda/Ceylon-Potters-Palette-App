package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.dto.EmployeeAttendanceDto;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.SQLException;

public class EmployeeAttendanceModel {

    public boolean save(EmployeeAttendanceDto employeeAttendanceDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO attendance VALUES (?,?,?)",
                employeeAttendanceDto.getEmployee_Id(),
                employeeAttendanceDto.getDate(),
                employeeAttendanceDto.getTime());
    }
}
