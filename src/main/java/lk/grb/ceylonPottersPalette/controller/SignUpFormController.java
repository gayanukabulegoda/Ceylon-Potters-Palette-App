package lk.grb.ceylonPottersPalette.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.grb.ceylonPottersPalette.dto.UserDto;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

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

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private ImageView imgBackBtn;

    @FXML
    private Pane backPane;

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

        boolean userNameValidate = Pattern.matches("([A-Za-z]+)", txtUsername.getText());

        if (!userNameValidate) {
            lblUserNameAlert.setText("Enter a valid Username!!");
            return false;
        }

        boolean passwordValidate = Pattern.matches(".{6,25}", txtPassword.getText());

        if (!passwordValidate) {
            lblPasswordAlert.setText("Invalid Password!!");
            return false;
        }
        return true;
    }

    @FXML
    void btnSignUpOnMouseEntered(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnSelected(btnSignUp);
    }

    @FXML
    void btnSignUpOnMouseExited(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnUnselected(btnSignUp);
    }

    @FXML
    void btnBackOnMouseEntered(MouseEvent event) {
        backPane.setVisible(true);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), backPane);
        transition.setFromX(-backPane.getWidth()); // Slide in from the left
        transition.setToX(0);
        transition.play();

        StyleUtil.powerOffOrBackBtnSelected(imgBackBtn);
    }

    @FXML
    void btnBackOnMouseExited(MouseEvent event) {
        backPane.setVisible(false);
        StyleUtil.powerOffOrBackBtnUnselected(imgBackBtn);
    }
}
