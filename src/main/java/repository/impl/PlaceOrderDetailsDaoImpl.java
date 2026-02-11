package repository.impl;

import db.DBConnection;
import dto.CartDto;
import dto.OrderDetailDto;
import dto.OrderDto;
import javafx.collections.ObservableList;
import repository.PlaceOrderDetailsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaceOrderDetailsDaoImpl implements PlaceOrderDetailsDao {

    @Override
    public void addOrderDetails(OrderDetailDto orderDetailDto) {
        try {
             Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orderdetail VALUES(?,?,?,?)");
             preparedStatement.setObject(1,orderDetailDto.getId());
             preparedStatement.setObject(2,orderDetailDto.getCode());
             preparedStatement.setObject(3,orderDetailDto.getQty());
             preparedStatement.setObject(4,orderDetailDto.getDiscount());

             preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
