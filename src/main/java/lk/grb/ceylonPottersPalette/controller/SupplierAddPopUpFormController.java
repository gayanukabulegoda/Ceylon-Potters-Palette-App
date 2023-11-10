package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.util.DateTimeUtil;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.NewId;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierAddPopUpFormController {

    @FXML
    private Pane AddBtnPane;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblAdd;

    @FXML
    private Label lblCancel;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtSupplierEmail;

    @FXML
    private TextField txtSupplierName;

    SupplierModel supplierModel = new SupplierModel();
    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {

        SupplierDto supplierDto = new SupplierDto();

        ArrayList<String> list = supplierModel.getAllSupplierId();

        supplierDto.setSupplier_Id(NewId.newId(list, NewId.GetType.SUPPLIER));
        supplierDto.setName(txtSupplierName.getText());
        supplierDto.setEmail(txtSupplierEmail.getText());
        supplierDto.setContact_No(txtContactNo.getText());
        supplierDto.setTime(DateTimeUtil.timeNow());
        supplierDto.setDate(DateTimeUtil.dateNow());

        boolean save = supplierModel.save(supplierDto);

        SupplierManageFormController.getInstance().allSupplierId();
        Navigation.closePane();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }
}
