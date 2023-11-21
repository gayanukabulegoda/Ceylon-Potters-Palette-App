package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import lk.grb.ceylonPottersPalette.dto.UserDto;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ResetPasswordFromController {

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private Label lblConfirmPwAlert;

    @FXML
    private Label lblNewPwAlert;

    UserModel userModel = new UserModel();
    EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) throws IOException, SQLException {

        if (validatePassword()) {

            if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {

                UserDto userDto = new UserDto();

                userDto.setPassword(txtConfirmPassword.getText());
                userDto.setUser_Name(userModel.getUserName(OTPVerifyFormController.employeeId));

                if (userModel.update(userDto)) {
                    Navigation.close(event);
                    Navigation.switchNavigation("loginForm.fxml", event);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Unable to Change Your Password! Try again!!");
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password Confirmation!!");
            }
        }
    }

    private boolean validatePassword() {

        boolean newPasswordValidate = Pattern.matches(".{6,25}", txtNewPassword.getText());

        if (!newPasswordValidate) {
            lblNewPwAlert.setText("Unable to Change Your Password! Try again!!");
            return false;
        }

        boolean confirmPasswordValidate = Pattern.matches(".{6,25}", txtConfirmPassword.getText());

        if (!confirmPasswordValidate) {
            lblConfirmPwAlert.setText("Invalid Password Confirmation!!");
            return false;
        }
        return true;
    }

    @FXML
    void confirmPasswordOnMouseClicked(MouseEvent event) {
        lblConfirmPwAlert.setText(" ");
    }

    @FXML
    void newPasswordOnMouseClicked(MouseEvent event) {
        lblNewPwAlert.setText(" ");
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("loginForm.fxml", event);
    }
}
