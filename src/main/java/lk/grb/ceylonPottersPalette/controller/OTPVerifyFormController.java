package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;

public class OTPVerifyFormController {

    @FXML
    private TextField txtOTP;

    @FXML
    void btnBackIconOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("forgotPasswordForm.fxml",event);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("forgotPasswordForm.fxml",event);
    }

    @FXML
    void btnVerifyOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("resetPasswordFrom.fxml",event);
    }
}
