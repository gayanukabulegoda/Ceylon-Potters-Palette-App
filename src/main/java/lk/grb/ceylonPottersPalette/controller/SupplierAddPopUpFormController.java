package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
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
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

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

    @FXML
    private Label lblContactNoAlert;

    @FXML
    private Label lblSupplierEmailAlert;

    @FXML
    private Label lblSupplierNameAlert;

    SupplierModel supplierModel = new SupplierModel();
    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {

        if(validateSupplier()) {
            SupplierDto supplierDto = new SupplierDto();

            ArrayList<String> list = supplierModel.getAllSupplierId();

            supplierDto.setSupplier_Id(NewId.newId(list, NewId.GetType.SUPPLIER));
            supplierDto.setName(txtSupplierName.getText());
            supplierDto.setEmail(txtSupplierEmail.getText());
            supplierDto.setContact_No(txtContactNo.getText());
            supplierDto.setTime(DateTimeUtil.timeNow());
            supplierDto.setDate(DateTimeUtil.dateNow());
            supplierDto.setUser_Name(GlobalFormController.user);

            boolean saved = supplierModel.save(supplierDto);

            if (saved) {
                Navigation.closePane();
                SupplierManageFormController.getInstance().allSupplierId();
            }
        }
    }

    private boolean validateSupplier() {

        boolean nameValidate = Pattern.matches("[A-Za-z\\s]{3,12}", txtSupplierName.getText());

        if (!nameValidate) {
            lblSupplierNameAlert.setText("Invalid Supplier Name!!");
            return false;
        }

        boolean contactNoValidate = Pattern.matches("([0]\\d{1,9})", txtContactNo.getText());

        if (!contactNoValidate) {
            lblContactNoAlert.setText("Invalid Contact Number!!");
            return false;
        }

        boolean emailValidate = Pattern.matches("([A-Za-z0-9]{3,}@[A-Za-z]{3,}\\.[A-Za-z]{1,})", txtSupplierEmail.getText());

        if (!emailValidate) {
            lblSupplierEmailAlert.setText("Invalid Email Address!!");
            return false;
        }
        return true;
    }

    @FXML
    void txtContactNoOnMouseClicked(MouseEvent event) {
        lblContactNoAlert.setText(" ");
    }

    @FXML
    void txtSupplierEmailOnMouseClicked(MouseEvent event) {
        lblSupplierEmailAlert.setText(" ");
    }

    @FXML
    void txtSupplierNameOnMouseClicked(MouseEvent event) {
        lblSupplierNameAlert.setText(" ");
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
    void btnAddOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(AddBtnPane);
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(AddBtnPane);
    }

    @FXML
    void btnCloseIconOnMouseEntered(MouseEvent event) {
        StyleUtil.closeIconBtnSelected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCloseIconOnMouseExited(MouseEvent event) {
        StyleUtil.closeIconBtnUnselected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        StyleUtil.cancelBtnSelected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        StyleUtil.cancelBtnUnselected(cancelBtnPane, lblCancel);
    }
}
