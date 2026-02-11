package repository;

import dto.OrderDto;

public interface PlaceOrderDao {
    boolean addOrder(OrderDto orderDto);
}
