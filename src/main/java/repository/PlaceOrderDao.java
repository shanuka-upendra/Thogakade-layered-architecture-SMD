package repository;

import dto.OrderDetailDto;
import dto.OrderDto;

public interface PlaceOrderDao {
    void addOrder(OrderDto orderDto);
}
