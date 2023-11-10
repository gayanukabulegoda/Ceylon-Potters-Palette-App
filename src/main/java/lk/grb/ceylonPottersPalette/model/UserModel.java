package lk.grb.ceylonPottersPalette.model;

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
}
