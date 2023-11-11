package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceCustomerOrderModel {

    CustomerOrderModel customerOrderModel = new CustomerOrderModel();
    ProductStockModel productStockModel = new ProductStockModel();
    CustomerOrderDetailModel customerOrderDetailModel = new CustomerOrderDetailModel();

    public boolean placeCustomerOrder(CustomerOrderDto customerOrderDto) {

        boolean isSaved = false;
        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean save = customerOrderModel.save(customerOrderDto);

            if (save) {
                boolean update = productStockModel.update(customerOrderDto.getOrderList());

                if (update) {
                    boolean saveCusOrder = customerOrderDetailModel.save(customerOrderDto);

                    if (saveCusOrder) {
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
