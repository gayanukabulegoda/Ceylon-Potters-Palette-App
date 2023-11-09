package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.dto.CustomerDTO;
import lk.grb.ceylonPottersPalette.dto.ProductStockDTO;
import lk.grb.ceylonPottersPalette.utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductStockModel {

    public boolean save(ProductStockDTO productStockDTO) throws SQLException {
        return SQLUtill.execute("INSERT INTO product_Stock VALUES (?,?,?,?,?)",
                productStockDTO.getProduct_Id(),
                productStockDTO.getDescription(),
                productStockDTO.getQty_On_Hand(),
                productStockDTO.getUnit_Price(),
                productStockDTO.getCategory(),
                productStockDTO.getQty());
    }

    public ProductStockDTO getData(String id) throws SQLException {
        ResultSet set = SQLUtill.execute("SELECT * FROM product_Stock WHERE customer_Id=?", id);

        ProductStockDTO productStockDTO = new ProductStockDTO();

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

    public boolean update(ProductStockDTO productStockDTO) throws SQLException {
        return SQLUtill.execute("UPDATE product_Stock SET " +
                        "description=?," +
                        "qty_On_Hand=?," +
                        "unit_Price=? ," +
                        "category=?   ," +
                        "WHERE product_Id=?",
                productStockDTO.getDescription(),
                productStockDTO.getQty_On_Hand(),
                productStockDTO.getUnit_Price(),
                productStockDTO.getCategory()
        );
    }

    public boolean delete(String id) throws SQLException {
        return SQLUtill.execute("DELETE FROM product_Stock WHERE product_Id=?", id);
    }

    public ArrayList<String> getAllProductId() throws SQLException {
        ResultSet resultSet = SQLUtill.execute("SELECT product_Id FROM product_Stock ORDER BY LENGTH(product_Id),product_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
}