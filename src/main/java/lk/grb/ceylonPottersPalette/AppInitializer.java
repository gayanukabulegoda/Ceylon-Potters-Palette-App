package lk.grb.ceylonPottersPalette;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/view/forgotPasswordForm.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/view/OTPVerifyForm.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/view/resetPasswordFrom.fxml"));
        stage.setScene(new Scene(root));
        //stage.setTitle("Ceylon Potters' Palette");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }
}
