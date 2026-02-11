package repository.impl;

import db.DBConnection;
import dto.OrderDetailDto;
import repository.PlaceOrderDetailsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaceOrderDetailsDaoImpl implements PlaceOrderDetailsDao {

    @Override
    public boolean addOrderDetails(OrderDetailDto orderDetailDto) {
        try {
             Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orderdetail VALUES(?,?,?,?)");
             preparedStatement.setObject(1,orderDetailDto.getId());
             preparedStatement.setObject(2,orderDetailDto.getCode());
             preparedStatement.setObject(3,orderDetailDto.getQty());
             preparedStatement.setObject(4,orderDetailDto.getDiscount());

             return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
