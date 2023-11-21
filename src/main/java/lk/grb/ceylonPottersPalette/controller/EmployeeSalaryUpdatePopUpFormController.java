package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.EmployeeSalaryDto;
import lk.grb.ceylonPottersPalette.model.EmployeeAttendanceModel;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.model.EmployeeSalaryModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeSalaryUpdatePopUpFormController implements Initializable {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private Label lblUpdate;

    @FXML
    private Label lblWorkedDays;

    @FXML
    private TextField txtBonus;

    @FXML
    private TextField txtSalary;

    @FXML
    private Pane updateBtnPane;

    public static String salaryId;

    EmployeeModel employeeModel = new EmployeeModel();
    EmployeeSalaryModel employeeSalaryModel = new EmployeeSalaryModel();
    EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconONAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {

        EmployeeSalaryDto employeeSalaryDto = new EmployeeSalaryDto();

        employeeSalaryDto.setSalary_Id(salaryId);
        employeeSalaryDto.setEmployee_Id(cmbEmployeeId.getSelectionModel().getSelectedItem());
        employeeSalaryDto.setWorked_Day_Count(Integer.parseInt(lblWorkedDays.getText()));
        employeeSalaryDto.setSalary(Double.parseDouble(txtSalary.getText()));
        employeeSalaryDto.setBonus(Double.parseDouble(txtBonus.getText()));
        employeeSalaryDto.setTotal_Payment(Double.parseDouble(lblTotalAmount.getText()));
        employeeSalaryDto.setDate(employeeSalaryDto.getDate());
        employeeSalaryDto.setTime(employeeSalaryDto.getTime());

        boolean updated = employeeSalaryModel.update(employeeSalaryDto);

        if (updated) {
            Navigation.closePane();
            EmployeeSalaryFormController.getInstance().allSalaryId();
        }
    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) throws SQLException {
        lblEmployeeName.setText(employeeModel.getEmployeeName(String.valueOf(cmbEmployeeId.getSelectionModel().getSelectedItem())));
        lblWorkedDays.setText(employeeAttendanceModel.workedDayCount(cmbEmployeeId.getSelectionModel().getSelectedItem()));
        //lblWorkedDays.setText(employeeAttendanceModel.workedDayCount(cmbEmployeeId.getSelectionModel().getSelectedItem(), (LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM")) + "%")));
    }

    @FXML
    void txtBonusOnAction(ActionEvent event) {
        lblTotalAmount.setText(String.valueOf(Double.parseDouble(txtSalary.getText())+Double.parseDouble(txtBonus.getText())));
    }

    @FXML
    void txtSalaryOnAction(ActionEvent event) {
        lblTotalAmount.setText(String.valueOf((Double.parseDouble(txtSalary.getText()))+Double.parseDouble(txtBonus.getText())));
        txtBonus.setEditable(true);
    }

    public void setDataInComboBox() throws SQLException {
        ArrayList<String> roles = employeeModel.getAllEmployeeId();
        cmbEmployeeId.getItems().addAll(roles);
    }

    public void setData() {
        try {
            EmployeeSalaryDto employeeSalaryDto = employeeSalaryModel.getData(salaryId);

            txtBonus.setText(String.valueOf(employeeSalaryDto.getBonus()));
            txtSalary.setText(String.valueOf(employeeSalaryDto.getSalary()));
            lblEmployeeName.setText(employeeModel.getEmployeeName(employeeSalaryDto.getEmployee_Id()));
            lblTotalAmount.setText(String.valueOf(employeeSalaryDto.getTotal_Payment()));
            lblWorkedDays.setText(String.valueOf(employeeSalaryDto.getWorked_Day_Count()));
            cmbEmployeeId.setValue(employeeSalaryDto.getEmployee_Id());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setData();
    }
}
