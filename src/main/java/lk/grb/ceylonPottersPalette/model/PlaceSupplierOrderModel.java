package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.dto.SupplierOrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceSupplierOrderModel {

    SupplierOrderModel supplierOrderModel = new SupplierOrderModel();
    ItemStockModel itemStockModel = new ItemStockModel();
    SupplierOrderDetailModel supplierOrderDetailModel = new SupplierOrderDetailModel();

    public boolean placeSupplierOrder(SupplierOrderDto supplierOrderDto) {

        boolean isSaved = false;
        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean save = supplierOrderModel.save(supplierOrderDto);

            if (save) {
                boolean update = itemStockModel.update(supplierOrderDto.getOrderList());

                if (update) {
                    boolean saveSupOrder = supplierOrderDetailModel.save(supplierOrderDto);

                    if (saveSupOrder) {
                        connection.commit();
                        isSaved = true;
                    }
                }
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return isSaved;
    }
}
