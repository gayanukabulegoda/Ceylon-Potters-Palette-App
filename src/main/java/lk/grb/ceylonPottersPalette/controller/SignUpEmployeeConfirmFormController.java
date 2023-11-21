package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class SignUpEmployeeConfirmFormController {

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private Label lblEmployeeIdAlert;

    EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml", event);
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) throws IOException, SQLException {
        if(validateEmployeeId()) {
            if (employeeModel.getEmployeeName(txtEmployeeId.getText()) != null) {

                SignUpOTPVerifyFormController.employeeId = txtEmployeeId.getText();

                Navigation.close(event);
                Navigation.switchNavigation("signUpOTPVerifyForm.fxml", event);
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid ID!! Try Again!!").show();
            }
        }
    }

    private boolean validateEmployeeId() {

        boolean employeeIdValidate = Pattern.matches("(E-00)\\d+", txtEmployeeId.getText());

        if (!employeeIdValidate) {
            lblEmployeeIdAlert.setText("Invalid ID!! Try Again!!");
            return false;
        }
        return true;
    }

    @FXML
    void txtEmployeeIdOnMouseClicked(MouseEvent event) {
        lblEmployeeIdAlert.setText(" ");
    }

    @FXML
    void hyperLogInOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml", event);
    }
}
