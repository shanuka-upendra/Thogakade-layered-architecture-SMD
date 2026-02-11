package repository;

import dto.OrderDetailDto;

public interface PlaceOrderDetailsDao {
    boolean addOrderDetails(OrderDetailDto orderDetailDto);
}
