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
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EmployeeManageFormController implements Initializable {

    @FXML
    private Pane btnEmployeeAttendancePane1;

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
    private VBox vBoxEmployeeManage;

    private static EmployeeManageFormController controller;

    public EmployeeManageFormController() {
        controller = this;
    }

    public static EmployeeManageFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("employeeAddPopUpForm.fxml");
    }

    @FXML
    void btnEmployeeAttendanceOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeAttendanceForm.fxml");
    }

    @FXML
    void btnEmployeeManageOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmployeeSalaryOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeSalaryForm.fxml");
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!validateId()) {
            new Alert(Alert.AlertType.ERROR, "Invalid Id! Id Should be in the format 'E-001' !!").show();
            return;
        }

        EmployeeModel employeeModel = new EmployeeModel();
        ArrayList<String> allEmployeeId = employeeModel.getAllEmployeeId();

        for (int i = 0; i < allEmployeeId.size(); i++) {
            if (txtSearch.getText().equals(allEmployeeId.get(i))) {
                EmployeeViewPopUpFormController.employeeId = txtSearch.getText();
                Navigation.imgPopUpBackground("employeeViewPopUpForm.fxml");
                return;
            }
        }
        new Alert(Alert.AlertType.ERROR, "Invalid Id! Id Should be in the format 'E-001' !!").show();
    }

    private boolean validateId() {
        return Pattern.matches("(E-00)\\d{1,}", txtSearch.getText());
    }

    public void allEmployeeId() throws SQLException {

        vBoxEmployeeManage.getChildren().clear();
        EmployeeModel employeeModel = new EmployeeModel();
        ArrayList<String> list = employeeModel.getAllEmployeeId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(EmployeeManageFormController.class.getResource("/view/employeeManageBarForm.fxml"));
            Parent root = loader.load();
            EmployeeManageBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxEmployeeManage.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allEmployeeId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
