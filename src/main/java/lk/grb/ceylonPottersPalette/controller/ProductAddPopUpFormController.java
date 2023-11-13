package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

import java.sql.SQLException;
import java.util.ArrayList;

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

    public static int producedTotalProductQuantity;

    ProductStockModel productStockModel = new ProductStockModel();
    ProductStockTransactionModel productStockTransactionModel = new ProductStockTransactionModel();

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {
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

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }
}
