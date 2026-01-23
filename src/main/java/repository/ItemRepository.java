package repository;

import dto.ItemDto;
import javafx.collections.ObservableList;

public interface ItemRepository {
    void addItem(String code, String desc, String size, Double price, Integer qty);
    void updateItem(String desc, String size, Double price, Integer qty,String code);
    void deleteItem(String code);
    ItemDto searchItem(String code);
    ObservableList<ItemDto> getAllItems();
}
