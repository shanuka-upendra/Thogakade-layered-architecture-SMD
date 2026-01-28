package repository;

import dto.ItemDto;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ItemRepository {
    void addItem(String code, String desc, String size, Double price, Integer qty);
    void updateItem(String desc, String size, Double price, Integer qty,String code);
    void deleteItem(String code);
    ResultSet searchItem(String code) throws SQLException;
    ResultSet getAllItems() throws SQLException;
}
