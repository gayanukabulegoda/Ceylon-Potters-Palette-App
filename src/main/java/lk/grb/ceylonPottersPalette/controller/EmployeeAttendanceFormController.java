package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.model.EmployeeAttendanceModel;
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeAttendanceFormController implements Initializable {

    @FXML
    private Pane btnEmployeeAttendancePane;

    @FXML
    private Pane btnEmployeeManagePane;

    @FXML
    private Pane btnEmployeeSalaryPane;

    @FXML
    private Label lblEmployeeAttendance;

    @FXML
    private Label lblEmployeeManage;

    @FXML
    private Label lblEmployeeSalary;

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
    void btnEmployeeAttendanceOnAction(ActionEvent event) {

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
    void btnEnterIdOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("employeeAttendanceMarkPopUp.fxml");
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
