package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierUpdatePopUpFormController implements Initializable {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblUpdate;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtSupplierEmail;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private Pane updateBtnPane;

    @FXML
    private Label lblContactNoAlert;

    @FXML
    private Label lblSupplierEmailAlert;

    @FXML
    private Label lblSupplierNameAlert;

    public static String supplierId;

    SupplierModel supplierModel = new SupplierModel();

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {

        SupplierDto supplierDto = new SupplierDto();

        supplierDto.setSupplier_Id(supplierId);
        supplierDto.setName(txtSupplierName.getText());
        supplierDto.setContact_No(txtContactNo.getText());
        supplierDto.setEmail(txtSupplierEmail.getText());

        boolean updated = supplierModel.update(supplierDto);

        if (updated) {
            Navigation.closePane();
            SupplierManageFormController.getInstance().allSupplierId();
        }
    }

    public void setData() {
        try {
            SupplierDto supplierDto = supplierModel.getData(supplierId);

            txtSupplierName.setText(supplierDto.getName());
            txtContactNo.setText(supplierDto.getContact_No());
            txtSupplierEmail.setText(supplierDto.getEmail());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
