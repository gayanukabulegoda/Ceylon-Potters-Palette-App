package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class ForgotPasswordFormController {

    @FXML
    private TextField txtUsername;

    UserModel userModel = new UserModel();

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("loginForm.fxml",event);
    }

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) throws IOException, SQLException {

        String employeeId = userModel.getEmployeeId(txtUsername.getText());

        if(userModel.getUserName(employeeId).equals(txtUsername.getText())) {

            OTPVerifyFormController.employeeId = employeeId;

            Navigation.close(event);
            Navigation.switchNavigation("OTPVerifyForm.fxml", event);
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Invalid UserName!! Try Again!!").show();
        }
    }
}
