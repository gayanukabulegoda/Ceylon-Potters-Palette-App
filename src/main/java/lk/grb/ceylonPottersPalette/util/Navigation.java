package lk.grb.ceylonPottersPalette.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.grb.ceylonPottersPalette.controller.GlobalFormController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Navigation {

    private static Stage stage;
    private static Scene scene;
    private static Parent parent;

    public static void switchNavigation(String link, ActionEvent event) throws IOException {
        parent = FXMLLoader.load(Navigation.class.getResource("/view/" + link));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void switchNavigation(String link, javafx.scene.input.MouseEvent event) throws IOException {
        parent = FXMLLoader.load(Navigation.class.getResource("/view/" + link));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void popupNavigation(String link) throws IOException {
        URL resource = Navigation.class.getResource("/view/" + link);
        Parent parent = FXMLLoader.load(resource);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void exit() {
        System.exit(0);
    }

    public static void close(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public static void close(javafx.scene.input.MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public static void switchPaging(Pane pane, String path) throws IOException {
        pane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("/view/"+path));
        Parent root = loader.load();
        pane.getChildren().add(root);
    }

    public static void closePane(){
        GlobalFormController.getInstance().popUpPane.getChildren().clear();
        GlobalFormController.getInstance().popUpPane.setVisible(false);
        GlobalFormController.getInstance().imgPopUpBackground.setVisible(false);
    }

    public static void closeOrderPopUpPane() {
        GlobalFormController.getInstance().orderPopUpPane.getChildren().clear();
        GlobalFormController.getInstance().orderPopUpPane.setVisible(false);
        GlobalFormController.getInstance().imgPopUpBackground.setVisible(false);
    }

    public static void imgPopUpBackground(String path) throws IOException {
        GlobalFormController.getInstance().imgPopUpBackground.setVisible(true);

        if (path.equals("customerOrderAddPopUpForm.fxml") | path.equals("customerOrderViewPopUpForm.fxml")) {
            GlobalFormController.getInstance().orderPopUpPane.setVisible(true);
            switchPaging(GlobalFormController.getInstance().orderPopUpPane, path);
        }
        else if (path.equals("supplierOrderAddPopUpForm.fxml")) {
            GlobalFormController.getInstance().orderPopUpPane.setVisible(true);
            switchPaging(GlobalFormController.getInstance().orderPopUpPane, path);
        }
        else {
            GlobalFormController.getInstance().popUpPane.setVisible(true);
            switchPaging(GlobalFormController.getInstance().popUpPane, path);
        }
    }
}
