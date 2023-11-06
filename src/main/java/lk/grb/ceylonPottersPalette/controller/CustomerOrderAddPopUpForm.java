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

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

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
