package lk.grb.ceylonPottersPalette.controller;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import lk.grb.ceylonPottersPalette.model.EmployeeAttendanceModel;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.qr.QrReader;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class EmployeeAttendanceFormController implements Initializable {

    @FXML
    private Pane scanQrMsgPane;

    @FXML
    private Pane btnEnterPane;

    @FXML
    private Pane btnQrPane;

    @FXML
    private Pane btnRefreshPane;

    @FXML
    private Pane searchBarPane;

    @FXML
    private ImageView imgEnter;

    @FXML
    private ImageView imgQrScan;

    @FXML
    private ImageView imgRefresh;

    @FXML
    private Label lblEnter;

    @FXML
    private Label lblSearchAlert;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxEmployeeAttendance;

    private static EmployeeAttendanceFormController controller;

    public EmployeeAttendanceFormController() {
        controller = this;
    }

    public static EmployeeAttendanceFormController getInstance() {
        return controller;
    }

    @FXML
    void btnRefreshTableOnAction(ActionEvent event) {
        try {
            allAttendanceId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRefreshTableOnMouseEntered(MouseEvent event) {
        StyleUtil.refreshBtnSelected(btnRefreshPane, imgRefresh);
    }

    @FXML
    void btnRefreshTableOnMouseExited(MouseEvent event) {
        StyleUtil.refreshBtnUnselected(btnRefreshPane, imgRefresh);
    }

    @FXML
    void txtSearchOnMouseClicked(MouseEvent event) {
        lblSearchAlert.setText(" ");
        StyleUtil.searchBarTransparent(searchBarPane);
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!validateId()) {
            lblSearchAlert.setText("Invalid Contact No!!");
            StyleUtil.searchBarRed(searchBarPane);
            return;
        }

        EmployeeModel employeeModel = new EmployeeModel();
        ArrayList<String> allEmployeeId = employeeModel.getAllEmployeeId();

        for (int i = 0; i < allEmployeeId.size(); i++) {
            if (txtSearch.getText().equals(employeeModel.getEmployeeContactNo(allEmployeeId.get(i)))) {
                allSelectedEmployeeSalaryId(allEmployeeId.get(i));
                lblSearchAlert.setText(" ");
                StyleUtil.searchBarTransparent(searchBarPane);
                txtSearch.clear();
                return;
            }
        }
        lblSearchAlert.setText("Invalid Contact No!!");
        StyleUtil.searchBarRed(searchBarPane);
    }

    private boolean validateId() {
        return Pattern.matches("[0-9]{10}", txtSearch.getText());
    }

    @FXML
    void btnEmployeeManageOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeManageForm.fxml");
    }

    @FXML
    void btnEmployeeSalaryOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeSalaryForm.fxml");
    }

    @FXML
    void btnEnterOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("employeeAttendanceMarkPopUp.fxml");
    }

    @FXML
    void btnEnterOnMouseEntered(MouseEvent event) {
        StyleUtil.addBtnSelected(btnEnterPane, lblEnter, imgEnter);
    }

    @FXML
    void btnEnterOnMouseExited(MouseEvent event) {
        StyleUtil.addBtnUnselected(btnEnterPane, lblEnter, imgEnter);
    }

    @FXML
    void btnQrOnAction(ActionEvent event) throws SQLException {

        AtomicReference<String> id = new AtomicReference<>();

        Thread qrThread = new Thread(() -> {
            id.set(QrReader.readQr());
        });
        qrThread.start();

        try {
            qrThread.join(); // Wait for the QR code reading thread to complete
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        EmployeeAttendanceMarkPopUpController.markAttendanceOViaQr(String.valueOf(id));
    }

    @FXML
    void btnQrOnMouseEntered(MouseEvent event) {
        StyleUtil.qrBtnSelected(btnQrPane, imgQrScan);

        scanQrMsgPane.setVisible(true);
        // Scale-in effect using ScaleTransition
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.3), scanQrMsgPane);
        scaleTransition.setFromX(0);
        scaleTransition.setToX(1);
        scaleTransition.setFromY(0);
        scaleTransition.setToY(1);

        // Slide-in effect using TranslateTransition
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.2), scanQrMsgPane);
        translateTransition.setFromX(scanQrMsgPane.getWidth()); // Start from the right of the pane
        translateTransition.setToX(0);

        scaleTransition.play();
        translateTransition.play();
    }

    @FXML
    void btnQrOnMouseExited(MouseEvent event) {
        StyleUtil.qrBtnUnselected(btnQrPane, imgQrScan);

        scanQrMsgPane.setVisible(false);
    }

    public void allSelectedEmployeeSalaryId(String id) throws SQLException {

        vBoxEmployeeAttendance.getChildren().clear();
        EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();
        ArrayList<String> list = employeeAttendanceModel.getSelectedAllAttendanceId(id);

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    public void allAttendanceId() throws SQLException {

        vBoxEmployeeAttendance.getChildren().clear();
        EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();
        ArrayList<String> list = employeeAttendanceModel.getAllAttendanceId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(EmployeeAttendanceFormController.class.getResource("/view/employeeAttendanceBarForm.fxml"));
            Parent root = loader.load();
            EmployeeAttendanceBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxEmployeeAttendance.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allAttendanceId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
