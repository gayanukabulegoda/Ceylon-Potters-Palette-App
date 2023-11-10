package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.model.CustomerModel;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.NewId;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderAddPopUpForm {

    @FXML
    private Pane AddToCartBtnPane;

    @FXML
    private JFXButton btnAddNewCustomer;

    @FXML
    private ImageView btnAddNewCustomerIcon;

    @FXML
    private Pane btnAddNewCustomerPane;

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private JFXComboBox<?> cmbCustomerId;

    @FXML
    private JFXComboBox<?> cmbProductId;

    @FXML
    private Label lblAddToCart;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblPlaceOrder;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Pane placeOrderPanel;

    @FXML
    private TextField txtProductQty;

    @FXML
    private VBox vBoxCustomerOrder;

//    public static CustomerAddPopUpFormController controller;
//    CustomerModel customerModel = new CustomerModel();

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.closeOrderPopUpPane();
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "customerManageForm.fxml");
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closeOrderPopUpPane();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbProductIdOnAction(ActionEvent event) {

    }
}
