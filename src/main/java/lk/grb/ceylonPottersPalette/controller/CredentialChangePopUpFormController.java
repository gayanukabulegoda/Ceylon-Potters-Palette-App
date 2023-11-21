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
import java.util.regex.Pattern;

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

    @FXML
    private Label lblConfirmPwAlert;

    @FXML
    private Label lblCurrentPwAlert;

    @FXML
    private Label lblNewPwAlert;

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
                lblConfirmPwAlert.setText(" ");
                userDto.setPassword(txtConfirmPassword.getText());
                LoginFormController.password = txtConfirmPassword.getText();
                updated = userModel.update(userDto);
                txtConfirmPasswordOnAction(event);

                if (updated) {
                    Navigation.closePane();
                }

            } else {
                lblConfirmPwAlert.setText("New Password & it's Confirmation Doesn't Match!!!");
            }
        } else {
            lblCurrentPwAlert.setText("Invalid Password!!");
        }
    }

    @FXML
    void txtCurrentPassword(ActionEvent event) {
        if(LoginFormController.password.equals(txtCurrentPassword.getText())){
            lblCurrentPwAlert.setText(" ");
            txtNewPassword.setEditable(true);
            txtConfirmPassword.setEditable(true);
        }
        else {
            lblCurrentPwAlert.setText("Invalid Password!!");
        }
    }

    @FXML
    void txtConfirmPasswordOnAction(ActionEvent event) {
        if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
            lblConfirmPwAlert.setText(" ");
            btnSave.setVisible(true);
        }
        else {
            lblConfirmPwAlert.setText("New Password & Confirmation Doesn't Match!!");
        }
    }

    @FXML
    void txtNewPasswordOnAction(ActionEvent event) {
        if(validateNewPassword()) {
            lblNewPwAlert.setText(" ");
        }
    }

    private boolean validateNewPassword() {
        String password = txtNewPassword.getText();

        boolean idValidate = Pattern.matches(".{6,25}", password);

        if (!idValidate) {
            lblNewPwAlert.setText("Invalid Password!!\nPassword should contain at least 6 characters");
            return false;
        }
        lblNewPwAlert.setText(" ");
        return true;
    }
}
