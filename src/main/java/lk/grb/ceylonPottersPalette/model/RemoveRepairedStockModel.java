package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class RemoveRepairedStockModel {

    ProductStockModel productStockModel = new ProductStockModel();
    RepairStockModel repairStockModel = new RepairStockModel();

    public boolean removeRepairStock(String product_Id, String qty) throws SQLException {

        Connection connection = null;
        boolean update = false;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isUpdated = productStockModel.updateIncrement(product_Id, qty);
            System.out.println(isUpdated+ " product");

            if (isUpdated) {
                boolean updated = repairStockModel.updateDecrement(product_Id, qty);
                System.out.println(isUpdated+ " repair");

                if (updated) {
                    connection.commit();
                    update = true;
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return update;
    }
}
