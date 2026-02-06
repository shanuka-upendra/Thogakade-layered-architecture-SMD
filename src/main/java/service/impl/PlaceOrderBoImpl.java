package service.impl;

import dto.ItemDto;
import javafx.collections.ObservableList;
import repository.ItemDao;
import repository.impl.ItemDaoImpl;
import service.CustomerBo;
import service.ItemBo;
import service.PlaceOrderBo;

import java.sql.SQLException;

public class PlaceOrderBoImpl implements PlaceOrderBo {

    CustomerBo customerBo = new CustomerBoImpl();
    ItemBo itemBo = new ItemBoImpl();

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
    public ObservableList<ItemDto> getAllItems(){
        return itemBo.getAllItems();
    }
}
