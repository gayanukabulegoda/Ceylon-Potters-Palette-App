package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPalette.model.SupplierOrderModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class SupplierOrderManageBarFormController {

    @FXML
    private Text amount;

    @FXML
    private Text date;

    @FXML
    private Text id;

    @FXML
    private Text supplierId;

    @FXML
    private Text time;

    @FXML
    private ImageView viewImg;

    SupplierOrderModel supplierOrderModel = new SupplierOrderModel();

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        SupplierOrderViewPopUpFormController.supplierOrderId = id.getText();
        SupplierOrderViewPopUpFormController.supplierId = supplierId.getText();
        Navigation.imgPopUpBackground("supplierOrderViewPopUpForm.fxml");
    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {

    }

    public void setData(String id) {
        try {
            SupplierOrderDto supplierOrderDto = supplierOrderModel.getData(id);

            this.id.setText(supplierOrderDto.getSupplier_Order_Id());
            supplierId.setText(supplierOrderDto.getSupplier_Id());
            amount.setText(String.valueOf(supplierOrderDto.getTotal_Price()));
            date.setText(supplierOrderDto.getDate());
            time.setText(supplierOrderDto.getTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
