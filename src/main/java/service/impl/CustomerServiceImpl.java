package service.impl;

import dto.CustomerDto;
import javafx.collections.ObservableList;
import service.CustomerService;

import java.time.LocalDate;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public void addCustomer(String id, String title, String name, LocalDate DOB, Double salary, String address, String city, String province, String postalCode) {

    }

    @Override
    public void updateCustomer(String title, String name, LocalDate DOB, Double salary, String address, String city, String province, String postalCode, String id) {

    }

    @Override
    public ObservableList<CustomerDto> getAllCustomers() {
        return null;
    }

    @Override
    public void deleteCustomer(String id) {

    }

    @Override
    public CustomerDto searchCustomer(String id) {
        return null;
    }
}
