package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.utill.Navigation;

import java.io.IOException;

public class SupplierOrderManageFormController {

    @FXML
    private Pane btnCustomerOrdersPane;

    @FXML
    private Pane btnSupplierOrdersPane;

    @FXML
    private Label lblCustomerOrders;

    @FXML
    private Label lblSupplierOrders;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxSupplierOrders;

    @FXML
    void btnAddOrderOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("supplierOrderAddPopUpForm.fxml");
    }

    @FXML
    void btnCustomerOrdersOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "customerOrderManageForm.fxml");
    }

    @FXML
    void btnSupplierOrdersOnAction(ActionEvent event) {

    }
}
