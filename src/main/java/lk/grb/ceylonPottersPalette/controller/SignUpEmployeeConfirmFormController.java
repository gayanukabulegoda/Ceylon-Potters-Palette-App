package lk.grb.ceylonPottersPalette.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class SignUpEmployeeConfirmFormController {

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private Label lblEmployeeIdAlert;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private Hyperlink hyperLogIn;

    @FXML
    private Pane backPane;

    @FXML
    private ImageView imgBackBtn;

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

    @FXML
    void hyperLogInOnMouseEntered(MouseEvent event) {
        StyleUtil.hyperLinkSelected(hyperLogIn);
    }

    @FXML
    void hyperLogInOnMouseExited(MouseEvent event) {
        StyleUtil.hyperLinkUnselected(hyperLogIn);
    }

    @FXML
    void btnConfirmOnMouseEntered(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnSelected(btnConfirm);
    }

    @FXML
    void btnConfirmOnMouseExited(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnUnselected(btnConfirm);
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
