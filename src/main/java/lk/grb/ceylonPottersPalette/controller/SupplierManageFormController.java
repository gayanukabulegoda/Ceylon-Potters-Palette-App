package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierManageFormController {

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

    public void allSupplierId() throws SQLException {

        vBoxSupplierManage.getChildren().clear();
        SupplierModel supplierModel = new SupplierModel();
        ArrayList<String> list = supplierModel.getAllSupplierId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
//        try {
//            FXMLLoader loader = new FXMLLoader(SupplierManageFormController.class.getResource("/view/SupplierDetailsBarForm.fxml"));
//            Parent root = loader.load();
//            SupplierDetailsBarFormController controller = loader.getController();
//            controller.setData(id);
//            vBox.getChildren().add(root);
//        } catch (IOException | SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
}
