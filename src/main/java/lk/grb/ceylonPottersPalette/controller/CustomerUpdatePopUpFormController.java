package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.model.CustomerModel;
import lk.grb.ceylonPottersPalette.util.DateTimeUtil;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.NewId;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerUpdatePopUpFormController implements Initializable {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblUpdate;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private Pane updateBtnPane;

    @FXML
    private Label lblContactNoAlert;

    @FXML
    private Label lblCustomerEmailAlert;

    @FXML
    private Label lblCustomerNameAlert;

    public static String customerId;

    CustomerModel customerModel = new CustomerModel();

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {

        CustomerDto customerDto = new CustomerDto();

        customerDto.setCustomer_Id(CustomerUpdatePopUpFormController.customerId);
        customerDto.setName(txtCustomerName.getText());
        customerDto.setContact_No(txtContactNo.getText());
        customerDto.setEmail(txtCustomerEmail.getText());
        customerDto.setDate(customerDto.getDate());
        customerDto.setTime(customerDto.getTime());
        customerDto.setUser_Name(customerDto.getUser_Name());

        boolean updated = customerModel.update(customerDto);

        if (updated) {
            Navigation.closePane();
            CustomerManageFormController.getInstance().allCustomerId();
        }
    }

    public void setData() {
        try {

            CustomerDto customerDto = customerModel.getData(customerId);

            txtCustomerName.setText(customerDto.getName());
            txtContactNo.setText(customerDto.getContact_No());
            txtCustomerEmail.setText(customerDto.getEmail());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
