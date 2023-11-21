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
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.model.ProductStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ProductUpdatePopUpFormController implements Initializable {

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
    private TextField txtCategory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private Pane updateBtnPane;

    @FXML
    private Label lblCategoryAlert;

    @FXML
    private Label lblDescriptionAlert;

    @FXML
    private Label lblQtyAlert;

    @FXML
    private Label lblUnitPriceAlert;

    public static String productId;

    ProductStockModel productStockModel = new ProductStockModel();

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

        if(validateProduct()) {

            ProductStockDto productStockDto = new ProductStockDto();

            productStockDto.setProduct_Id(productId);
            productStockDto.setDescription(txtDescription.getText());
            productStockDto.setUnit_Price(Double.parseDouble(txtUnitPrice.getText()));
            productStockDto.setQty_On_Hand(Integer.parseInt(txtQuantity.getText()));
            productStockDto.setCategory(txtCategory.getText());

            boolean updated = productStockModel.updateFromPopUp(productStockDto);

            if (updated) {
                Navigation.closePane();
                ProductStockFormController.getInstance().allProductId();
            }
        }
    }

    private boolean validateProduct() {

        boolean unitPriceValidate = Pattern.matches("(\\d.+)", txtUnitPrice.getText());

        if (!unitPriceValidate) {
            lblUnitPriceAlert.setText("Invalid!!");
            return false;
        }

        boolean descriptionValidate = Pattern.matches("[A-Za-z\\s]{2,12}", txtDescription.getText());

        if (!descriptionValidate) {
            lblDescriptionAlert.setText("Invalid!!");
            return false;
        }

        boolean qtyValidate = Pattern.matches("(\\d.+)", txtQuantity.getText());

        if (!qtyValidate) {
            lblQtyAlert.setText("Invalid!!");
            return false;
        }

        boolean categoryValidate = Pattern.matches("[A-Za-z\\s]{2,12}", txtQuantity.getText());

        if (!categoryValidate) {
            lblCategoryAlert.setText("Invalid!!");
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

    public void setData() {
        try {
            ProductStockDto productStockDto = productStockModel.getData(productId);

            txtDescription.setText(productStockDto.getDescription());
            txtQuantity.setText(String.valueOf(productStockDto.getQty_On_Hand()));
            txtUnitPrice.setText(String.valueOf(productStockDto.getUnit_Price()));
            txtCategory.setText(productStockDto.getCategory());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
