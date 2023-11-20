package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpEmployeeConfirmFormController {

    @FXML
    private TextField txtEmployeeId;

    EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml", event);
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) throws IOException, SQLException {

        if(employeeModel.getEmployeeName(txtEmployeeId.getText()) != null){

            SignUpOTPVerifyFormController.employeeId = txtEmployeeId.getText();

            Navigation.close(event);
            Navigation.switchNavigation("signUpOTPVerifyForm.fxml", event);
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Invalid ID!! Try Again!!").show();
        }
    }

    @FXML
    void hyperLogInOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml", event);
    }
}
