package config;

import repository.impl.CustomerRepositoryImpl;
import repository.impl.ItemRepositoryImpl;
import service.impl.CustomerServiceImpl;
import service.impl.ItemServiceImpl;

public class Config {
    public static CustomerRepositoryImpl getCustomer(){
        return new CustomerRepositoryImpl();
    }

    public static CustomerServiceImpl getService(){
        return new CustomerServiceImpl();
    }

    public static ItemRepositoryImpl getItemRepo(){
        return new ItemRepositoryImpl();
    }

    public static ItemServiceImpl getItemSev(){
        return new ItemServiceImpl();
    }
}
