package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.dto.ItemStockDto;
import lk.grb.ceylonPottersPalette.dto.UserDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT user_Name FROM user WHERE user_Name=? AND password=?", userName, password);


        if (set.next()) {
            return set.getString(1);
        } else {
            return "No";
        }
    }

    public static String getRole(String userName) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT e.role " +
                "FROM employee e " +
                "JOIN user u ON e.employee_Id = u.employee_Id " +
                "WHERE u.user_Name = ?;", userName);


        if (set.next()) {
            return set.getString(1);
        } else {
            return "No";
        }
    }

    public boolean update(UserDto userDto) throws SQLException {
        return SQLUtil.execute("UPDATE user SET " +
                        "password=? " +
                        "WHERE user_Name=?",
                userDto.getPassword(),
                userDto.getUser_Name()
        );
    }

    public boolean save(UserDto userDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO user VALUES (?,?,?)",
                userDto.getUser_Name(),
                userDto.getPassword(),
                userDto.getEmployeeId());
    }

    public boolean delete(String userName) throws SQLException {
        return SQLUtil.execute("DELETE FROM user WHERE user_Name=?", userName);
    }

    public String getEmployeeId(String userName) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT employee_Id FROM user WHERE user_Name=?", userName);


        if (set.next()) {
            return set.getString(1);
        } else {
            return "null";
        }
    }

    public String getUserName(String employeeId) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT user_Name FROM user WHERE employee_Id=?", employeeId);


        if (set.next()) {
            return set.getString(1);
        } else {
            return "null";
        }
    }
}
