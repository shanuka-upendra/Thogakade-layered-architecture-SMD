package service.impl;

import config.Config;
import dto.ItemDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.ItemDao;
import service.ItemBo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemBoImpl implements ItemBo {

    ItemDao itemDao = Config.getItemRepo();

    @Override
    public void addItem(String code, String desc, String size, Double price, Integer qty) {
        itemDao.addItem(code, desc, size, price, qty);
    }

    @Override
    public void updateItem(String desc, String size, Double price, Integer qty, String code) {
        itemDao.updateItem(desc, size, price, qty, code);

    }

    @Override
    public void deleteItem(String code) {
        itemDao.deleteItem(code);
    }

    @Override
    public ItemDto searchItem(String code) throws SQLException {
        ItemDto itemDto = null;
        ResultSet resultSet = itemDao.searchItem(code);

            try {
                while (resultSet.next()) {
                    itemDto = new ItemDto(
                            resultSet.getString("ItemCode"),
                            resultSet.getString("Description"),
                            resultSet.getString("PackSize"),
                            resultSet.getDouble("UnitPrice"),
                            resultSet.getInt("QtyOnHand")
                    );
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return itemDto;
        }


    @Override
    public ObservableList<ItemDto> getAllItems() {
        ObservableList<ItemDto> itemDetails = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = itemDao.getAllItems();
            while (resultSet.next()) {
                itemDetails.add(new ItemDto(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemDetails;
    }
}
