package service;

import dto.CartDto;
import dto.OrderDto;
import javafx.collections.ObservableList;

public interface PlaceOrderDetailsBo {
    void addOrder(OrderDto orderDto, ObservableList<CartDto> addCart);
}
