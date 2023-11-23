package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.model.CustomerOrderDetailModel;
import lk.grb.ceylonPottersPalette.model.ProductStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerOrderAddPopUpBarFormController {

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

    ProductStockModel productStockModel = new ProductStockModel();

    @FXML
    void deleteOnMouseClick(MouseEvent event) throws IOException {
        String[] x ={id.getText(),qty.getText()};
        removeElement(CustomerOrderAddPopUpFormController.productList,x);
        CustomerOrderAddPopUpFormController.getInstance().allCustomerOrderCartId();
    }

    private static boolean removeElement(List<String[]> list, String[] target) {
        return list.removeIf(element -> Arrays.equals(element, target));
    }

    @FXML
    void deleteOnMouseEntered(MouseEvent event) {
        StyleUtil.deleteImgSelected(deleteImg);
    }

    @FXML
    void deleteOnMouseExited(MouseEvent event) {
        StyleUtil.deleteImgUnselected(deleteImg);
    }

    public void setData(String[] element) {
        try {
            String[] descriptionAndUnitPrice = productStockModel.descAndUnitPriceGet(element[0]);

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
