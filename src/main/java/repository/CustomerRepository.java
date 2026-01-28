package repository;

import dto.CustomerDto;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface CustomerRepository {
    void addCustomer(String id, String title, String name, LocalDate DOB, Double salary, String address, String city, String province, String postalCode);
    void updateCustomer(String title, String name, LocalDate DOB,Double salary,String address,String city,String province,String postalCode,String id);
    ResultSet getAllCustomers() throws SQLException;
    void deleteCustomer(String id);
    ResultSet searchCustomer(String id) throws SQLException;
}
