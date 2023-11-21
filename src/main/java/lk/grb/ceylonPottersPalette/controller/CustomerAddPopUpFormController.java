package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.model.CustomerModel;
import lk.grb.ceylonPottersPalette.util.DateTimeUtil;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.NewId;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CustomerAddPopUpFormController {

    @FXML
    private Pane AddBtnPane;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblAdd;

    @FXML
    private Label lblCancel;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private Label lblContactNoAlert;

    @FXML
    private Label lblCustomerEmailAlert;

    @FXML
    private Label lblCustomerNameAlert;

    CustomerModel customerModel = new CustomerModel();

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {
        CustomerDto customerDto = new CustomerDto();

        ArrayList<String> list = customerModel.getAllCustomerId();

        customerDto.setCustomer_Id(NewId.newId(list, NewId.GetType.CUSTOMER));
        customerDto.setName(txtCustomerName.getText());
        customerDto.setContact_No(txtContactNo.getText());
        customerDto.setEmail(txtCustomerEmail.getText());
        customerDto.setTime(DateTimeUtil.timeNow());
        customerDto.setDate(DateTimeUtil.dateNow());
        customerDto.setUser_Name(GlobalFormController.user);

        boolean saved = customerModel.save(customerDto);

        if (saved) {
            Navigation.closePane();
            CustomerManageFormController.getInstance().allCustomerId();
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    private boolean validateCustomer() {

        String name = txtCustomerName.getText();

        boolean nameValidate = Pattern.matches("[A-Za-z\\s]+", name);

        if (!nameValidate) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Name!!").show();
            return false;
        }

        String contactNo = txtContactNo.getText();

        boolean contactNoValidate = Pattern.matches("[A-Za-z\\s]+", name);

        if (!nameValidate) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Name!!").show();
            return false;
        }


        return true;
    }
}
