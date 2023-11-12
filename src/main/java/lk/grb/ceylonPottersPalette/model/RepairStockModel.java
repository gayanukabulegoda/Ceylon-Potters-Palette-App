package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.dto.RepairStockDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairStockModel {

    public RepairStockDto getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM repair_Stock WHERE product_Id=?", id);

        RepairStockDto repairStockDto = new RepairStockDto();

        if (set.next()) {
            repairStockDto.setProduct_Id(set.getString(1));
            repairStockDto.setQty_To_Repair(set.getString(2));
        }
        return repairStockDto;
    }

    public boolean update(String id, String qty) throws SQLException {
        String sql = "UPDATE repair_Stock SET qty_To_Repair = qty_To_Repair + ? WHERE product_Id=?";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        statement.setString(1,qty);
        statement.setString(2,id);
        int i = statement.executeUpdate();

        if (i > 0) {
            return true;
        }
        return false;
    }

    public boolean updateDecrement(String id, String qty) throws SQLException {
        String sql = "UPDATE repair_Stock SET qty_To_Repair = qty_To_Repair - ? WHERE product_Id=?";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        statement.setString(1,qty);
        statement.setString(2,id);
        int i = statement.executeUpdate();

        if (i > 0) {
            return true;
        }
        return false;
    }

    public boolean save(String productId, int qty) throws SQLException {
        return SQLUtil.execute("INSERT INTO repair_Stock VALUES (?,?)",productId,qty);
    }
}
