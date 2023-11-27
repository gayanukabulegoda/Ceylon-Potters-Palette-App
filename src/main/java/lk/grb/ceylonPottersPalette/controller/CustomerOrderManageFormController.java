package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.model.CustomerModel;
import lk.grb.ceylonPottersPalette.model.CustomerOrderModel;
import lk.grb.ceylonPottersPalette.model.SupplierOrderModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CustomerOrderManageFormController implements Initializable {
    @FXML
    private Pane btnCustomerOrdersPane;

    @FXML
    private Pane btnSupplierOrdersPane;

    @FXML
    private Label lblCustomerOrders;

    @FXML
    private Label lblSupplierOrders;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxCustomerOrders;

    private static CustomerOrderManageFormController controller;

    public CustomerOrderManageFormController() {
        controller = this;
    }

    public static  CustomerOrderManageFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddOrderOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("customerOrderAddPopUpForm.fxml");
    }

    @FXML
    void btnCustomerOrdersOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshTableOnAction(ActionEvent event) {
        try {
            allCustomerOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSupplierOrdersOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "supplierOrderManageForm.fxml");
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!(validateId() | validateContactNo())) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact No Or Order ID!!").show();
            return;
        }

        CustomerOrderModel customerOrderModel = new CustomerOrderModel();
        CustomerModel customerModel = new CustomerModel();
        ArrayList<String> allCustomerOrderId = customerOrderModel.getAllCustomerOrderId();

        for (int i = 0; i < allCustomerOrderId.size(); i++) {
            if (txtSearch.getText().equals(allCustomerOrderId.get(i))) {
                CustomerOrderViewPopUpFormController.customerOrderId = txtSearch.getText();
                CustomerOrderViewPopUpFormController.customerId = customerOrderModel.getCustomerIdForOrder(txtSearch.getText());
                Navigation.imgPopUpBackground("customerOrderViewPopUpForm.fxml");
                txtSearch.clear();
                return;
            }

            ArrayList<String> customerIds = customerOrderModel.getCustomerId(allCustomerOrderId.get(i));

            for (int j = 0; j < customerIds.size(); j++) {
                if (txtSearch.getText().equals(customerModel.getCustomerContactNo(customerIds.get(j)))) {
                    allSelectedCustomerOrderId(customerIds.get(j));
                    txtSearch.clear();
                    return;
                }
            }
        }
        new Alert(Alert.AlertType.ERROR, "Invalid Contact No Or Order ID!!").show();
    }

    private boolean validateId() {
        return Pattern.matches("(CO-0)\\d+", txtSearch.getText());
    }

    private boolean validateContactNo() {
        return Pattern.matches("[0-9]{10}", txtSearch.getText());
    }

    public void allSelectedCustomerOrderId(String id) throws SQLException {

        vBoxCustomerOrders.getChildren().clear();
        CustomerOrderModel customerOrderModel = new CustomerOrderModel();
        ArrayList<String> list = customerOrderModel.getSelectedAllCustomerOrderId(id);

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    public void allCustomerOrderId() throws SQLException {

        vBoxCustomerOrders.getChildren().clear();
        CustomerOrderModel customerOrderModel = new CustomerOrderModel();
        ArrayList<String> list = customerOrderModel.getAllCustomerOrderId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerOrderManageFormController.class.getResource("/view/customerOrderManageBarForm.fxml"));
            Parent root = loader.load();
            CustomerOrderManageBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxCustomerOrders.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allCustomerOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
