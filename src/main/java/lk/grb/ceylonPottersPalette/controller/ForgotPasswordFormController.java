package lk.grb.ceylonPottersPalette.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ForgotPasswordFormController {

    @FXML
    private TextField txtUsername;

    @FXML
    private Label lblUserNameAlert;

    @FXML
    private Pane backPane;

    @FXML
    private JFXButton btnResetPassword;

    @FXML
    private ImageView imgBackBtn;

    UserModel userModel = new UserModel();

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("loginForm.fxml",event);
    }

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) throws IOException, SQLException {

        if(validateUserName()) {
            String employeeId = userModel.getEmployeeId(txtUsername.getText());

            if (userModel.getUserName(employeeId).equals(txtUsername.getText())) {

                OTPVerifyFormController.employeeId = employeeId;

                Navigation.close(event);
                Navigation.switchNavigation("OTPVerifyForm.fxml", event);
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid UserName!! Try Again!!").show();
            }
        }
    }

    private boolean validateUserName() {

        boolean userNameValidate = Pattern.matches("([A-Za-z]+)", txtUsername.getText());

        if (!userNameValidate) {
            lblUserNameAlert.setText("Invalid Username!!");
            return false;
        }
        return true;
    }

    @FXML
    void userNameOnMouseClicked(MouseEvent event) {
        lblUserNameAlert.setText(" ");
    }

    @FXML
    void btnResetPasswordOnMouseEntered(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnSelected(btnResetPassword);
    }

    @FXML
    void btnResetPasswordOnMouseExited(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnUnselected(btnResetPassword);
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
