package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.db.DbConnection;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductStockModel {

    public boolean save(ProductStockDto productStockDTO) throws SQLException {
        return SQLUtil.execute("INSERT INTO product_Stock VALUES (?,?,?,?,?,?)",
                productStockDTO.getProduct_Id(),
                productStockDTO.getDescription(),
                productStockDTO.getQty_On_Hand(),
                productStockDTO.getUnit_Price(),
                productStockDTO.getCategory(),
                productStockDTO.getQty());
    }

    public ProductStockDto getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM product_Stock WHERE customer_Id=?", id);

        ProductStockDto productStockDTO = new ProductStockDto();

        if (set.next()) {
            productStockDTO.setProduct_Id(set.getString(1));
            productStockDTO.setDescription(set.getString(2));
            productStockDTO.setQty_On_Hand(Integer.parseInt(set.getString(3)));
            productStockDTO.setUnit_Price(Double.parseDouble(set.getString(4)));
            productStockDTO.setCategory(set.getString(5));
            productStockDTO.setQty(Integer.parseInt(set.getString(6)));
        }
        return productStockDTO;
    }

    public boolean update(ArrayList<String[]> arrayList) throws SQLException {
        String sql = "UPDATE product_Stock SET qty_On_Hand = qty_On_Hand - ? WHERE product_Id=?";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < arrayList.size() ; i++) {
            statement.setInt(1, Integer.parseInt(arrayList.get(i)[1]));
            statement.setString(2,arrayList.get(i)[0]);
            int value = statement.executeUpdate();

            if (value == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM product_Stock WHERE product_Id=?", id);
    }

    public ArrayList<String> getAllProductId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT product_Id FROM product_Stock ORDER BY LENGTH(product_Id),product_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public String getDescription(String id) throws SQLException {

        String sql = ("SELECT description FROM product_Stock WHERE product_Id=?");
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public String getUnitPrice(String id) throws SQLException {

        String sql = ("SELECT unit_Price FROM product_Stock WHERE product_Id=?");
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public String getQtyOnHand(String id) throws SQLException {

        String sql = ("SELECT qty_On_Hand FROM product_Stock WHERE product_Id=?");
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}