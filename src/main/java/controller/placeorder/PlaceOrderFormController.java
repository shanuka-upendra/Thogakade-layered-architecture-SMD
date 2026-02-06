package controller.placeorder;

import dto.CartDto;
import dto.ItemDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import service.CustomerBo;
import service.ItemBo;
import service.PlaceOrderBo;
import service.impl.CustomerBoImpl;
import service.impl.ItemBoImpl;
import service.impl.PlaceOrderBoImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    PlaceOrderBo placeOrderBo = new PlaceOrderBoImpl();

    ObservableList<CartDto> addCart = FXCollections.observableArrayList();
    Double total = 0.0;

    @FXML
    private ComboBox<String> cmbItems;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableView<CartDto> tblPlaceOrder;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtQunatity;

    @FXML
    public TextField txtItemCode;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        addCart.add(new CartDto(
                txtItemCode.getText(),
                cmbItems.getValue(),
                Integer.parseInt(txtQunatity.getText()),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtDiscount.getText()),
                ((Double.parseDouble(txtUnitPrice.getText())*
                        Integer.parseInt(txtQunatity.getText()))*(
                        100-(Double.parseDouble(txtDiscount.getText())))/100)
        ));
        getTotal(((Double.parseDouble(txtUnitPrice.getText())*
                Integer.parseInt(txtQunatity.getText()))*(
                100-(Double.parseDouble(txtDiscount.getText())))/100));
        clearText();
    }

    void getTotal(Double value){
        total+= value;
        lblNetTotal.setText(String.valueOf(total));
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void cmbItemsOnAction(ActionEvent event){
        txtUnitPrice.setText(String.valueOf(placeOrderBo.getUnitPrice(cmbItems.getValue())));
        txtItemCode.setText(placeOrderBo.getItemCode(cmbItems.getValue()));

    }

    @FXML
    void txtCustomerIdOnKeyPressed(KeyEvent event){
        txtCustomerName.setText(placeOrderBo.getCustomerNameById(txtCustomerId.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        tblPlaceOrder.setItems(addCart);

        ObservableList<ItemDto> getAllItems = null;
        getAllItems = placeOrderBo.getAllItems();
        ObservableList<String> getNameItem = FXCollections.observableArrayList();
            for (ItemDto itemDto : getAllItems){
                getNameItem.add(itemDto.getDescription());
            }
        cmbItems.setItems(getNameItem);
    }

    void clearText(){
        txtItemCode.clear();
        txtUnitPrice.clear();
        txtDiscount.clear();
        txtQunatity.clear();
    }
}
