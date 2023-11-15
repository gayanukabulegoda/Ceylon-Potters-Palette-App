package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerOrderViewPopUpFormController implements Initializable {

    @FXML
    private JFXButton btnClose;

    @FXML
    private Pane btnClosePane;

    @FXML
    private ImageView imgFlower1;

    @FXML
    private ImageView imgFlower2;

    @FXML
    private ImageView imgFlower3;

    @FXML
    private Label lblClose;

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

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Navigation.closeOrderPopUpPane();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start();
    }
}
