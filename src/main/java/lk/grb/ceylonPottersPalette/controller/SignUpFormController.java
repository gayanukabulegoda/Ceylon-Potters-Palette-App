package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.dto.UserDto;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class SignUpFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblPasswordAlert;

    @FXML
    private Label lblUserNameAlert;

    @FXML
    private TextField txtUsername;

    UserModel userModel = new UserModel();

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml", event);
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException, SQLException {

        if (validateCredentials()) {
            UserDto userDto = new UserDto();

            userDto.setUser_Name(txtUsername.getText());
            userDto.setPassword(txtPassword.getText());
            userDto.setEmployeeId(SignUpOTPVerifyFormController.employeeId);

            boolean saved = userModel.save(userDto);

            if (saved) {
                Navigation.close(event);
                Navigation.switchNavigation("globalForm.fxml", event);
            } else {
                new Alert(Alert.AlertType.ERROR, "Oops! Unable to Save Your Data!!").show();
            }
        }
    }

    @FXML
    void txtPasswordOnMouseClicked(MouseEvent event) {
        lblPasswordAlert.setText(" ");
    }

    @FXML
    void txtUserNameOnMouseClicked(MouseEvent event) {
        lblUserNameAlert.setText(" ");
    }

    private boolean validateCredentials() {

        boolean userNameValidate = Pattern.matches("([A-Za-z])", txtUsername.getText());

        if (!userNameValidate) {
            lblUserNameAlert.setText("Invalid!!");
            return false;
        }

        boolean passwordValidate = Pattern.matches(".{6,25}", txtPassword.getText());

        if (!passwordValidate) {
            lblPasswordAlert.setText("Invalid!!");
            return false;
        }
        return true;
    }
}
