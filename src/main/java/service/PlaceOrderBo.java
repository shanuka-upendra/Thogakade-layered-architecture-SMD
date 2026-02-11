package service;

import dto.CartDto;
import dto.ItemDto;
import dto.OrderDetailDto;
import dto.OrderDto;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface PlaceOrderBo {
    Double getUnitPrice(String s);
    String getItemCode(String value);
    ObservableList<ItemDto> getAllItems();
    String getCustomerNameById(String text);
    void addOrder(OrderDto orderDto, ObservableList<CartDto> addCart);
}
