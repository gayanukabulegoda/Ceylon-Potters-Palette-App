package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
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
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CustomerOrderAddPopUpFormController implements Initializable {

    @FXML
    private Pane AddToCartBtnPane;

    @FXML
    private ImageView btnAddNewCustomerIconImg;

    @FXML
    private Pane btnAddNewCustomerPane;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbProductId;

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
    private Label lblCmbCustomerIdAlert;

    @FXML
    private Label lblCmbProductIdAlert;

    @FXML
    private Label lblQtyAlert;

    CustomerOrderModel customerOrderModel = new CustomerOrderModel();
    CustomerModel customerModel = new CustomerModel();
    ProductStockModel productStockModel = new ProductStockModel();
    PlaceCustomerOrderModel placeCustomerOrderModel = new PlaceCustomerOrderModel();
    public static ArrayList<String[]> productList;
    ArrayList<String> idList;

    {
        try {
            idList = customerOrderModel.getAllCustomerOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    double netTotal = 0;

    private static CustomerOrderAddPopUpFormController controller;

    public CustomerOrderAddPopUpFormController() {
        controller = this;
        productList = new ArrayList<>();
    }

    public static  CustomerOrderAddPopUpFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.closeOrderPopUpPane();
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "customerManageForm.fxml");
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

        if (validateCustomerOrder()) {

//            for (int i = 0; i < productList.size(); i++) {
//                if (productList.get(i)[0].equals(String.valueOf(cmbProductId.getSelectionModel().getSelectedItem()))) {
//                    productList.get(i)[1] += Integer.parseInt(txtProductQty.getText());
//
//                    return;
//                }
//            }

            String[] products = {String.valueOf(cmbProductId.getSelectionModel().getSelectedItem()), txtProductQty.getText()};

            productList.add(products);

            allCustomerOrderCartId();

            netTotal += (Integer.parseInt(txtProductQty.getText())) * (Double.parseDouble(lblUnitPrice.getText()));
            lblNetTotal.setText(String.valueOf(netTotal));

            txtProductQty.clear();
        }
    }

    private boolean validateCustomerOrder() {

        if ((cmbCustomerId.getSelectionModel().getSelectedItem()) == null) {
            lblCmbCustomerIdAlert.setText("Please Select a Customer!!");
            return false;
        }

        if ((cmbProductId.getSelectionModel().getSelectedItem()) == null) {
            lblCmbProductIdAlert.setText("Please Select a Product!!");
            return false;
        }

        boolean qtyValidate = Pattern.matches("([0-9]+)", txtProductQty.getText());

        if (!qtyValidate) {
            lblQtyAlert.setText("Enter the Product Quantity!!");
            return false;
        }
        return true;
    }

    @FXML
    void cmbProductIdOnMouseClick(MouseEvent event) {
        lblCmbProductIdAlert.setText(" ");
    }

    @FXML
    void txtQtyOnMouseClick(MouseEvent event) {
        lblQtyAlert.setText(" ");
    }

    @FXML
    void cmbCustomerIdOnMouseClick(MouseEvent event) {
        lblCmbCustomerIdAlert.setText(" ");
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closeOrderPopUpPane();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException {

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
            CustomerOrderManageFormController.getInstance().allCustomerOrderId();
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

    @FXML
    void btnPlaceOrderOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(placeOrderPanel);
    }

    @FXML
    void btnPlaceOrderOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(placeOrderPanel);
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
    void btnAddToCartOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(AddToCartBtnPane);
    }

    @FXML
    void btnAddToCartOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(AddToCartBtnPane);
    }

    @FXML
    void btnAddNewCustomerOnMouseEntered(MouseEvent event) {
        StyleUtil.addNewCustomerORSupplierBtnSelected(btnAddNewCustomerPane, btnAddNewCustomerIconImg);
    }

    @FXML
    void btnAddNewCustomerOnMouseExited(MouseEvent event) {
        StyleUtil.addNewCustomerORSupplierBtnUnselected(btnAddNewCustomerPane, btnAddNewCustomerIconImg);
    }

    public void setProductDataInComboBox() throws SQLException {
        ArrayList<String> roles = productStockModel.getAllProductId();
        cmbProductId.getItems().addAll(roles);
    }

    public void allCustomerOrderCartId() {

        vBoxCustomerOrder.getChildren().clear();

        for (int i = 0; i < productList.size(); i++) {
            loadDataTable(productList.get(i));
        }
    }

    private void loadDataTable(String[] id) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerOrderAddPopUpFormController.class.getResource("/view/customerOrderAddPopUpBarForm.fxml"));
            Parent root = loader.load();
            CustomerOrderAddPopUpBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxCustomerOrder.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
