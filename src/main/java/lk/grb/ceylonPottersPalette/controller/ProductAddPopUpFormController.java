package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.model.ProductStockModel;
import lk.grb.ceylonPottersPalette.model.ProductStockTransactionModel;
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.util.DateTimeUtil;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.NewId;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ProductAddPopUpFormController {

    @FXML
    private Pane AddBtnPane;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblAdd;

    @FXML
    private Label lblCancel;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private Label lblCategoryAlert;

    @FXML
    private Label lblDescriptionAlert;

    @FXML
    private Label lblQtyAlert;

    @FXML
    private Label lblUnitPriceAlert;

    public static int producedTotalProductQuantity;

    ProductStockModel productStockModel = new ProductStockModel();
    ProductStockTransactionModel productStockTransactionModel = new ProductStockTransactionModel();

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {

        if(validateProduct()) {
            ProductStockDto productStockDto = new ProductStockDto();

            ArrayList<String> list = productStockModel.getAllProductId();

            productStockDto.setProduct_Id(NewId.newId(list, NewId.GetType.PRODUCT_STOCK));
            productStockDto.setDescription(txtDescription.getText());
            productStockDto.setQty_On_Hand(Integer.parseInt(txtQuantity.getText()));
            productStockDto.setUnit_Price(Double.parseDouble(txtUnitPrice.getText()));
            productStockDto.setCategory(txtCategory.getText());

            producedTotalProductQuantity += productStockDto.getQty_On_Hand();
            productStockDto.setQty(producedTotalProductQuantity);

            boolean saved = productStockTransactionModel.saveProduct(productStockDto);

            if (saved) {
                Navigation.closePane();
                ProductStockFormController.getInstance().allProductId();
            }
        }
    }

    private boolean validateProduct() {

        boolean unitPriceValidate = Pattern.matches("(\\d+)", txtUnitPrice.getText());

        if (!unitPriceValidate) {
            lblUnitPriceAlert.setText("Invalid Unit Price!!");
            return false;
        }

        boolean descriptionValidate = Pattern.matches("[A-Za-z\\s]{3,}", txtDescription.getText());

        if (!descriptionValidate) {
            lblDescriptionAlert.setText("Invalid Description!!");
            return false;
        }

        boolean qtyValidate = Pattern.matches("(\\d+)", txtQuantity.getText());

        if (!qtyValidate) {
            lblQtyAlert.setText("Invalid Quantity!!");
            return false;
        }

        boolean categoryValidate = Pattern.matches("[A-Za-z\\s]{3,}", txtCategory.getText());

        if (!categoryValidate) {
            lblCategoryAlert.setText("Invalid Category!!");
            return false;
        }

        return true;
    }

    @FXML
    void txtCategoryOnMouseClicked(MouseEvent event) {
        lblCategoryAlert.setText(" ");
    }

    @FXML
    void txtDescriptionOnMouseClicked(MouseEvent event) {
        lblDescriptionAlert.setText(" ");
    }

    @FXML
    void txtQtyOnMouseClicked(MouseEvent event) {
        lblQtyAlert.setText(" ");
    }

    @FXML
    void txtUnitPriceOnMouseClicked(MouseEvent event) {
        lblUnitPriceAlert.setText(" ");
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(AddBtnPane);
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(AddBtnPane);
    }

    @FXML
    void btnCloseIconOnMouseEntered(MouseEvent event) {
        StyleUtil.closeIconBtnSelected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCloseIconOnMouseExited(MouseEvent event) {
        StyleUtil.closeIconBtnUnselected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        StyleUtil.cancelBtnSelected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        StyleUtil.cancelBtnUnselected(cancelBtnPane, lblCancel);
    }
}
