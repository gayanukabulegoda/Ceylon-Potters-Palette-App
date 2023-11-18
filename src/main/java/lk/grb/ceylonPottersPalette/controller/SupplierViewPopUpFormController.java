package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierViewPopUpFormController implements Initializable {

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private Pane btnClosePane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblClose;

    @FXML
    private Label lblContactNo;

    @FXML
    private Label lblRegisteredDate;

    @FXML
    private Label lblRegisteredTime;

    @FXML
    private Label lblSupplierId;

    @FXML
    private Label lblSupplierName;

    @FXML
    private Label lblUser;

    public static String supplierId;

    SupplierModel supplierModel = new SupplierModel();

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    public void setData() throws SQLException {

        SupplierDto supplierDto = supplierModel.getData(supplierId);

        lblSupplierId.setText(supplierDto.getSupplier_Id());
        lblSupplierName.setText(supplierDto.getName());
        lblContactNo.setText(supplierDto.getContact_No());
        lblRegisteredDate.setText(supplierDto.getDate());
        lblRegisteredTime.setText(supplierDto.getTime());
        lblUser.setText(supplierDto.getUser_Name());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
