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
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.model.EmployeeSalaryModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EmployeeSalaryFormController implements Initializable {

    @FXML
    private Pane addSalaryPane;

    @FXML
    private Pane btnRefreshPane;

    @FXML
    private Pane btnEmployeeAttendancePane;

    @FXML
    private Pane btnEmployeeManagePane;

    @FXML
    private Pane btnEmployeeSalaryPane;

    @FXML
    private ImageView imgAdd;

    @FXML
    private ImageView imgRefresh;

    @FXML
    private Label lblAddSalary;

    @FXML
    private Label lblSearchAlert;

    @FXML
    private Label lblEmployeeAttendance;

    @FXML
    private Label lblEmployeeManage;

    @FXML
    private Label lblEmployeeSalary;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxEmployeeSalary;

    private static EmployeeSalaryFormController controller;

    public EmployeeSalaryFormController() {
        controller = this;
    }

    public static EmployeeSalaryFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddSalaryOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("employeeSalaryPopUpForm.fxml");
    }

    @FXML
    void btnAddSalaryOnMouseEntered(MouseEvent event) {
        StyleUtil.addBtnSelected(addSalaryPane, lblAddSalary, imgAdd);
    }

    @FXML
    void btnAddSalaryOnMouseExited(MouseEvent event) {
        StyleUtil.addBtnUnselected(addSalaryPane, lblAddSalary, imgAdd);
    }

    @FXML
    void btnEmployeeAttendanceOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeAttendanceForm.fxml");
    }

    @FXML
    void btnEmployeeManageOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeManageForm.fxml");
    }

    @FXML
    void btnRefreshTableOnAction(ActionEvent event) {
        try {
            allSalaryId();
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
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!validateId()) {
            lblSearchAlert.setText("Invalid Contact No!!");
            return;
        }

        EmployeeSalaryModel employeeSalaryModel = new EmployeeSalaryModel();
        EmployeeModel employeeModel = new EmployeeModel();
        ArrayList<String> allEmployeeId = employeeSalaryModel.getAllEmployeeId();

        for (int i = 0; i < allEmployeeId.size(); i++) {
            if (txtSearch.getText().equals(employeeModel.getEmployeeContactNo(allEmployeeId.get(i)))) {
                allSelectedEmployeeSalaryId(allEmployeeId.get(i));
                lblSearchAlert.setText(" ");
                txtSearch.clear();
                return;
            }
        }
        lblSearchAlert.setText("Invalid Contact No!!");
    }

    private boolean validateId() {
        return Pattern.matches("[0-9]{10}", txtSearch.getText());
    }

    public void allSalaryId() throws SQLException {

        vBoxEmployeeSalary.getChildren().clear();
        EmployeeSalaryModel employeeSalaryModel = new EmployeeSalaryModel();
        ArrayList<String> list = employeeSalaryModel.getAllSalaryId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    public void allSelectedEmployeeSalaryId(String id) throws SQLException {

        vBoxEmployeeSalary.getChildren().clear();
        EmployeeSalaryModel employeeSalaryModel = new EmployeeSalaryModel();
        ArrayList<String> list = employeeSalaryModel.getSelectedAllSalaryId(id);

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(EmployeeSalaryFormController.class.getResource("/view/employeeSalaryBarForm.fxml"));
            Parent root = loader.load();
            EmployeeSalaryBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxEmployeeSalary.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allSalaryId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
