package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.dto.CustomerDto;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.model.CustomerModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerManageBarFormController {

    @FXML
    private Text contact_No;

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text email;

    @FXML
    private Text id;

    @FXML
    private Text name;

    @FXML
    private ImageView updateImg;

    @FXML
    private ImageView viewImg;

    CustomerModel customerModel = new CustomerModel();

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
        CustomerUpdatePopUpFormController.customerId = id.getText();
        Navigation.imgPopUpBackground("customerUpdatePopUpForm.fxml");
    }

    @FXML
    void updateOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void updateOnMouseExited(MouseEvent event) {

    }

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        CustomerViewPopUpFormController.customerId = id.getText();
        Navigation.imgPopUpBackground("customerViewPopUpForm.fxml");
    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {

    }

    public void setData(String id) {
        try {
            CustomerDto customerDto = customerModel.getData(id);

            this.id.setText(customerDto.getCustomer_Id());
            name.setText(customerDto.getName());
            email.setText(customerDto.getEmail());
            contact_No.setText(customerDto.getContact_No());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
