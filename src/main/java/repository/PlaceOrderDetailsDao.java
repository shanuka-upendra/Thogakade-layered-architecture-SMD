package repository;

import dto.CartDto;
import dto.OrderDetailDto;
import dto.OrderDto;
import javafx.collections.ObservableList;

public interface PlaceOrderDetailsDao {
    void addOrderDetails(OrderDetailDto orderDetailDto);
}
