package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.dto.SupplierOrderDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierOrderDetailModel {

    public boolean save(SupplierOrderDto supplierOrderDto) throws SQLException {
        String sql = "INSERT INTO supplier_Order_Detail VALUES (?,?,?)";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < supplierOrderDto.getOrderList().size(); i++) {
            statement.setString(1, supplierOrderDto.getSupplier_Order_Id());
            statement.setString(2,supplierOrderDto.getOrderList().get(i)[0]);
            statement.setInt(3, Integer.parseInt(supplierOrderDto.getOrderList().get(i)[1]));

            int values = statement.executeUpdate();
            if (values == 0) {
                return false;
            }
        }
        return true;
    }
}
