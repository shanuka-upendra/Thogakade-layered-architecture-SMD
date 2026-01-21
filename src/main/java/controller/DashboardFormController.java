package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    Stage stage = new Stage();

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        stage.setTitle("Customer");
    }

    @FXML
    void btnItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrderDetailsOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {

    }

}
