package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.model.*;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.sql.SQLException;

public class ConfirmationPopUpFormController {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private Pane confirmBtnPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblConfirm;

    private static String id;

    EmployeeModel employeeModel = new EmployeeModel();
    EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();
    SupplierModel supplierModel = new SupplierModel();
    CustomerModel customerModel = new CustomerModel();
    ProductStockModel productStockModel = new ProductStockModel();
    ItemStockModel itemStockModel = new ItemStockModel();

    public static void setId(String id) {
        ConfirmationPopUpFormController.id = id;
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) throws SQLException {
        if (id.startsWith("E")) {
            boolean delete = employeeModel.delete(id);
            EmployeeManageFormController.getInstance().allEmployeeId();
        } else if (id.startsWith("S")) {
            boolean delete = supplierModel.delete(id);
            SupplierManageFormController.getInstance().allSupplierId();
        } else if (id.startsWith("C")) {
            boolean delete = customerModel.delete(id);
            CustomerManageFormController.getInstance().allCustomerId();
        } else if (id.startsWith("A")) {
            boolean delete = employeeAttendanceModel.delete(id);
            EmployeeAttendanceFormController.getInstance().allAttendanceId();
        } else if (id.startsWith("P")) {
            boolean delete = productStockModel.delete(id);
            ProductStockFormController.getInstance().allProductId();
        } else if (id.startsWith("I")) {
            boolean delete = itemStockModel.delete(id);
            ItemStockFormController.getInstance().allItemId();
        }
        Navigation.closePane();
    }
}
