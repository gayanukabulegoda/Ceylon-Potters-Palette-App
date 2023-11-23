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
import javafx.scene.input.MouseEvent;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

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

        if (validateCustomer()) {

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

        boolean nameValidate = Pattern.matches("[A-Za-z\\s]{3,12}", txtCustomerName.getText());

        if (!nameValidate) {
            lblCustomerNameAlert.setText("Invalid Customer Name!!");
            return false;
        }

        boolean contactNoValidate = Pattern.matches("([0]\\d{1,9})", txtContactNo.getText());

        if (!contactNoValidate) {
            lblContactNoAlert.setText("Invalid Contact Number!!");
            return false;
        }

        boolean emailValidate = Pattern.matches("([A-Za-z0-9]{3,}@[A-Za-z]{3,}\\.[A-Za-z]{1,})", txtCustomerEmail.getText());

        if (!emailValidate) {
            lblCustomerEmailAlert.setText("Invalid Email Address!!");

            return false;
        }
        return true;
    }

    @FXML
    void contactNoOnMouseClick(MouseEvent event) {
        lblContactNoAlert.setText(" ");
    }

    @FXML
    void customerNameOnMouseClick(MouseEvent event) {
        lblCustomerNameAlert.setText(" ");
    }

    @FXML
    void emailOnMouseClick(MouseEvent event) {
        lblCustomerEmailAlert.setText(" ");
    }

    @FXML
    void btnCloseIconOnMouseEntered(MouseEvent event) {
        StyleUtil.closeIconBtnSelected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCloseIconOnMouseExited(MouseEvent event) {
        StyleUtil.closeIconBtnUnselected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        StyleUtil.cancelBtnSelected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        StyleUtil.cancelBtnUnselected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(AddBtnPane);
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(AddBtnPane);
    }
}
