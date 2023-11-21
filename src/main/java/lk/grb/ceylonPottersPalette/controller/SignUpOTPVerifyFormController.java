package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

public class SignUpOTPVerifyFormController implements Initializable {

    @FXML
    private TextField txtOTP;

    @FXML
    private Label lblOtpAlert;

    public static String employeeId;
    public static String otp;

    //EmployeeModel employeeModel = new EmployeeModel();
    SendEmail sendEmail = new SendEmail();

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("signUpEmployeeConfirmForm.fxml", event);
    }

    @FXML
    void btnVerifyOnAction(ActionEvent event) throws IOException {

        if (otp.equals(txtOTP.getText())) {
            Navigation.close(event);
            Navigation.switchNavigation("signUpForm.fxml", event);
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Wrong OTP !! Try Again").show();
        }
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

        try {
            //EmployeeDto employeeDto = employeeModel.getData(employeeId);
            String email = "ceylonpotterspallet@gmail.com";
            String subject = "OTP Verification";
            String body = otp;

            String[] detail = {email, subject, body};
            sendEmail.sendMail(detail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
