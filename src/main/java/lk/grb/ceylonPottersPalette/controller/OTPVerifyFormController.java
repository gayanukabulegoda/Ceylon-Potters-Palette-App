package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.SendEmail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class OTPVerifyFormController implements Initializable {

    @FXML
    private TextField txtOTP;

    @FXML
    private Label lblOtpAlert;

    public static String otp;
    public static String employeeId;
    EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    void btnBackIconOnAction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("forgotPasswordForm.fxml",event);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("forgotPasswordForm.fxml",event);
    }

    @FXML
    void btnVerifyOnAction(ActionEvent event) throws IOException {

        if(validateOtp()) {

            if (otp.equals(txtOTP.getText())) {
                Navigation.close(event);
                Navigation.switchNavigation("resetPasswordFrom.fxml", event);
            } else {
                new Alert(Alert.AlertType.ERROR, "Wrong OTP !! Try Again").show();
            }
        }
    }

    private boolean validateOtp() {

        boolean otpValidate = Pattern.matches("[0-9]{6}", txtOTP.getText());

        if (!otpValidate) {
            lblOtpAlert.setText("Wrong OTP !! Try Again");
            return false;
        }

        return true;
    }

    @FXML
    void txtOtpOnMouseClicked(MouseEvent event) {
        lblOtpAlert.setText(" ");
    }

    public String generateOTP(int otpLength) {
        String numbers = "0123456789";
        StringBuilder otp = new StringBuilder(otpLength);

        Random random = new Random();

        for (int i = 0; i < otpLength; i++) {
            int index = random.nextInt(numbers.length());
            char digit = numbers.charAt(index);
            otp.append(digit);
        }

        return otp.toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        otp = generateOTP(6);

        SendEmail sendEmail = new SendEmail();

        try {
            EmployeeDto employeeDto = employeeModel.getData(employeeId);
            String email = employeeDto.getEmail();
            String subject = "OTP Verification";
            String body = otp;

            String[] detail = {email, subject, body};
            sendEmail.sendMail(detail);

        } catch (SQLException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
