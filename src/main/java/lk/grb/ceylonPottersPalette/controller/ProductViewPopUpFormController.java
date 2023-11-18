package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.ItemStockDto;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.model.ProductStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductViewPopUpFormController implements Initializable {

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private Pane btnClosePane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblClose;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblProductId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    public static String productId;

    ProductStockModel productStockModel = new ProductStockModel();

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    public void setData() throws SQLException {

        ProductStockDto productStockDto = productStockModel.getData(productId);

        lblProductId.setText(productStockDto.getProduct_Id());
        lblDescription.setText(productStockDto.getDescription());
        lblUnitPrice.setText(String.valueOf(productStockDto.getUnit_Price()));
        lblQtyOnHand.setText(String.valueOf(productStockDto.getQty_On_Hand()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
