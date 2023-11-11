package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import lk.grb.ceylonPottersPalette.util.DateTimeUtil;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GlobalFormController implements Initializable {
    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnSales;

    @FXML
    private JFXButton btnStock;

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private ImageView imgCustomer;

    @FXML
    private ImageView imgDashboard;

    @FXML
    private ImageView imgEmployee;

    @FXML
    private ImageView imgLogOut;

    @FXML
    public ImageView imgPopUpBackground;

    @FXML
    private ImageView imgSales;

    @FXML
    private ImageView imgStock;

    @FXML
    private ImageView imgSupplier;

    @FXML
    private Label lblCustomer;

    @FXML
    private Label lblDashboard;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEmployee;

    @FXML
    private Label lblLogOut;

    @FXML
    private Label lblSales;

    @FXML
    private Label lblStock;

    @FXML
    private Label lblSupplier;

    @FXML
    private Label lblTime;

    @FXML
    public Pane pagingPane;

    @FXML
    private Pane paneBtnCustomer;

    @FXML
    private Pane paneBtnDashboard;

    @FXML
    private Pane paneBtnEmployee;

    @FXML
    private Pane paneBtnLogOut;

    @FXML
    private Pane paneBtnSales;

    @FXML
    private Pane paneBtnStock;

    @FXML
    private Pane paneBtnSupplier;

    @FXML
    public Pane popUpPane;

    @FXML
    public Pane orderPopUpPane;

    private static GlobalFormController controller;

    public GlobalFormController() {
        controller = this;
    }

    public static GlobalFormController getInstance() {
        return controller;
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        btnUnselected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon.png");
        btnUnselected(paneBtnSales, lblSales, imgSales, "salesIcon.png");
        btnUnselected(paneBtnStock, lblStock, imgStock, "stockIcon.png");
        btnSelected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon2.png");
        btnUnselected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon.png");
        btnUnselected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon.png");

        Navigation.switchPaging(pagingPane, "customerManageForm.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        btnSelected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon2.png");
        btnUnselected(paneBtnSales, lblSales, imgSales, "salesIcon.png");
        btnUnselected(paneBtnStock, lblStock, imgStock, "stockIcon.png");
        btnUnselected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon.png");
        btnUnselected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon.png");
        btnUnselected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon.png");

        Navigation.switchPaging(pagingPane, "dashboardForm.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        btnUnselected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon.png");
        btnUnselected(paneBtnSales, lblSales, imgSales, "salesIcon.png");
        btnUnselected(paneBtnStock, lblStock, imgStock, "stockIcon.png");
        btnUnselected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon.png");
        btnSelected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon2.png");
        btnUnselected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon.png");

        Navigation.switchPaging(pagingPane, "employeeManageForm.fxml");
    }

    @FXML
    void btnSalesOnAction(ActionEvent event) throws IOException {
        btnUnselected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon.png");
        btnSelected(paneBtnSales, lblSales, imgSales, "salesIcon2.png");
        btnUnselected(paneBtnStock, lblStock, imgStock, "stockIcon.png");
        btnUnselected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon.png");
        btnUnselected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon.png");
        btnUnselected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon.png");

        Navigation.switchPaging(pagingPane, "customerOrderManageForm.fxml");
    }

    @FXML
    void btnStockOnAction(ActionEvent event) throws IOException {
        btnUnselected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon.png");
        btnUnselected(paneBtnSales, lblSales, imgSales, "salesIcon.png");
        btnSelected(paneBtnStock, lblStock, imgStock, "stockIcon2.png");
        btnUnselected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon.png");
        btnUnselected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon.png");
        btnUnselected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon.png");

        Navigation.switchPaging(pagingPane, "productStockForm.fxml");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        btnUnselected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon.png");
        btnUnselected(paneBtnSales, lblSales, imgSales, "salesIcon.png");
        btnUnselected(paneBtnStock, lblStock, imgStock, "stockIcon.png");
        btnUnselected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon.png");
        btnUnselected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon.png");
        btnSelected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon2.png");

        Navigation.switchPaging(pagingPane, "supplierManageForm.fxml");
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        logoutBtnSelected(paneBtnLogOut, lblLogOut, imgLogOut, "logoutIcon.png");
        Navigation.switchNavigation("loginForm.fxml", event);
    }

    @FXML
    void btnLogOutOnMouseEntered(MouseEvent event) {
        logoutBtnSelected(paneBtnLogOut, lblLogOut, imgLogOut, "logoutIcon.png");
    }

    @FXML
    void btnLogOutOnMouseExited(MouseEvent event) {
        btnUnselected(paneBtnLogOut, lblLogOut, imgLogOut, "logoutIcon2.png");
    }

    @FXML
    void btnCustomerOnMouseEntered(MouseEvent event) {
        allBtnHoverCss(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon2.png");
    }

    @FXML
    void btnCustomerOnMouseExited(MouseEvent event) {
        btnUnselected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon.png");
    }

    @FXML
    void btnDashboardOnMouseEntered(MouseEvent event) {
        allBtnHoverCss(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon2.png");
    }

    @FXML
    void btnDashboardOnMouseExited(MouseEvent event) {
        btnUnselected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon.png");
    }

    @FXML
    void btnEmployeeOnMouseEntered(MouseEvent event) {
        allBtnHoverCss(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon2.png");
    }

    @FXML
    void btnEmployeeOnMouseExited(MouseEvent event) {
        btnUnselected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon.png");
    }

    @FXML
    void btnSalesOnMouseEntered(MouseEvent event) {
        allBtnHoverCss(paneBtnSales, lblSales, imgSales, "salesIcon2.png");
    }

    @FXML
    void btnSalesOnMouseExited(MouseEvent event) {
        btnUnselected(paneBtnSales, lblSales, imgSales, "salesIcon.png");
    }

    @FXML
    void btnStockOnMouseEntered(MouseEvent event) {
        allBtnHoverCss(paneBtnStock, lblStock, imgStock, "stockIcon2.png");
    }

    @FXML
    void btnStockOnMouseExited(MouseEvent event) {
        btnUnselected(paneBtnStock, lblStock, imgStock, "stockIcon.png");
    }

    @FXML
    void btnSupplierOnMouseEntered(MouseEvent event) {
        allBtnHoverCss(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon2.png");
    }

    @FXML
    void btnSupplierOnMouseExited(MouseEvent event) {
        btnUnselected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon.png");
    }

    void btnSelected(Pane pane, Label label, ImageView imageView, String path) {
        pane.setStyle(
                "-fx-background-color: #FFDDC5;" +
                        "-fx-background-radius: 12px;");
        label.setStyle("-fx-text-fill: #885D40;" +
                "-fx-font-size: 16px");
        imageView.setImage(new Image("assests/icon/" + path));
    }

    void btnUnselected(Pane pane, Label label, ImageView imageView, String path) {
        pane.setStyle(
                "-fx-background-radius: 12px;");
        label.setStyle("-fx-font-weight: 500;" +
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #A3A3A3;");
        imageView.setImage(new Image("assests/icon/" + path));
    }

    void logoutBtnSelected(Pane pane, Label label, ImageView imageView, String path) {
        pane.setStyle(
                "-fx-background-color: #FFE1E1;" +
                        "-fx-background-radius: 12px;");
        label.setStyle("-fx-text-fill: #FF2626;" +
                "-fx-font-size: 16px");
        imageView.setImage(new Image("assests/icon/" + path));
    }

    void allBtnHoverCss(Pane pane, Label label, ImageView imageView, String path){
        pane.setStyle(
                "-fx-background-color: #E8E8E8;" +
                        "-fx-background-radius: 12px;");
        label.setStyle("-fx-text-fill: #885D40;" +
                "-fx-font-size: 16px");
        imageView.setImage(new Image("assests/icon/" + path));
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSelected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon2.png");
        try {
            Navigation.switchPaging(pagingPane, "dashboardForm.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        lblTime.setText(DateTimeUtil.timeNow());
        lblDate.setText(DateTimeUtil.dateNow());
    }
}
