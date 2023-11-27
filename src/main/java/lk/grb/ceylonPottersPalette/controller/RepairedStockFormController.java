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
import lk.grb.ceylonPottersPalette.model.ProductStockModel;
import lk.grb.ceylonPottersPalette.model.RepairStockModel;
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RepairedStockFormController implements Initializable {

    @FXML
    private Pane btnItemStockPane;

    @FXML
    private Pane btnProductStockPane;

    @FXML
    private Pane btnRepairStockPane;

    @FXML
    private Label lblItemStock;

    @FXML
    private Label lblProductStock;

    @FXML
    private Label lblRepairStock;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxRepairStock;

    private static RepairedStockFormController controller;

    public RepairedStockFormController() {
        controller = this;
    }

    public static RepairedStockFormController getInstance() {
        return controller;
    }

    @FXML
    void btnItemStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "itemStockForm.fxml");
    }

    @FXML
    void btnProductStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "productStockForm.fxml");
    }

    @FXML
    void btnRepairStockOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!validateName()) {
            new Alert(Alert.AlertType.ERROR, "Wrong Name! Try again!!").show();
            return;
        }

        ProductStockModel productStockModel = new ProductStockModel();
        ArrayList<String> allProductId = productStockModel.getAllProductId();

        for (int i = 0; i < allProductId.size(); i++) {
            if (txtSearch.getText().equals(productStockModel.getDescription(allProductId.get(i)))) {
                ProductViewPopUpFormController.productId = allProductId.get(i);
                txtSearch.clear();
                Navigation.imgPopUpBackground("productViewPopUpForm.fxml");
                return;
            }
        }
        new Alert(Alert.AlertType.ERROR, "Wrong Name! Try again!!").show();
    }

    private boolean validateName() {
        return Pattern.matches("[A-Za-z\\s]{3,}", txtSearch.getText());
    }

    public void allRepairedProductId() throws SQLException {

        vBoxRepairStock.getChildren().clear();
        ProductStockModel productStockModel = new ProductStockModel();
        ArrayList<String> list = productStockModel.getAllProductId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(RepairedStockFormController.class.getResource("/view/repairedStockBarForm.fxml"));
            Parent root = loader.load();
            RepairedStockBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxRepairStock.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allRepairedProductId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
