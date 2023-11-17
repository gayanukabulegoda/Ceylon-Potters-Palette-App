package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.model.CustomerModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerViewPopUpFormController implements Initializable {

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private Pane btnClosePane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblClose;

    @FXML
    private Label lblContactNo;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblRegisteredDate;

    @FXML
    private Label lblRegisteredTime;

    @FXML
    private Label lblUser;

    public static String customerId;

    CustomerModel customerModel = new CustomerModel();

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    public void setData() throws SQLException {

        CustomerDto customerDto = customerModel.getData(customerId);

        lblCustomerId.setText(customerDto.getCustomer_Id());
        lblCustomerName.setText(customerDto.getName());
        lblContactNo.setText(customerDto.getContact_No());
        lblRegisteredDate.setText(customerDto.getDate());
        lblRegisteredTime.setText(customerDto.getTime());
        lblUser.setText(customerDto.getUser_Name());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
