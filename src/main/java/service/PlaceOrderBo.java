package service;

import dto.ItemDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface PlaceOrderBo {
    Double getUnitPrice(String s) throws SQLException;
    String getItemCode(String value) throws SQLException;
    ObservableList<ItemDto> getAllItems() throws SQLException;
    String getCustomerNameById(String text) throws SQLException;

}
