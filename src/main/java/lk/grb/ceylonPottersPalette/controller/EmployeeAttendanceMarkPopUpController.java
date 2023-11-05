package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EmployeeAttendanceMarkPopUpController {

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private JFXButton btnMarkAttendance;

    @FXML
    private Pane closeIconPane;

    @FXML
    private JFXComboBox<?> cmbEmployeeId;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblMarkAttendance;

    @FXML
    private Pane markAttendaceBtnPane;

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {

    }

    @FXML
    void btnMarkAttendanceOnAction(ActionEvent event) {

    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {

    }
}
