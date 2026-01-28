package service.impl;

import config.Config;
import dto.CustomerDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.CustomerDao;
import service.CustomerBo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerBoImpl implements CustomerBo {

    CustomerDao customerDao = Config.getCustomer();


    @Override
    public void addCustomer(String id, String title, String name, LocalDate DOB, Double salary, String address, String city, String province, String postalCode) {
        customerDao.addCustomer(id, title, name, DOB, salary, address, city, province, postalCode);

    }

    @Override
    public void updateCustomer(String title, String name, LocalDate DOB, Double salary, String address, String city, String province, String postalCode, String id) {
        customerDao.updateCustomer(title, name, DOB, salary, address, city, province, postalCode, id);

    }

    @Override
    public void deleteCustomer(String id) {
        customerDao.deleteCustomer(id);
    }


    @Override
    public ObservableList<CustomerDto> getAllCustomers() {
        ObservableList<CustomerDto> customerInfoDto = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = customerDao.getAllCustomers();

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
    public CustomerDto searchCustomer(String id)  {
        CustomerDto customerDto = null;
        try {
            ResultSet resultSet = customerDao.searchCustomer(id);

            if(resultSet.next()){
                customerDto = new CustomerDto(
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
        return customerDto;
    }
}
