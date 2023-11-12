package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateRepairStockModel {

    ProductStockModel productStockModel = new ProductStockModel();
    RepairStockModel repairStockModel = new RepairStockModel();

    public boolean updateRepairStock(String product_Id, String qty) throws SQLException {

        Connection connection = null;
        boolean update = false;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isUpdated = productStockModel.update(product_Id, qty);

            if (isUpdated) {
                boolean updated = repairStockModel.update(product_Id, qty);

                if (updated) {
                    connection.commit();
                    update = true;
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
        finally {
            connection.setAutoCommit(true);
        }
        return update;
    }
}
