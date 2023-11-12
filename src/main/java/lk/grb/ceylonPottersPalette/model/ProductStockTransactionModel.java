package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductStockTransactionModel {

    ProductStockModel productStockModel = new ProductStockModel();
    RepairStockModel repairStockModel = new RepairStockModel();

    public boolean saveProduct(ProductStockDto productStockDto) throws SQLException {
        Connection connection = null;
        boolean update = false;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isSaved = productStockModel.save(productStockDto);

            if (isSaved) {
                boolean saved = repairStockModel.save(productStockDto.getProduct_Id(), 0);
                if (saved) {
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
