package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SupplierManageFormController implements Initializable {

    @FXML
    private Pane addSupllierPane;

    @FXML
    private ImageView imgAdd;

    @FXML
    private Label lblAddSupplier;

    @FXML
    private Label lblSearchAlert;

    @FXML
    private Pane searchBarPane;

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
    void btnAddSupplierOnMouseEntered(MouseEvent event) {
        StyleUtil.addBtnSelected(addSupllierPane, lblAddSupplier, imgAdd);
    }

    @FXML
    void btnAddSupplierOnMouseExited(MouseEvent event) {
        StyleUtil.addBtnUnselected(addSupllierPane, lblAddSupplier, imgAdd);
    }

    @FXML
    void txtSearchOnMouseClicked(MouseEvent event) {
        lblSearchAlert.setText(" ");
        StyleUtil.searchBarTransparent(searchBarPane);
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!validateId()) {
            lblSearchAlert.setText("Invalid Contact No!!");
            StyleUtil.searchBarRed(searchBarPane);
            return;
        }

        SupplierModel supplierModel = new SupplierModel();
        ArrayList<String> allSupplierId = supplierModel.getAllSupplierId();

        for (int i = 0; i < allSupplierId.size(); i++) {
            if (txtSearch.getText().equals(supplierModel.getSupplierContactNo(allSupplierId.get(i)))) {
                SupplierViewPopUpFormController.supplierId = allSupplierId.get(i);
                Navigation.imgPopUpBackground("supplierViewPopUpForm.fxml");
                lblSearchAlert.setText(" ");
                StyleUtil.searchBarTransparent(searchBarPane);
                txtSearch.clear();
                return;
            }
        }
        lblSearchAlert.setText("Invalid Contact No!!");
        StyleUtil.searchBarRed(searchBarPane);
    }

    private boolean validateId() {
        return Pattern.matches("[0-9]{10}", txtSearch.getText());
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
