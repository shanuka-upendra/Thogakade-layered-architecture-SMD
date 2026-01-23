package controller;

import config.Config;
import dto.ItemDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ItemService;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    ItemService itemService = Config.getItemSev();

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<ItemDto> tblItems;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtPackSize;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        String code = txtItemCode.getText();
        String desc = txtDesc.getText();
        String packSize = txtPackSize.getText();
        Double price = Double.valueOf(txtUnitPrice.getText());
        Integer qty = Integer.valueOf(txtQty.getText());

        itemService.addItem(code,desc,packSize,price,qty);
        loadItemTable();
        clearText();

    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {
        itemService.deleteItem(txtItemCode.getText());
        loadItemTable();
        clearText();
    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {
        ItemDto item = itemService.searchItem(txtItemCode.getText());

        if(item != null){
            setDataToFields(item);

            for (ItemDto items : tblItems.getItems()){
                if(items.getItemCode().equals(item.getItemCode())){
                    tblItems.getSelectionModel().select(items);
                    tblItems.scrollTo(items);
                    break;
                }
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Item Not Found!").show();
        }

    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        String code = txtItemCode.getText();
        String desc = txtDesc.getText();
        String packSize = txtPackSize.getText();
        Double price = Double.valueOf(txtUnitPrice.getText());
        Integer qty = Integer.valueOf(txtQty.getText());

        itemService.updateItem(desc,packSize,price,qty,code);

    }

    void loadItemTable(){
        tblItems.setItems(itemService.getAllItems());
    }

    void setDataToFields(ItemDto item){
        txtItemCode.setText(item.getItemCode());
        txtDesc.setText(item.getDescription());
        txtPackSize.setText(item.getPackSize());
        txtUnitPrice.setText(String.valueOf(item.getPrice()));
        txtQty.setText(String.valueOf(item.getQty()));
    }

    void clearText(){
        txtItemCode.clear();
        txtDesc.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQty.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadItemTable();
    }
}
