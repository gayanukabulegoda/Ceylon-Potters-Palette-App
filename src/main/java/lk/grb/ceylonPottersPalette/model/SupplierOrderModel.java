package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderModel {

    public boolean save(SupplierOrderDto supplierOrderDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO supplier_Order VALUES (?,?,?,?,?)",
                supplierOrderDto.getSupplier_Order_Id(),
                supplierOrderDto.getSupplier_Id(),
                supplierOrderDto.getTotal_Price(),
                supplierOrderDto.getDate(),
                supplierOrderDto.getTime());
    }

    public ArrayList<String> getAllSupplierOrderId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplier_Order_Id FROM supplier_Order ORDER BY LENGTH(supplier_Order_Id),supplier_Order_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
