package lk.grb.ceylonPottersPalette.controller;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import javafx.util.Duration;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.model.*;
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

    @FXML
    private ImageView imgFlower1;

    @FXML
    private ImageView imgFlower2;

    @FXML
    private ImageView imgFlower3;

    @FXML
    private Label lblCustomerNo;

    @FXML
    private Label lblSupplierNo;

    @FXML
    private Pane piChartPane;

    @FXML
    private PieChart pieChart;

    ProductStockModel productStockModel = new ProductStockModel();
    ItemStockModel itemStockModel = new ItemStockModel();
    EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();
    CustomerOrderModel customerOrderModel = new CustomerOrderModel();
    CustomerModel customerModel = new CustomerModel();
    SupplierModel supplierModel = new SupplierModel();
    SupplierOrderModel supplierOrderModel = new SupplierOrderModel();
    EmployeeSalaryModel employeeSalaryModel = new EmployeeSalaryModel();

    @FXML
    void btnChangeCredentialsOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("credentialChangePopUpForm.fxml");
    }

    @FXML
    void btnEmployeePaymentOnAction(ActionEvent event) throws IOException {
        GlobalFormController.getInstance().btnEmployeeOnAction(event);
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeSalaryForm.fxml");
    }

    @FXML
    void btnCustomersOnAction(ActionEvent event) throws IOException {
        GlobalFormController.getInstance().btnCustomerOnAction(event);
    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) throws IOException {
        GlobalFormController.getInstance().btnSupplierOnAction(event);
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

    public void start() {
        imgFlower1.setCache(true);
        imgFlower1.setCacheHint(CacheHint.SPEED);

        imgFlower2.setCache(true);
        imgFlower2.setCacheHint(CacheHint.SPEED);

        imgFlower3.setCache(true);
        imgFlower3.setCacheHint(CacheHint.SPEED);

        // Create a RotateTransition for continuous rotation
        RotateTransition rotateTransition1 = new RotateTransition(Duration.seconds(8), imgFlower1);
        rotateTransition1.setInterpolator(Interpolator.LINEAR);
        rotateTransition1.setByAngle(360); // Rotate by 360 degrees
        rotateTransition1.setCycleCount(RotateTransition.INDEFINITE); // Repeat indefinitely
        rotateTransition1.play();

        RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(8), imgFlower2);
        rotateTransition2.setInterpolator(Interpolator.LINEAR);
        rotateTransition2.setByAngle(-360); // Rotate by 360 degrees
        rotateTransition2.setCycleCount(RotateTransition.INDEFINITE); // Repeat indefinitely
        rotateTransition2.play();

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(8), imgFlower3);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setByAngle(-360); // Rotate by 360 degrees
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE); // Repeat indefinitely
        rotateTransition.play();
    }

    public void setPiChart() throws SQLException {

        //double savings = (customerOrderModel.getOrderTotal()) - (supplierOrderModel.getOrderTotal() + employeeSalaryModel.getSalaryTotal());

        // Create sample data for the pie chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Total Income", customerOrderModel.getOrderTotal()),
                new PieChart.Data("Total Expenses", (supplierOrderModel.getOrderTotal() + employeeSalaryModel.getSalaryTotal()))
              //  new PieChart.Data("Total Savings", (savings))
//                new PieChart.Data("Category 4", 15)
        );

        // Create a pie chart with the data
        pieChart = new PieChart(pieChartData);

        pieChart.getData().get(0).getNode().setStyle("-fx-pie-color: #C56E33;");
        pieChart.getData().get(1).getNode().setStyle("-fx-pie-color: #973F04;");
     //   pieChart.getData().get(2).getNode().setStyle("-fx-pie-color: #727374;");

        pieChart.setLabelLineLength(0);
        pieChart.setLabelsVisible(false);

        piChartPane.getChildren().add(pieChart);
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

        try {
            lblCustomerNo.setText("00" + customerModel.getCustomerCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            lblSupplierNo.setText("00" + supplierModel.getSupplierCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        start();

        try {
            setPiChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
