package service.impl;

import config.Config;
import dto.CartDto;
import dto.ItemDto;
import dto.OrderDto;
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
    public ItemDto searchItem(String code){
        ItemDto itemDto = null;
            try {
                ResultSet resultSet = itemDao.searchItem(code);
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

    @Override
    public Double getUnitPrice(String price){
        return itemDao.getUnitPrice(price);
    }

    @Override
    public String getItemCode(String value){
        return itemDao.getItemCode(value);
    }

    @Override
    public void updateItemQty(OrderDto orderDto, ObservableList<CartDto> addCart) {
        for(CartDto cartItemDto:addCart){
            itemDao.updateItemQty(cartItemDto.getItemCode(),cartItemDto.getQty());
        }
    }


}
