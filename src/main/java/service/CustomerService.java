package service;

import dto.CustomerDto;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public interface CustomerService {
    void addCustomer(String id, String title, String name, LocalDate DOB, Double salary, String address, String city, String province, String postalCode);
    void updateCustomer(String title, String name, LocalDate DOB,Double salary,String address,String city,String province,String postalCode,String id);
    ObservableList<CustomerDto> getAllCustomers();
    void deleteCustomer(String id);
    CustomerDto searchCustomer(String id);
}
