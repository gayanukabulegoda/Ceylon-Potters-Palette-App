package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.RepairStockDto;
import lk.grb.ceylonPottersPalette.model.UpdateRepairStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.sql.SQLException;

public class RepairedStockBarUpdateBtnPopUpFormController {

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblUpdate;

    @FXML
    private TextField txtEnterQuantity;

    @FXML
    private Pane updateBtnPane;

    UpdateRepairStockModel updateRepairStockModel = new UpdateRepairStockModel();

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        try {
            boolean isUpdated = updateRepairStockModel.updateRepairStock(RepairedStockBarFormController.productId, txtEnterQuantity.getText());

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
