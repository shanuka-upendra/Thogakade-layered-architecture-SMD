package service.impl;

import config.Config;
import dto.ItemDto;
import javafx.collections.ObservableList;
import repository.ItemRepository;
import service.ItemService;

public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository = Config.getItemRepo();

    @Override
    public void addItem(String code, String desc, String size, Double price, Integer qty) {
        itemRepository.addItem(code, desc, size, price, qty);
    }

    @Override
    public void updateItem(String desc, String size, Double price, Integer qty, String code) {
        itemRepository.updateItem(desc, size, price, qty, code);

    }

    @Override
    public void deleteItem(String code) {
        itemRepository.deleteItem(code);
    }

    @Override
    public ItemDto searchItem(String code) {
        return itemRepository.searchItem(code);
    }

    @Override
    public ObservableList<ItemDto> getAllItems() {
        return itemRepository.getAllItems();
    }
}
