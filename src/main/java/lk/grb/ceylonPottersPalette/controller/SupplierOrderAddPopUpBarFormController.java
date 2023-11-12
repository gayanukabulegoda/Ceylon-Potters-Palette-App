package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.model.ItemStockModel;

import java.sql.SQLException;

public class SupplierOrderAddPopUpBarFormController {

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text description;

    @FXML
    private Text id;

    @FXML
    private Text qty;

    @FXML
    private Text total;

    @FXML
    private Text unitPrice;

    ItemStockModel itemStockModel = new ItemStockModel();

    @FXML
    void deleteOnMouseClick(MouseEvent event) {

    }

    @FXML
    void deleteOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void deleteOnMouseExited(MouseEvent event) {

    }

    public void setData(String[] element) {
        try {

            String[] descriptionAndUnitPrice = itemStockModel.descAndUnitPriceGet(element[0]);

            this.id.setText(element[0]);
            description.setText(descriptionAndUnitPrice[0]);
            unitPrice.setText(descriptionAndUnitPrice[1]);
            qty.setText(element[1]);
            total.setText(String.valueOf(Double.parseDouble(unitPrice.getText()) * Integer.parseInt(qty.getText())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
