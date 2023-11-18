package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.model.ProductStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class ProductStockBarFormController {

    @FXML
    private Text category;

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

    ProductStockModel productStockModel = new ProductStockModel();

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
        ProductUpdatePopUpFormController.productId = id.getText();
        Navigation.imgPopUpBackground("productUpdatePopUpForm.fxml");
    }

    @FXML
    void updateOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void updateOnMouseExited(MouseEvent event) {

    }

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        ProductViewPopUpFormController.productId = id.getText();
        Navigation.imgPopUpBackground("productViewPopUpForm.fxml");
    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {

    }

    public void setData(String id) {
        try {
            ProductStockDto productStockDto = productStockModel.getData(id);

            this.id.setText(productStockDto.getProduct_Id());
            description.setText(productStockDto.getDescription());
            unitPrice.setText(String.valueOf(productStockDto.getUnit_Price()));
            quantity.setText(String.valueOf(productStockDto.getQty_On_Hand()));
            category.setText(productStockDto.getCategory());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
