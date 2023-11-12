package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.model.CustomerModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerManageFormController implements Initializable {

    @FXML
    private TextField txtSearch;

    @FXML
    public VBox vBoxCustomerManage;

    private static CustomerManageFormController controller;

    CustomerModel customerModel = new CustomerModel();

    public CustomerManageFormController() {
        controller = this;
    }

    public static  CustomerManageFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("customerAddPopUpForm.fxml");
    }

    public void allCustomerId() throws SQLException {

        vBoxCustomerManage.getChildren().clear();
        CustomerModel customerModel = new CustomerModel();
        ArrayList<String> list = customerModel.getAllCustomerId();

        for (int i = 0; i < list.size(); i++) {
           loadDataTable(list.get(i));
        }
    }

    public void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerManageFormController.class.getResource("/view/customerManageBarForm.fxml"));
            Parent root = loader.load();
            CustomerManageBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxCustomerManage.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allCustomerId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
