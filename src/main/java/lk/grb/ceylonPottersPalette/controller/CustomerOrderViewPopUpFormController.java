package lk.grb.ceylonPottersPalette.controller;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.model.CustomerModel;
import lk.grb.ceylonPottersPalette.model.CustomerOrderDetailModel;
import lk.grb.ceylonPottersPalette.model.CustomerOrderModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerOrderViewPopUpFormController implements Initializable {

    @FXML
    private Pane btnClosePane;

    @FXML
    private ImageView imgFlower1;

    @FXML
    private ImageView imgFlower2;

    @FXML
    private ImageView imgFlower3;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderTime;

    @FXML
    private VBox vBoxCustomerOrder;

    public static String customerOrderId;
    public static String customerId;

    CustomerOrderModel customerOrderModel = new CustomerOrderModel();
    CustomerModel customerModel = new CustomerModel();
    CustomerOrderDetailModel customerOrderDetailModel = new CustomerOrderDetailModel();

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Navigation.closeOrderPopUpPane();
    }

    @FXML
    void btnCloseOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(btnClosePane);
    }

    @FXML
    void btnCloseOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(btnClosePane);
    }

    public void start() {
        imgFlower1.setCache(true);
        imgFlower1.setCacheHint(CacheHint.SPEED);

        imgFlower2.setCache(true);
        imgFlower2.setCacheHint(CacheHint.SPEED);

        imgFlower3.setCache(true);
        imgFlower3.setCacheHint(CacheHint.SPEED);

        // Create a RotateTransition for continuous rotation
        RotateTransition rotateTransition1 = new RotateTransition(Duration.seconds(8), imgFlower1);
        rotateTransition1.setInterpolator(Interpolator.LINEAR);
        rotateTransition1.setByAngle(360); // Rotate by 360 degrees
        rotateTransition1.setCycleCount(RotateTransition.INDEFINITE); // Repeat indefinitely
        rotateTransition1.play();

        RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(8), imgFlower2);
        rotateTransition2.setInterpolator(Interpolator.LINEAR);
        rotateTransition2.setByAngle(-360); // Rotate by 360 degrees
        rotateTransition2.setCycleCount(RotateTransition.INDEFINITE); // Repeat indefinitely
        rotateTransition2.play();

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(8), imgFlower3);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setByAngle(-360); // Rotate by 360 degrees
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE); // Repeat indefinitely
        rotateTransition.play();
    }

    public void allCustomerOrderCartId(ArrayList<String[]> productList) {

        vBoxCustomerOrder.getChildren().clear();

        for (int i = 0; i < productList.size(); i++) {
            loadDataTable(productList.get(i));
        }
    }

    private void loadDataTable(String[] id) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerOrderViewPopUpFormController.class.getResource("/view/customerOrderViewPopUpBarForm.fxml"));
            Parent root = loader.load();
            CustomerOrderViewPopUpBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxCustomerOrder.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData() throws SQLException {

        CustomerOrderDto customerOrderDto = customerOrderModel.getData(customerOrderId);
        String customerName = customerModel.getCustomerName(customerId);
        ArrayList<String[]> list = customerOrderDetailModel.getData(customerOrderId);

        lblOrderId.setText(customerOrderDto.getCustomer_Order_Id());
        lblOrderDate.setText(customerOrderDto.getDate());
        lblOrderTime.setText(customerOrderDto.getTime());
        lblCustomerId.setText(customerOrderDto.getCustomer_Id());
        lblCustomerName.setText(customerName);
        lblNetTotal.setText(String.valueOf(customerOrderDto.getTotal_Price()));

        allCustomerOrderCartId(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start();

        try {
            setData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
