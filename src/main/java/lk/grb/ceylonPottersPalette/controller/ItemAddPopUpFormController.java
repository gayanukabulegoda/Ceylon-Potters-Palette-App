package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.ItemStockDto;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.model.ItemStockModel;
import lk.grb.ceylonPottersPalette.model.ProductStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.NewId;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ItemAddPopUpFormController {

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
    private TextField txtDescription;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private Label lblDescriptionAlert;

    @FXML
    private Label lblQtyAlert;

    @FXML
    private Label lblUnitPriceAlert;

    ItemStockModel itemStockModel = new ItemStockModel();

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {

        if(validateItem()) {
            ItemStockDto itemStockDto = new ItemStockDto();

            ArrayList<String> list = itemStockModel.getAllItemId();

            itemStockDto.setItem_Id(NewId.newId(list, NewId.GetType.ITEM_STOCK));
            itemStockDto.setDescription(txtDescription.getText());
            itemStockDto.setUnit_Price(Double.parseDouble(txtUnitPrice.getText()));
            itemStockDto.setQty_On_Hand(Integer.parseInt(txtQuantity.getText()));

            boolean saved = itemStockModel.save(itemStockDto);

            if (saved) {
                Navigation.closePane();
                ItemStockFormController.getInstance().allItemId();
            }
        }
    }

    private boolean validateItem() {

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
        return true;
    }

    @FXML
    void txtDescriptionOnMouseClicked(MouseEvent event) {
        lblDescriptionAlert.setText(" ");
    }

    @FXML
    void txtQuantityOnMouseClicked(MouseEvent event) {
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
