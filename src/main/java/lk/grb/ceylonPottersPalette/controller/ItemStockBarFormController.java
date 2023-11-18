package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.dto.ItemStockDto;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.model.ItemStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class ItemStockBarFormController {

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text description;

    @FXML
    private Text id;

    @FXML
    private Text quantity;

    @FXML
    private Text unitPrice;

    @FXML
    private ImageView updateImg;

    @FXML
    private ImageView viewImg;

    ItemStockModel itemStockModel = new ItemStockModel();

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
        ItemUpdatePopUpFormController.itemId = id.getText();
        Navigation.imgPopUpBackground("itemUpdatePopUpForm.fxml");
    }

    @FXML
    void updateOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void updateOnMouseExited(MouseEvent event) {

    }

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        ItemViewPopUpFormController.itemId = id.getText();
        Navigation.imgPopUpBackground("itemViewPopUpForm.fxml");
    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {

    }

    public void setData(String id) {
        try {
            ItemStockDto itemStockDto = itemStockModel.getData(id);

            this.id.setText(itemStockDto.getItem_Id());
            description.setText(itemStockDto.getDescription());
            unitPrice.setText(String.valueOf(itemStockDto.getUnit_Price()));
            quantity.setText(String.valueOf(itemStockDto.getQty_On_Hand()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
