package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.model.SupplierModel;

import java.sql.SQLException;

public class SupplierManageBarFormController {

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

    SupplierModel supplierModel = new SupplierModel();

    @FXML
    void deleteOnMouseClick(MouseEvent event) {

    }

    @FXML
    void deleteOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void deleteOnMouseExited(MouseEvent event) {

    }

    @FXML
    void updateOnMouseClick(MouseEvent event) {

    }

    @FXML
    void updateOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void updateOnMouseExited(MouseEvent event) {

    }

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) {

    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {

    }

    public void setData(String id) {
        try {
            SupplierDto supplierDto = supplierModel.getData(id);

            this.id.setText(supplierDto.getSupplier_Id());
            name.setText(supplierDto.getName());
            email.setText(supplierDto.getEmail());
            contact_No.setText(supplierDto.getContact_No());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
