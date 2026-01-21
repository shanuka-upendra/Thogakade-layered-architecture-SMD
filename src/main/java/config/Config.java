package config;

import repository.CustomerRepositoryImpl;
import service.impl.CustomerServiceImpl;

public class Config {
    public static CustomerRepositoryImpl getCustomer(){
        return new CustomerRepositoryImpl();
    }

    public static CustomerServiceImpl getService(){
        return new CustomerServiceImpl();
    }
}
