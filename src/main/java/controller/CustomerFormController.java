package controller;


import config.Config;
import dto.CustomerDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.CustomerService;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

//@RequiredArgsConstructor
public class CustomerFormController implements Initializable {

    CustomerService customerService = Config.getService();

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colCustomerTitle;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<CustomerDto> tblCustomers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerTitle;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    @FXML
    private DatePicker dateDOB;

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        String title = txtCustomerTitle.getText();
        String name = txtCustomerName.getText();
        LocalDate DOB = dateDOB.getValue();
        Double salary = Double.valueOf(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();

        customerService.addCustomer(id,title,name,DOB,salary,address,city,province,postalCode);
        loadTableCustomers();
        clearText();

    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {
        String title = txtCustomerTitle.getText();
        String name = txtCustomerName.getText();
        LocalDate DOB = dateDOB.getValue();
        Double salary = Double.valueOf(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();
        String id = txtCustomerId.getText();

        customerService.updateCustomer(title,name,DOB,salary,address,city,province,postalCode,id);
        loadTableCustomers();
        clearText();

    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {
        customerService.deleteCustomer(txtCustomerId.getText());
        loadTableCustomers();
        clearText();

    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
        CustomerDto customer = customerService.searchCustomer(txtCustomerId.getText());

        if(customer != null) {
            setDataToFields(customer);

            for (CustomerDto item : tblCustomers.getItems()){
                if(item.getId().equals(customer.getId())){
                    tblCustomers.getSelectionModel().select(item);
                    tblCustomers.scrollTo(item);
                    break;
                }
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Customer Not Found!").show();
        }

    }

    void loadTableCustomers() {
        tblCustomers.setItems(customerService.getAllCustomers());
    }

    void clearText(){
        txtCustomerId.clear();
        txtCustomerTitle.clear();
        txtCustomerName.clear();
        dateDOB.setValue(null);
        txtSalary.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();

        tblCustomers.getSelectionModel().clearSelection();
        txtCustomerId.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTableCustomers();

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setDataToFields(newValue);
            }
        });
    }

    void setDataToFields(CustomerDto customer) {
        txtCustomerId.setText(String.valueOf(customer.getId()));
        txtCustomerTitle.setText(customer.getTitle());
        txtCustomerName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(String.valueOf(customer.getSalary()));
        txtCity.setText(customer.getCity());
        txtProvince.setText(customer.getProvince());
        txtPostalCode.setText(String.valueOf(customer.getPostalCode()));

        if (customer.getDOB() != null) {
            Object dobValue = customer.getDOB();

            if (dobValue instanceof java.sql.Date) {
                java.sql.Date sqlDate = (java.sql.Date) dobValue;
                dateDOB.setValue(sqlDate.toLocalDate());
            }

            else if (dobValue instanceof String) {
                String dobString = (String) dobValue;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dateDOB.setValue(LocalDate.parse(dobString, formatter));
            }
        }


    }
}
