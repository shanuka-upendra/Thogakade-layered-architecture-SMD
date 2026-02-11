package service.impl;

import db.DBConnection;
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

import java.sql.Connection;
import java.sql.SQLException;

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
    public void addOrder(OrderDto orderDto, ObservableList<CartDto> addCart) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try{
           connection.setAutoCommit(false);
           boolean isAddOrder = placeOrderDao.addOrder(orderDto);
           System.out.println("Order Added: "+ isAddOrder);

           if(isAddOrder) {
               boolean isOrderDetails = placeOrderDetailsBo.addOrder(orderDto, addCart);
               System.out.println("Order Details: "+isOrderDetails);
               if (isOrderDetails) {
                   boolean isUpdateItem = itemBo.updateItemQty(orderDto, addCart);
                   System.out.println("Item Quantity: "+isUpdateItem);
                   if(isUpdateItem){
                       connection.commit();
                   }
               }
           }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public ObservableList<ItemDto> getAllItems(){
        return itemBo.getAllItems();
    }
}
