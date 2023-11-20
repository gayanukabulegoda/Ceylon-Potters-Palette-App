package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class SignUpFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    UserModel userModel = new UserModel();

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml", event);
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException, SQLException {

        UserDto userDto = new UserDto();

        userDto.setUser_Name(txtUsername.getText());
        userDto.setPassword(txtPassword.getText());
        userDto.setEmployeeId(SignUpOTPVerifyFormController.employeeId);

        boolean saved = userModel.save(userDto);

        if (saved) {
            Navigation.close(event);
            Navigation.switchNavigation("globalForm.fxml", event);
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Oops! Unable to Save Your Data!!").show();
        }
    }
}
