package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailModel {

    public boolean save(SupplierOrderDto supplierOrderDto) throws SQLException {
        String sql = "INSERT INTO supplier_Order_Detail VALUES (?,?,?)";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < supplierOrderDto.getOrderList().size(); i++) {
            statement.setString(1, supplierOrderDto.getSupplier_Order_Id());
            statement.setString(2,supplierOrderDto.getOrderList().get(i)[0]);
            statement.setInt(3, Integer.parseInt(supplierOrderDto.getOrderList().get(i)[1]));

            int values = statement.executeUpdate();
            if (values == 0) {
                return false;
            }
        }
        return true;
    }

    public SupplierOrderDto getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM supplier_Order_Detail WHERE supplier_Order_Id=?", id);

        SupplierOrderDto supplierOrderDto = new SupplierOrderDto();

        if (set.next()) {
            supplierOrderDto.setSupplier_Order_Id(set.getString(1));
            supplierOrderDto.setSupplier_Id(set.getString(2));
            supplierOrderDto.setTotal_Price(Double.parseDouble(set.getString(3)));
            supplierOrderDto.setDate(set.getString(4));
            supplierOrderDto.setTime(set.getString(5));
        }
        return supplierOrderDto;
    }

    public ArrayList<String[]> getDataAsAnArray(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM supplier_Order_Detail WHERE supplier_Order_Id=?", id);

        ArrayList<String[]> items = new ArrayList<>();

        while (set.next()) {

            String[] itemIdAndQuantity = new String[2];
            itemIdAndQuantity[0] = set.getString(2);
            itemIdAndQuantity[1] = set.getString(3);

            items.add(itemIdAndQuantity);
        }
        return items;
    }
}
