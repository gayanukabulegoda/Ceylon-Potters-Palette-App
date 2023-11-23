package lk.grb.ceylonPottersPalette.util;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class StyleUtil {

    public static void closeIconBtnSelected(Pane pane, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: #FFE1E1;" +
                        "-fx-background-radius: 12px;");
        imageView.setImage(new Image("assests/icon/closeIcon2.png"));
    }

    public static void closeIconBtnUnselected(Pane pane, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12px;");
        imageView.setImage(new Image("assests/icon/closeIcon.png"));
    }

    public static void cancelBtnSelected(Pane pane, Label label) {
        pane.setStyle(
                "-fx-background-color: #FFDDC5;" +
                        "-fx-background-radius: 12px;");
        label.setStyle("-fx-text-fill: #9E4B14;");
    }

    public static void cancelBtnUnselected(Pane pane, Label label) {
        pane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12px;");
        label.setStyle("-fx-text-fill: #c56e33;");
    }

    public static void confirmORSaveBtnSelected(Pane pane) {
        pane.setStyle(
                "-fx-background-color: #885D40;" +
                        "-fx-background-radius: 12px;");
    }

    public static void confirmORSaveBtnUnselected(Pane pane) {
        pane.setStyle(
                "-fx-background-color: #C56E33;" +
                        "-fx-background-radius: 12px;");
    }
}
