package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.grb.ceylonPottersPalette.model.ItemStockModel;
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ItemStockFormController implements Initializable {

    @FXML
    private Pane addItemPane;

    @FXML
    private Pane btnItemStockPane;

    @FXML
    private Pane btnProductStockPane;

    @FXML
    private Pane btnRepairStockPane;

    @FXML
    private ImageView imgAdd;

    @FXML
    private Label lblSearchAlert;

    @FXML
    private Label lblAddItem;

    @FXML
    private Label lblItemStock;

    @FXML
    private Label lblProductStock;

    @FXML
    private Label lblRepairStock;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxItemStock;

    private static ItemStockFormController controller;

    public ItemStockFormController() {
        controller = this;
    }

    public static ItemStockFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("itemAddPopUpForm.fxml");
    }

    @FXML
    void btnAddItemOnMouseEntered(MouseEvent event) {
        StyleUtil.addBtnSelected(addItemPane,lblAddItem, imgAdd);
    }

    @FXML
    void btnAddItemOnMouseExited(MouseEvent event) {
        StyleUtil.addBtnUnselected(addItemPane, lblAddItem, imgAdd);
    }

    @FXML
    void btnProductStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "productStockForm.fxml");
    }

    @FXML
    void btnRepairStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "repairedStockForm.fxml");
    }

    @FXML
    void txtSearchOnMouseClicked(MouseEvent event) {
        lblSearchAlert.setText(" ");
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!validateName()) {
            lblSearchAlert.setText("Wrong Name! Try again!!");
            return;
        }

        ItemStockModel itemStockModel = new ItemStockModel();
        ArrayList<String> allItemId = itemStockModel.getAllItemId();

        for (int i = 0; i < allItemId.size(); i++) {
            if (txtSearch.getText().equals(itemStockModel.getDescription(allItemId.get(i)))) {
                ItemViewPopUpFormController.itemId = allItemId.get(i);
                lblSearchAlert.setText(" ");
                txtSearch.clear();
                Navigation.imgPopUpBackground("itemViewPopUpForm.fxml");
                return;
            }
        }
        lblSearchAlert.setText("Wrong Name! Try again!!");
    }

    private boolean validateName() {
        return Pattern.matches("[A-Za-z\\s]{3,}", txtSearch.getText());
    }

    public void allItemId() throws SQLException {

        vBoxItemStock.getChildren().clear();
        ItemStockModel itemStockModel = new ItemStockModel();
        ArrayList<String> list = itemStockModel.getAllItemId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(ItemStockFormController.class.getResource("/view/itemStockBarForm.fxml"));
            Parent root = loader.load();
            ItemStockBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxItemStock.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allItemId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
