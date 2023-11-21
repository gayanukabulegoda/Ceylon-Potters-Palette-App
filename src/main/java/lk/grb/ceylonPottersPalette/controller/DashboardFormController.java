package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.model.CustomerOrderModel;
import lk.grb.ceylonPottersPalette.model.EmployeeAttendanceModel;
import lk.grb.ceylonPottersPalette.model.ItemStockModel;
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
    ItemStockModel itemStockModel = new ItemStockModel();
    EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();
    CustomerOrderModel customerOrderModel = new CustomerOrderModel();

    @FXML
    void btnChangeCredentialsOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("credentialChangePopUpForm.fxml");
    }

    @FXML
    void btnEmployeePaymentOnAction(ActionEvent event) throws IOException {
        GlobalFormController.getInstance().btnEmployeeOnAction(event);
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeSalaryForm.fxml");
    }

    public void allOrderId() throws SQLException {

        vBoxOrders.getChildren().clear();
        CustomerOrderModel customerOrderModel1 = new CustomerOrderModel();
        ArrayList<String> list = customerOrderModel1.getAllCustomerOrderId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(DashboardFormController.class.getResource("/view/dashboardOrderBarForm.fxml"));
            Parent root = loader.load();
            DashboardOrderBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxOrders.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int qtyTotal = 0;

        try {
            ArrayList<String> allProductId = productStockModel.getAllProductId();

            for (int i = 0; i < allProductId.size(); i++) {
                qtyTotal = Integer.parseInt(productStockModel.getQtyTotal(allProductId.get(i)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lblProductTotal.setText(String.valueOf(qtyTotal));

        try {
            lblAttendance.setText("0" + employeeAttendanceModel.getTodayAttendance());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            lblClayStock.setText(itemStockModel.getAvailableClayStock() + "kg");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            lblTodaySales.setText("0" + customerOrderModel.getAllSales());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            allOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
