package repository.impl;

import db.DBConnection;
import dto.CustomerDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerRepositoryImpl implements CustomerRepository {



    @Override
    public void addCustomer(String id, String title, String name, LocalDate DOB, Double salary, String address, String city, String province, String postalCode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?)");

            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,title);
            preparedStatement.setObject(3,name);
            preparedStatement.setObject(4,DOB);
            preparedStatement.setObject(5,salary);
            preparedStatement.setObject(6,address);
            preparedStatement.setObject(7,city);
            preparedStatement.setObject(8,province);
            preparedStatement.setObject(9,postalCode);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(String title, String name, LocalDate DOB, Double salary, String address, String city, String province, String postalCode, String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer SET CustTitle = ? , CustName = ? , DOB = ? , salary = ? , CustAddress = ? , City = ? , Province = ? , PostalCode = ? WHERE CustID = ?");

            preparedStatement.setObject(1,title);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,DOB);
            preparedStatement.setObject(4,salary);
            preparedStatement.setObject(5,address);
            preparedStatement.setObject(6,city);
            preparedStatement.setObject(7,province);
            preparedStatement.setObject(8,postalCode);
            preparedStatement.setObject(9,id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<CustomerDto> getAllCustomers() {
        ObservableList<CustomerDto> customerInfoDto = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                CustomerDto customerDto = new CustomerDto(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getDate("DOB"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                customerInfoDto.add(customerDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerInfoDto;
    }

    @Override
    public void deleteCustomer(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE CustID = ?");

            preparedStatement.setObject(1,id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CustomerDto searchCustomer(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE CustID = ?");
            preparedStatement.setString(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new CustomerDto(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getDate("DOB"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
