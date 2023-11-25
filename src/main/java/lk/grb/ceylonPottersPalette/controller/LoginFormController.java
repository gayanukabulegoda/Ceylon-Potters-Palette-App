package lk.grb.ceylonPottersPalette.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXButton;
import javafx.util.Duration;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private Label lblPasswordAlert;

    @FXML
    private Label lblUserNameAlert;

    @FXML
    private Pane powerOffPane;

    @FXML
    private JFXButton btnLogIn;

    @FXML
    private ImageView imgPowerOff;

    @FXML
    private Hyperlink hyperForgotPassword;

    @FXML
    private Hyperlink hyperSignUp;

    public static String password;

    UserModel userModel = new UserModel();

    @FXML
    void btnLogInOnAction(ActionEvent event) {
        if(validateCredentials()) {
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
    }

    private boolean validateCredentials() {

        boolean userNameValidate = Pattern.matches("([A-Za-z]+)", txtUsername.getText());

        if (!userNameValidate) {
            lblUserNameAlert.setText("Invalid Username!!");
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
    void txtPasswordOnMouseClicked(MouseEvent event) {
        lblPasswordAlert.setText(" ");
    }

    @FXML
    void txtUserNameOnMouseClicked(MouseEvent event) {
        lblUserNameAlert.setText(" ");
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

    @FXML
    void btnLogInOnMouseEntered(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnSelected(btnLogIn);
    }

    @FXML
    void btnLogInOnMouseExited(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnUnselected(btnLogIn);
    }

    @FXML
    void hyperSignUpOnMouseEntered(MouseEvent event) {
        StyleUtil.hyperLinkSelected(hyperSignUp);
    }

    @FXML
    void hyperSignUpOnMouseExited(MouseEvent event) {
        StyleUtil.hyperLinkUnselected(hyperSignUp);
    }

    @FXML
    void hyperForgotPasswordOnMouseEntered(MouseEvent event) {
        StyleUtil.hyperLinkSelected(hyperForgotPassword);
    }

    @FXML
    void hyperForgotPasswordOnMouseExited(MouseEvent event) {
        StyleUtil.hyperLinkForgotPwUnselected(hyperForgotPassword);
    }

    @FXML
    void btnPowerOffOnMouseEntered(MouseEvent event) {
        powerOffPane.setVisible(true);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), powerOffPane);
        transition.setFromX(-powerOffPane.getWidth()); // Slide in from the left
        transition.setToX(0);
        transition.play();

        StyleUtil.powerOffOrBackBtnSelected(imgPowerOff);
    }

    @FXML
    void btnPowerOffOnMouseExited(MouseEvent event) {
        powerOffPane.setVisible(false);
        StyleUtil.powerOffOrBackBtnUnselected(imgPowerOff);
    }
}
