package service.impl;

import config.Config;
import dto.CustomerDto;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import repository.CustomerRepository;
import repository.CustomerRepositoryImpl;
import service.CustomerService;

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
    public ObservableList<CustomerDto> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteCustomer(id);
    }

    @Override
    public CustomerDto searchCustomer(String id) {
        return customerRepository.searchCustomer(id);

    }
}
