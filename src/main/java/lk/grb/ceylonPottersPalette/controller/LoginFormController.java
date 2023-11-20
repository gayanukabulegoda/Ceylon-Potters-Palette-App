package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    public static String password;

    UserModel userModel = new UserModel();

    @FXML
    void btnLogInOnAction(ActionEvent event) {

        try {
            String username = userModel.checkUsernameAndPassword(txtUsername.getText(), txtPassword.getText());

            if (username.equals(txtUsername.getText())) {
                GlobalFormController.user = txtUsername.getText();

                password = txtPassword.getText();
                Navigation.switchNavigation("globalForm.fxml", event);
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Username Or Password!!").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPowerOffOnAction(ActionEvent event) {
        Navigation.exit();
    }

    @FXML
    void hyperForgotPasswordOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("forgotPasswordForm.fxml",event);
    }

    @FXML
    void hyperSignUpOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("signUpEmployeeConfirmForm.fxml", event);
    }
}
