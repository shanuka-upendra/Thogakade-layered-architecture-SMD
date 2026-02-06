package service;

import dto.ItemDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ItemBo {
    void addItem(String code, String desc, String size, Double price, Integer qty);
    void updateItem(String desc, String size, Double price, Integer qty, String code);
    void deleteItem(String code);
    ItemDto searchItem(String code);
    ObservableList<ItemDto> getAllItems();
    Double getUnitPrice(String s);
    String getItemCode(String value);
}
