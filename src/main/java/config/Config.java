package config;

import repository.impl.CustomerDaoImpl;
import repository.impl.ItemDaoImpl;
import service.impl.CustomerBoImpl;
import service.impl.ItemBoImpl;

public class Config {
    public static CustomerDaoImpl getCustomer(){
        return new CustomerDaoImpl();
    }

    public static CustomerBoImpl getService(){
        return new CustomerBoImpl();
    }

    public static ItemDaoImpl getItemRepo(){
        return new ItemDaoImpl();
    }

    public static ItemBoImpl getItemSev(){
        return new ItemBoImpl();
    }
}
