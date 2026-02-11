package repository;

import java.sql.ResultSet;

public interface ItemDao {
    void addItem(String code, String desc, String size, Double price, Integer qty);
    void updateItem(String desc, String size, Double price, Integer qty,String code);
    void deleteItem(String code);
    ResultSet searchItem(String code);
    ResultSet getAllItems();
    Double getUnitPrice(String s);
    String getItemCode(String value);
    boolean updateItemQty(String itemCode, Integer qty);
}
