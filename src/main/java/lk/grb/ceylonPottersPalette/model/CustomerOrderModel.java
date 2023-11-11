package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderModel {

    public boolean save(CustomerOrderDto customerOrderDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO customer_Order VALUES (?,?,?,?,?)",
                customerOrderDto.getCustomer_Order_Id(),
                customerOrderDto.getCustomer_Id(),
                customerOrderDto.getTotal_Price(),
                customerOrderDto.getDate(),
                customerOrderDto.getTime());
    }

    public ArrayList<String> getAllCustomerOrderId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_Order_Id FROM customer_Order ORDER BY LENGTH(customer_Order_Id),customer_Order_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
