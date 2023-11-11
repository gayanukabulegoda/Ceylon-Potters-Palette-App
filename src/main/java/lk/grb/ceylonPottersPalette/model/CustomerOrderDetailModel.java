package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerOrderDetailModel {

    public boolean save(CustomerOrderDto customerOrderDto) throws SQLException {
        String sql = "insert into customer_Order_Detail VALUES (?,?,?)";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < customerOrderDto.getOrderList().size(); i++) {
            statement.setString(1, customerOrderDto.getCustomer_Order_Id());
            statement.setString(2,customerOrderDto.getOrderList().get(i)[0]);
            statement.setInt(3, Integer.parseInt(customerOrderDto.getOrderList().get(i)[1]));

            int values = statement.executeUpdate();
            if (values == 0) {
                return false;
            }
        }
        return true;
    }
}
