package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public CustomerOrderDto getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM customer_Order_Detail WHERE customer_Order_Id=?", id);

        CustomerOrderDto customerOrderDto = new CustomerOrderDto();

        if (set.next()) {
            customerOrderDto.setCustomer_Order_Id(set.getString(1));
            customerOrderDto.setCustomer_Id(set.getString(2));
            customerOrderDto.setTotal_Price(Double.parseDouble(set.getString(3)));
            customerOrderDto.setDate(set.getString(4));
            customerOrderDto.setTime(set.getString(5));
        }
        return customerOrderDto;
    }
}
