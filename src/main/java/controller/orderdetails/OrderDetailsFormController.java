package controller.orderdetails;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderDetailsFormController {

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableView<?> tblOrders;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private DatePicker txtOrderDate;

    @FXML
    private TextField txtOrderID;


    @FXML
    void btnSearchOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOrderOnAction(ActionEvent event) {

    }

}
