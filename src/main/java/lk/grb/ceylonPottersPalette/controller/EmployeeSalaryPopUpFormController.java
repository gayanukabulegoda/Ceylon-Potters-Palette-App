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
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.model.EmployeeSalaryModel;
import lk.grb.ceylonPottersPalette.util.DateTimeUtil;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeSalaryPopUpFormController implements Initializable {

    @FXML
    private Pane AddBtnPane;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblAdd;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private Label lblWorkedDays;

    @FXML
    private TextField txtBonus;

    @FXML
    private TextField txtSalary;

    EmployeeModel employeeModel = new EmployeeModel();
    EmployeeSalaryModel employeeSalaryModel = new EmployeeSalaryModel();

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {

        EmployeeSalaryDto employeeSalaryDto = new EmployeeSalaryDto();

        employeeSalaryDto.setEmployee_Id(cmbEmployeeId.getSelectionModel().getSelectedItem());
      //  employeeSalaryDto.setWorked_Day_Count(calculateWorkedDays());
     //   lblWorkedDays.setText(String.valueOf(employeeSalaryModel.workedDayCount(cmbEmployeeId.getSelectionModel().getSelectedItem())));

        employeeSalaryDto.setSalary(Double.parseDouble(txtSalary.getText()));
        employeeSalaryDto.setBonus(Double.parseDouble(txtBonus.getText()));
        lblTotalAmount.setText(String.valueOf(employeeSalaryDto.getSalary() + employeeSalaryDto.getBonus()));

        employeeSalaryDto.setTotal_Payment(employeeSalaryDto.getSalary() + employeeSalaryDto.getBonus());
        employeeSalaryDto.setDate(DateTimeUtil.dateNow());
        employeeSalaryDto.setTime(DateTimeUtil.timeNow());

        boolean save = employeeSalaryModel.save(employeeSalaryDto);

        Navigation.closePane();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconONAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) throws SQLException {
        lblEmployeeName.setText(employeeModel.getEmployeeName(String.valueOf(cmbEmployeeId.getSelectionModel().getSelectedItem())));
    }

//    public int calculateWorkedDays() throws SQLException {
//        return Integer.parseInt(employeeSalaryModel.workedDayCount(cmbEmployeeId.getSelectionModel().getSelectedItem()));
//    }

    public void setDataInComboBox() throws SQLException {
        ArrayList<String> roles = employeeModel.getAllEmployeeId();
        cmbEmployeeId.getItems().addAll(roles);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
