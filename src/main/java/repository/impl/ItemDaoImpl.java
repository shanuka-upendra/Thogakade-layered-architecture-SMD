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
    public ResultSet searchItem(String code){

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item WHERE ItemCode = ?");
            preparedStatement.setString(1,code);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResultSet getAllItems(){

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item");
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Double getUnitPrice(String s) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT UnitPrice FROM item WHERE Description = ?");
            preparedStatement.setString(1,s);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getItemCode(String value){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ItemCode FROM item WHERE Description = ?");
            preparedStatement.setString(1,value);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateItemQty(String itemCode, Integer qty) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE item SET QtyOnHand = QtyOnHand - ? WHERE itemCode = ?");
            preparedStatement.setObject(1,qty);
            preparedStatement.setObject(2,itemCode);

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
