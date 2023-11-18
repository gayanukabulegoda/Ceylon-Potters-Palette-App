package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.model.ProductStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    @FXML
    private Label lblAttendance;

    @FXML
    private Label lblClayStock;

    @FXML
    private Label lblProductTotal;

    @FXML
    private Label lblTodaySales;

    @FXML
    private VBox vBoxOrders;

    ProductStockModel productStockModel = new ProductStockModel();

    @FXML
    void btnChangeCredentialsOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("credentialChangePopUpForm.fxml");
    }

    @FXML
    void btnEmployeePaymentOnAction(ActionEvent event) throws IOException {
        GlobalFormController.getInstance().btnEmployeeOnAction(event);
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeSalaryForm.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int qtyTotal = 0;

        try {
            ArrayList<String> allProductId = productStockModel.getAllProductId();

            for (int i = 0; i < allProductId.size(); i++) {
                qtyTotal += Integer.parseInt(productStockModel.getQtyTotal(allProductId.get(i)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lblProductTotal.setText(String.valueOf(qtyTotal));
    }
}
