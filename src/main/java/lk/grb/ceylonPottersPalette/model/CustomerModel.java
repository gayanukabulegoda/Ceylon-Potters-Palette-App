package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {

    public boolean save(CustomerDto customerDTO) throws SQLException {
        return SQLUtil.execute("insert into customer VALUES (?,?,?,?,?)",
                customerDTO.getCustomer_Id(),
                customerDTO.getName(),
                customerDTO.getCustomer_Id(),
                customerDTO.getEmail(),
                customerDTO.getUser_Name());
    }

    public CustomerDto getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM customer WHERE customer_Id=?", id);

        CustomerDto customerDTO = new CustomerDto();

        if (set.next()) {
            customerDTO.setCustomer_Id(set.getString(1));
            customerDTO.setName(set.getString(2));
            customerDTO.setContact_No(set.getString(3));
            customerDTO.setEmail(set.getString(4));
            customerDTO.setUser_Name(set.getString(7));
        }
        return customerDTO;
    }

    public boolean update(CustomerDto customerDTO) throws SQLException {
        return SQLUtil.execute("UPDATE customer SET " +
                        "name=?," +
                        "email=?," +
                        "contact_No=? ," +
                        "WHERE customer_Id=?",
                customerDTO.getName(),
                customerDTO.getEmail(),
                customerDTO.getContact_No()
        );
    }

    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM customer WHERE customer_Id=?", id);
    }

    public ArrayList<String> getAllCustomerId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_Id FROM customer ORDER BY LENGTH(customer_Id),customer_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
