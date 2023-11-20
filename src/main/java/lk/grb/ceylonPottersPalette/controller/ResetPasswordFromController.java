package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;

public class ResetPasswordFromController {

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml", event);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml", event);
    }
}
