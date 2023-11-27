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
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.model.SupplierOrderModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SupplierOrderManageFormController implements Initializable {

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
    private VBox vBoxSupplierOrders;

    private static SupplierOrderManageFormController controller;

    public SupplierOrderManageFormController() {
        controller = this;
    }

    public static  SupplierOrderManageFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddOrderOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("supplierOrderAddPopUpForm.fxml");
    }

    @FXML
    void btnCustomerOrdersOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "customerOrderManageForm.fxml");
    }

    @FXML
    void btnSupplierOrdersOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshTableOnAction(ActionEvent event) {
        try {
            allSupplierOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!(validateId() | validateContactNo())) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact No Or Order ID!!").show();
            return;
        }

        SupplierOrderModel supplierOrderModel = new SupplierOrderModel();
        ArrayList<String> allSupplierOrderId = supplierOrderModel.getAllSupplierOrderId();
        SupplierModel supplierModel = new SupplierModel();

        for (int i = 0; i < allSupplierOrderId.size(); i++) {
            if (txtSearch.getText().equals(allSupplierOrderId.get(i))) {
                SupplierOrderViewPopUpFormController.supplierOrderId = txtSearch.getText();
                SupplierOrderViewPopUpFormController.supplierId = supplierOrderModel.getSupplierIdForOrder(txtSearch.getText());
                Navigation.imgPopUpBackground("supplierOrderViewPopUpForm.fxml");
                txtSearch.clear();
                return;
            }

            ArrayList<String> supplierIds = supplierOrderModel.getSupplierId(allSupplierOrderId.get(i));

            for (int j = 0; j < supplierIds.size(); j++) {
                if (txtSearch.getText().equals(supplierModel.getSupplierContactNo(supplierIds.get(j)))) {
                    allSelectedSupplierOrderId(supplierIds.get(j));
                    txtSearch.clear();
                    return;
                }
            }
        }
        new Alert(Alert.AlertType.ERROR, "Invalid Contact No Or Order ID!!").show();
    }

    private boolean validateId() {
        return Pattern.matches("(SO-0)\\d+", txtSearch.getText());
    }

    private boolean validateContactNo() {
        return Pattern.matches("[0-9]{10}", txtSearch.getText());
    }

    public void allSelectedSupplierOrderId(String id) throws SQLException {

        vBoxSupplierOrders.getChildren().clear();
        SupplierOrderModel supplierOrderModel = new SupplierOrderModel();
        ArrayList<String> list = supplierOrderModel.getSelectedAllSupplierOrderId(id);

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    public void allSupplierOrderId() throws SQLException {

        vBoxSupplierOrders.getChildren().clear();
        SupplierOrderModel supplierOrderModel = new SupplierOrderModel();
        ArrayList<String> list = supplierOrderModel.getAllSupplierOrderId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(SupplierOrderManageFormController.class.getResource("/view/supplierOrderManageBarForm.fxml"));
            Parent root = loader.load();
            SupplierOrderManageBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxSupplierOrders.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allSupplierOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
