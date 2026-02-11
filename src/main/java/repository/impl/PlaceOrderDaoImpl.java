package repository.impl;

import db.DBConnection;
import dto.OrderDetailDto;
import dto.OrderDto;
import repository.PlaceOrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaceOrderDaoImpl implements PlaceOrderDao {
    @Override
    public void addOrder(OrderDto orderDto) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders VALUES (?,?,?)");
            preparedStatement.setObject(1,orderDto.getOrderId());
            preparedStatement.setObject(2,orderDto.getOrderDate());
            preparedStatement.setObject(3,orderDto.getCustomerId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
