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
    public boolean addOrder(OrderDto orderDto, ObservableList<CartDto> addCart) {
        boolean allSaved = true;
        for(CartDto cartItemDto : addCart){

           OrderDetailDto detail = new OrderDetailDto(
                    orderDto.getOrderId(),
                    cartItemDto.getItemCode(),
                    cartItemDto.getQty(),
                    Integer.valueOf(cartItemDto.getDiscount())
           );

           boolean isSaved = placeOrderDetailsDao.addOrderDetails(detail);

           if(!isSaved){
               allSaved = false;
           }
        }
        return allSaved;
    }
}
