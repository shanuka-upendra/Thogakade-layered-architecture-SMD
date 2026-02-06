package service;

import dto.ItemDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface PlaceOrderBo {
    Double getUnitPrice(String s);
    String getItemCode(String value);
    ObservableList<ItemDto> getAllItems();
    String getCustomerNameById(String text);

}
