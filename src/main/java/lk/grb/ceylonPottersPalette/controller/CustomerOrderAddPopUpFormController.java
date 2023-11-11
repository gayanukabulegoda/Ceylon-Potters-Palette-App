package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.model.*;
import lk.grb.ceylonPottersPalette.util.DateTimeUtil;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.NewId;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerOrderAddPopUpFormController implements Initializable {

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
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbProductId;

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

    CustomerOrderModel customerOrderModel = new CustomerOrderModel();
    CustomerModel customerModel = new CustomerModel();
    ProductStockModel productStockModel = new ProductStockModel();
    PlaceCustomerOrderModel placeCustomerOrderModel = new PlaceCustomerOrderModel();
    ArrayList<String[]> productList = new ArrayList<>();
    ArrayList<String> idList;

    {
        try {
            idList = customerOrderModel.getAllCustomerOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    double netTotal = 0;

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.closeOrderPopUpPane();
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "customerManageForm.fxml");
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

        String[] products = {String.valueOf(cmbProductId.getSelectionModel().getSelectedItem()), txtProductQty.getText()};

        productList.add(products);

        netTotal += (Integer.parseInt(txtProductQty.getText())) * (Double.parseDouble(lblUnitPrice.getText()));
        lblNetTotal.setText(String.valueOf(netTotal));

        txtProductQty.clear();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closeOrderPopUpPane();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

        CustomerOrderDto customerOrderDto = new CustomerOrderDto();

        customerOrderDto.setCustomer_Order_Id(lblOrderId.getText());
        customerOrderDto.setCustomer_Id(cmbCustomerId.getSelectionModel().getSelectedItem());
        customerOrderDto.setTotal_Price(Double.parseDouble(lblNetTotal.getText()));
        customerOrderDto.setDate(DateTimeUtil.dateNow());
        customerOrderDto.setTime(DateTimeUtil.timeNow());
        customerOrderDto.setOrderList(productList);

        boolean isSaved = placeCustomerOrderModel.placeCustomerOrder(customerOrderDto);

        if (isSaved) {
            Navigation.closeOrderPopUpPane();
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Unable to Save the ORDER!!!").show();
        }
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) throws SQLException {
        lblCustomerName.setText(customerModel.getCustomerName(cmbCustomerId.getSelectionModel().getSelectedItem()));
    }

    public void setCustomerDataInComboBox() throws SQLException {
        ArrayList<String> roles = customerModel.getAllCustomerId();
        cmbCustomerId.getItems().addAll(roles);
    }

    @FXML
    void cmbProductIdOnAction(ActionEvent event) throws SQLException {
        lblDescription.setText(productStockModel.getDescription(cmbProductId.getSelectionModel().getSelectedItem()));
        lblUnitPrice.setText(productStockModel.getUnitPrice(cmbProductId.getSelectionModel().getSelectedItem()));
        lblQtyOnHand.setText(productStockModel.getQtyOnHand(cmbProductId.getSelectionModel().getSelectedItem()));
    }

    public void setProductDataInComboBox() throws SQLException {
        ArrayList<String> roles = productStockModel.getAllProductId();
        cmbProductId.getItems().addAll(roles);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblOrderId.setText(NewId.newId(idList, NewId.GetType.CUSTOMER_ORDER));
        lblOrderDate.setText(DateTimeUtil.dateNow());

        try {
            setCustomerDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            setProductDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
