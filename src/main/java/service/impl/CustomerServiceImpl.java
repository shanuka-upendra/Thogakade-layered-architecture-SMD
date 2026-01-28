package service.impl;

import config.Config;
import dto.CustomerDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.CustomerRepository;
import service.CustomerService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository = Config.getCustomer();


    @Override
    public void addCustomer(String id, String title, String name, LocalDate DOB, Double salary, String address, String city, String province, String postalCode) {
        customerRepository.addCustomer(id, title, name, DOB, salary, address, city, province, postalCode);

    }

    @Override
    public void updateCustomer(String title, String name, LocalDate DOB, Double salary, String address, String city, String province, String postalCode, String id) {
        customerRepository.updateCustomer(title, name, DOB, salary, address, city, province, postalCode, id);

    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteCustomer(id);
    }


    @Override
    public ObservableList<CustomerDto> getAllCustomers() {
        ObservableList<CustomerDto> customerInfoDto = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = customerRepository.getAllCustomers();

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
            ResultSet resultSet = customerRepository.searchCustomer(id);

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
