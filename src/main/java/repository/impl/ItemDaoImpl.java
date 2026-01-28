package repository.impl;

import db.DBConnection;
import repository.ItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDaoImpl implements ItemDao {

    @Override
    public void addItem(String code, String desc, String size, Double price, Integer qty) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO item VALUES(?,?,?,?,?)");

            preparedStatement.setObject(1,code);
            preparedStatement.setObject(2,desc);
            preparedStatement.setObject(3,size);
            preparedStatement.setObject(4,price);
            preparedStatement.setObject(5,qty);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(String desc, String size, Double price, Integer qty, String code) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE item SET Description = ? , PackSize = ? , UnitPrice = ? , QtyOnHand = ? WHERE ItemCode = ?");

            preparedStatement.setObject(1,desc);
            preparedStatement.setObject(2,size);
            preparedStatement.setObject(3,price);
            preparedStatement.setObject(4,qty);
            preparedStatement.setObject(5,code);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteItem(String code) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM item WHERE ItemCode = ?");

            preparedStatement.setObject(1,code);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet searchItem(String code) throws SQLException {

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE ItemCode = ?");
            preparedStatement.setString(1,code);
            ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    @Override
    public ResultSet getAllItems() throws SQLException {

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item");
            ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }
}
