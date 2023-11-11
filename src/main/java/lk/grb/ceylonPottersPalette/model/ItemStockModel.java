package lk.grb.ceylonPottersPalette.model;

import lk.grb.ceylonPottersPalette.dto.ItemStockDto;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemStockModel {

    public boolean save(ItemStockDto itemStockDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO item_Stock VALUES (?,?,?,?)",
                itemStockDto.getItem_Id(),
                itemStockDto.getDescription(),
                itemStockDto.getUnit_Price(),
                itemStockDto.getQty_On_Hand());
    }

    public ItemStockDto getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM item_Stock WHERE item_Id=?", id);

        ItemStockDto itemStockDto = new ItemStockDto();

        if (set.next()) {
            itemStockDto.setItem_Id(set.getString(1));
            itemStockDto.setDescription(set.getString(2));
            itemStockDto.setUnit_Price(Integer.parseInt(set.getString(3)));
            itemStockDto.setQty_On_Hand(Integer.parseInt(set.getString(4)));
        }
        return itemStockDto;
    }

    public boolean update(ItemStockDto itemStockDto) throws SQLException {
        return SQLUtil.execute("UPDATE item_Stock SET " +
                        "description=?," +
                        "unit_Price=?," +
                        "qty_On_Hand=? ," +
                        "WHERE item_Id=?",
                itemStockDto.getDescription(),
                itemStockDto.getUnit_Price(),
                itemStockDto.getQty_On_Hand()
        );
    }

    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM item_Stock WHERE item_Id=?", id);
    }

    public ArrayList<String> getAllItemId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT item_Id FROM item_Stock ORDER BY LENGTH(item_Id),item_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
