package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.model.ProductStockModel;
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SupplierManageFormController implements Initializable {

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxSupplierManage;

    private static SupplierManageFormController controller;

    public SupplierManageFormController() {
        controller = this;
    }

    public static SupplierManageFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("supplierAddPopUpForm.fxml");
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!validateId()) {
            new Alert(Alert.AlertType.ERROR, "Invalid Id! Id Should be in the format 'S-001' !!").show();
            return;
        }

        SupplierModel supplierModel = new SupplierModel();
        ArrayList<String> allSupplierId = supplierModel.getAllSupplierId();

        for (int i = 0; i < allSupplierId.size(); i++) {
            if (txtSearch.getText().equals(allSupplierId.get(i))) {
                SupplierViewPopUpFormController.supplierId = txtSearch.getText();
                Navigation.imgPopUpBackground("supplierViewPopUpForm.fxml");
                return;
            }
        }
        new Alert(Alert.AlertType.ERROR, "Invalid Id! Id Should be in the format 'S-001' !!").show();
    }

    private boolean validateId() {
        return Pattern.matches("(S-00)\\d+", txtSearch.getText());
    }

    public void allSupplierId() throws SQLException {

        vBoxSupplierManage.getChildren().clear();
        SupplierModel supplierModel = new SupplierModel();
        ArrayList<String> list = supplierModel.getAllSupplierId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(SupplierManageFormController.class.getResource("/view/supplierManageBarForm.fxml"));
            Parent root = loader.load();
            SupplierManageBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxSupplierManage.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allSupplierId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
