package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.model.RemoveRepairedStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.sql.SQLException;

public class RepairedStockBarRepairedBtnPopUpFormController {

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private JFXButton btnRepaired;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblRepaired;

    @FXML
    private Pane repairedBtnPane;

    @FXML
    private TextField txtEnterQuantity;

    RemoveRepairedStockModel removeRepairedStockModel = new RemoveRepairedStockModel();

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnRepairedOnAction(ActionEvent event) {

        try {
            boolean isUpdated = removeRepairedStockModel.removeRepairStock(RepairedStockBarFormController.productId, txtEnterQuantity.getText());

            if (isUpdated) {
                Navigation.closePane();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtEnterQuantityOnAction(ActionEvent event) {

    }
}
