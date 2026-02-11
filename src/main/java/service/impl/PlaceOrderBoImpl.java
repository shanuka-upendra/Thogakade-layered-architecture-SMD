package service.impl;

import dto.CartDto;
import dto.ItemDto;
import dto.OrderDetailDto;
import dto.OrderDto;
import javafx.collections.ObservableList;
import repository.PlaceOrderDao;
import repository.impl.PlaceOrderDaoImpl;
import service.CustomerBo;
import service.ItemBo;
import service.PlaceOrderBo;
import service.PlaceOrderDetailsBo;

public class PlaceOrderBoImpl implements PlaceOrderBo {

    CustomerBo customerBo = new CustomerBoImpl();
    ItemBo itemBo = new ItemBoImpl();
    PlaceOrderDao placeOrderDao = new PlaceOrderDaoImpl();
    PlaceOrderDetailsBo placeOrderDetailsBo = new PlaceOrderDetailsBoImpl();

    @Override
    public Double getUnitPrice(String s) {
        return itemBo.getUnitPrice(s);
    }

    @Override
    public String getItemCode(String value){
        return itemBo.getItemCode(value);
    }

    @Override
    public String getCustomerNameById(String text){
        return customerBo.getCustomerNameById(text);
    }

    @Override
    public void addOrder(OrderDto orderDto, ObservableList<CartDto> addCart) {
        placeOrderDao.addOrder(orderDto);
        placeOrderDetailsBo.addOrder(orderDto, addCart);
        itemBo.updateItemQty(orderDto,addCart);
    }

    @Override
    public ObservableList<ItemDto> getAllItems(){
        return itemBo.getAllItems();
    }
}
