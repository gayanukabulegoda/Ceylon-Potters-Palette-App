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

    public CustomerOrderDto getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM customer_Order WHERE customer_Order_Id=?", id);

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

    public ArrayList<String> getAllCustomerOrderId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_Order_Id FROM customer_Order ORDER BY LENGTH(customer_Order_Id),customer_Order_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public String getAllSales() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM customer_Order");

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
