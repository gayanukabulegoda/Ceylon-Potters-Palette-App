package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.utill.Navigation;

import java.io.IOException;

public class SupplierManageFormController {

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxSupplierManage;

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("supplierAddPopUpForm.fxml");
    }
}
