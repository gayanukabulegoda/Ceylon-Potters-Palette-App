package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.utill.Navigation;

import java.io.IOException;

public class ProductStockFormController {


    @FXML
    private Pane btnItemStockPane;

    @FXML
    private Pane btnProductStockPane;

    @FXML
    private Pane btnRepairStockPane;

    @FXML
    private Label lblItemStock;

    @FXML
    private Label lblProductStock;

    @FXML
    private Label lblRepairStock;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxProductStock;

    @FXML
    void btnAddProductOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("productAddPopUpForm.fxml");
    }

    @FXML
    void btnItemStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "itemStockForm.fxml");
    }

    @FXML
    void btnProductStockOnAction(ActionEvent event) {

    }

    @FXML
    void btnRepairStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "repairedStockForm.fxml");
    }
}
