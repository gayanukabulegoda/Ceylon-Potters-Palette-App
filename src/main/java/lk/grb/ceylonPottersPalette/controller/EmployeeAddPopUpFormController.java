package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EmployeeAddPopUpFormController {

    @FXML
    private Pane SaveBtnPane;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private JFXButton btnSave;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private JFXComboBox<?> cmbRole;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblSave;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtHouseNo;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtStreet;

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void cmbRoleOnAction(ActionEvent event) {

    }
}
