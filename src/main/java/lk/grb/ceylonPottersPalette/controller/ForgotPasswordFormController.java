package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;

public class ForgotPasswordFormController {

    @FXML
    private TextField txtUsername;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml",event);
    }

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("OTPVerifyForm.fxml", event);
    }
}
