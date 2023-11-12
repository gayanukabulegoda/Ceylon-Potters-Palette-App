package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPalette.model.CustomerOrderModel;

import java.sql.SQLException;

public class CustomerOrderManageBarFormController {

    @FXML
    private Text amount;

    @FXML
    private Text customerId;

    @FXML
    private Text date;

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text id;

    @FXML
    private Text time;

    @FXML
    private ImageView updateImg;

    @FXML
    private ImageView viewImg;

    CustomerOrderModel customerOrderModel = new CustomerOrderModel();

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
