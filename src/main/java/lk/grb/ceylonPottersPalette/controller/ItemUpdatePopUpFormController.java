package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.ItemStockDto;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.model.ItemStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ItemUpdatePopUpFormController implements Initializable {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblUpdate;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private Pane updateBtnPane;

    @FXML
    private Label lblDescriptionAlert;

    @FXML
    private Label lblQtyAlert;

    @FXML
    private Label lblUnitPriceAlert;

    public static String itemId;

    ItemStockModel itemStockModel = new ItemStockModel();

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {

        if(validateItem()) {
            ItemStockDto itemStockDto = new ItemStockDto();

            itemStockDto.setItem_Id(itemId);
            itemStockDto.setDescription(txtDescription.getText());
            itemStockDto.setUnit_Price(Double.parseDouble(txtUnitPrice.getText()));
            itemStockDto.setQty_On_Hand(Integer.parseInt(txtQuantity.getText()));

            boolean updated = itemStockModel.updateFromPopUp(itemStockDto);

            if (updated) {
                Navigation.closePane();
                ItemStockFormController.getInstance().allItemId();
            }
        }
    }

    private boolean validateItem() {

        boolean unitPriceValidate = Pattern.matches("(\\d.+)", txtUnitPrice.getText());

        if (!unitPriceValidate) {
            lblUnitPriceAlert.setText("Invalid Unit Price!!");
            return false;
        }

        boolean descriptionValidate = Pattern.matches("[A-Za-z\\s]{3,}", txtDescription.getText());

        if (!descriptionValidate) {
            lblDescriptionAlert.setText("Invalid Description!!");
            return false;
        }

        boolean qtyValidate = Pattern.matches("(\\d.+)", txtQuantity.getText());

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

    public void setData() {
        try {
            ItemStockDto itemStockDto = itemStockModel.getData(itemId);

            txtDescription.setText(itemStockDto.getDescription());
            txtQuantity.setText(String.valueOf(itemStockDto.getQty_On_Hand()));
            txtUnitPrice.setText(String.valueOf(itemStockDto.getUnit_Price()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
