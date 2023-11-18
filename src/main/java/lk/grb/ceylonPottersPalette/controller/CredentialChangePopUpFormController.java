package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.dto.UserDto;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.util.DateTimeUtil;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.NewId;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CredentialChangePopUpFormController {

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
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblSave;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private PasswordField txtCurrentPassword;

    @FXML
    private PasswordField txtNewPassword;

    UserModel userModel = new UserModel();

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        UserDto userDto = new UserDto();
        boolean updated;
        userDto.setUser_Name(GlobalFormController.user);

        String currentPassword = txtCurrentPassword.getText();

        if (LoginFormController.password.equals(currentPassword)) {

            txtCurrentPassword(event);

            String newPassword = txtNewPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();

            if (confirmPassword.equals(newPassword)) {
                userDto.setPassword(txtConfirmPassword.getText());
                LoginFormController.password = txtConfirmPassword.getText();
                updated = userModel.update(userDto);
                txtConfirmPasswordOnAction(event);

                if (updated) {
                    Navigation.closePane();
                }

            } else {
                new Alert(Alert.AlertType.ERROR, "New Password & it's Confirmation Doesn't Match!!!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Your Current Password is Invalid!!!").show();
        }
    }

    @FXML
    void txtCurrentPassword(ActionEvent event) {
        txtNewPassword.setEditable(true);
        txtConfirmPassword.setEditable(true);
    }

    @FXML
    void txtConfirmPasswordOnAction(ActionEvent event) {
        btnSave.setVisible(true);
    }
}
