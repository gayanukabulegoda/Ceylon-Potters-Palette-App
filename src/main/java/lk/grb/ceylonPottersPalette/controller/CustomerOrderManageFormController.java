package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    void btnSupplierOrdersOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "supplierOrderManageForm.fxml");
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
