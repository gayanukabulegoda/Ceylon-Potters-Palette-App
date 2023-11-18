package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeManageBarFormController {

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text email;

    @FXML
    private Text id;

    @FXML
    private Text contact_No;

    @FXML
    private Text name;

    @FXML
    private Text role;

    @FXML
    private ImageView updateImg;

    @FXML
    private ImageView viewImg;

    EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    void deleteOnMouseClick(MouseEvent event) throws IOException {
        ConfirmationPopUpFormController.setId(id.getText());
        Navigation.imgPopUpBackground("confirmationPopUpForm.fxml");
    }

    @FXML
    void deleteOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void deleteOnMouseExited(MouseEvent event) {

    }

    @FXML
    void updateOnMouseClick(MouseEvent event) throws IOException {
        EmployeeUpdatePopUpFormController.employeeId = id.getText();
        Navigation.imgPopUpBackground("employeeUpdatePopUpForm.fxml");
    }

    @FXML
    void updateOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void updateOnMouseExited(MouseEvent event) {

    }

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        EmployeeViewPopUpFormController.employeeId = id.getText();
        Navigation.imgPopUpBackground("employeeViewPopUpForm.fxml");
    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {

    }

    public void setData(String id) {
        try {
            EmployeeDto employeedto = employeeModel.getData(id);

            this.id.setText(employeedto.getEmployee_Id());
            name.setText(employeedto.getFirst_Name() + " " + employeedto.getLast_Name());
            role.setText(employeedto.getRole());
            email.setText(employeedto.getEmail());
            contact_No.setText(employeedto.getContact_No());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
