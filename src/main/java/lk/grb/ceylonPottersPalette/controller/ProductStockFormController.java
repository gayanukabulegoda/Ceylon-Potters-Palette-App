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
import lk.grb.ceylonPottersPalette.model.ItemStockModel;
import lk.grb.ceylonPottersPalette.model.ProductStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ProductStockFormController implements Initializable {

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
    private VBox vBoxProductStock;

    private static ProductStockFormController controller;

    public ProductStockFormController() {
        controller = this;
    }

    public static ProductStockFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddProductOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("productAddPopUpForm.fxml");
    }

    @FXML
    void btnItemStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "itemStockForm.fxml");
    }

    @FXML
    void btnProductStockOnAction(ActionEvent event) {

    }

    @FXML
    void btnRepairStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "repairedStockForm.fxml");
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!validateId()) {
            new Alert(Alert.AlertType.ERROR, "Invalid Id! Id Should be in the format 'P-001' !!").show();
            return;
        }

        ProductStockModel productStockModel = new ProductStockModel();
        ArrayList<String> allProductId = productStockModel.getAllProductId();

        for (int i = 0; i < allProductId.size(); i++) {
            if (txtSearch.getText().equals(allProductId.get(i))) {
                ProductViewPopUpFormController.productId = txtSearch.getText();
                Navigation.imgPopUpBackground("productViewPopUpForm.fxml");
                return;
            }
        }
        new Alert(Alert.AlertType.ERROR, "Invalid Id! Id Should be in the format 'P-001' !!").show();
    }

    private boolean validateId() {
        return Pattern.matches("(P-00)\\d+", txtSearch.getText());
    }

    public void allProductId() throws SQLException {

        vBoxProductStock.getChildren().clear();
        ProductStockModel productStockModel = new ProductStockModel();
        ArrayList<String> list = productStockModel.getAllProductId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(ProductStockFormController.class.getResource("/view/productStockBarForm.fxml"));
            Parent root = loader.load();
            ProductStockBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxProductStock.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allProductId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
