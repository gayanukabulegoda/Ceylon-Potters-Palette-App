package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPalette.model.CustomerOrderModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerOrderManageBarFormController {

    @FXML
    private Text amount;

    @FXML
    private Text customerId;

    @FXML
    private Text date;

    @FXML
    private Text id;

    @FXML
    private Text time;

    @FXML
    private ImageView viewImg;

    @FXML
    private ImageView reportImg;

    CustomerOrderModel customerOrderModel = new CustomerOrderModel();

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        CustomerOrderViewPopUpFormController.customerOrderId = id.getText();
        CustomerOrderViewPopUpFormController.customerId = customerId.getText();
        Navigation.imgPopUpBackground("customerOrderViewPopUpForm.fxml");
    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {

    }

    @FXML
    void reportOnMouseClick(MouseEvent event) {

    }

    @FXML
    void reportOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void reportOnMouseExited(MouseEvent event) {

    }

    public void setData(String id) {
        try {
            CustomerOrderDto customerOrderDto = customerOrderModel.getData(id);

            this.id.setText(customerOrderDto.getCustomer_Order_Id());
            customerId.setText(customerOrderDto.getCustomer_Id());
            amount.setText(String.valueOf(customerOrderDto.getTotal_Price()));
            date.setText(customerOrderDto.getDate());
            time.setText(customerOrderDto.getTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}