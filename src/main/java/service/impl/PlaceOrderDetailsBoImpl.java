package service.impl;

import dto.CartDto;
import dto.OrderDetailDto;
import dto.OrderDto;
import javafx.collections.ObservableList;
import repository.PlaceOrderDetailsDao;
import repository.impl.PlaceOrderDetailsDaoImpl;
import service.PlaceOrderDetailsBo;

public class PlaceOrderDetailsBoImpl implements PlaceOrderDetailsBo {

    PlaceOrderDetailsDao placeOrderDetailsDao = new PlaceOrderDetailsDaoImpl();

    @Override
    public void addOrder(OrderDto orderDto, ObservableList<CartDto> addCart) {
        for(CartDto cartItemDto : addCart){
            placeOrderDetailsDao.addOrderDetails(new OrderDetailDto(
                    orderDto.getOrderId(),
                    cartItemDto.getItemCode(),
                    cartItemDto.getQty(),
                    Integer.valueOf(cartItemDto.getDiscount())
            ));
        }
    }
}
