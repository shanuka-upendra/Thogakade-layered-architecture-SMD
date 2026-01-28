package service.impl;

import config.Config;
import dto.ItemDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.ItemRepository;
import service.ItemService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository = Config.getItemRepo();

    @Override
    public void addItem(String code, String desc, String size, Double price, Integer qty) {
        itemRepository.addItem(code, desc, size, price, qty);
    }

    @Override
    public void updateItem(String desc, String size, Double price, Integer qty, String code) {
        itemRepository.updateItem(desc, size, price, qty, code);

    }

    @Override
    public void deleteItem(String code) {
        itemRepository.deleteItem(code);
    }

    @Override
    public ItemDto searchItem(String code) throws SQLException {
        ItemDto itemDto = null;
        ResultSet resultSet = itemRepository.searchItem(code);

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
            ResultSet resultSet = itemRepository.getAllItems();
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
