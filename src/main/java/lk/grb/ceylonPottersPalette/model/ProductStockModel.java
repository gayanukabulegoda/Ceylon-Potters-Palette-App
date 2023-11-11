package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

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

    public boolean update(ProductStockDto productStockDTO) throws SQLException {
        return SQLUtil.execute("UPDATE product_Stock SET " +
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
}