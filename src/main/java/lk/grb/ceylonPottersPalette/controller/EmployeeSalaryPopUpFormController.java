package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.EmployeeSalaryDto;
import lk.grb.ceylonPottersPalette.model.EmployeeAttendanceModel;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.model.EmployeeSalaryModel;
import lk.grb.ceylonPottersPalette.util.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeSalaryPopUpFormController implements Initializable {

    @FXML
    private Pane AddBtnPane;

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
    private Label lblWorkedDays;

    @FXML
    private TextField txtBonus;

    @FXML
    private TextField txtSalary;

    @FXML
    private Label lblCmbEmployeeIdAlert;

    @FXML
    private Label lblSalaryAlert;

    @FXML
    private Label lblBonusAlert;

    EmployeeModel employeeModel = new EmployeeModel();
    EmployeeSalaryModel employeeSalaryModel = new EmployeeSalaryModel();
    EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();

    @FXML
    void btnAddOnAction() throws SQLException {

        if(validateEmployeeSalary()) {
            EmployeeSalaryDto employeeSalaryDto = new EmployeeSalaryDto();

            ArrayList<String> list = employeeSalaryModel.getAllSalaryId();

            employeeSalaryDto.setSalary_Id(NewId.newId(list, NewId.GetType.SALARY_ID));
            employeeSalaryDto.setEmployee_Id(cmbEmployeeId.getSelectionModel().getSelectedItem());
            employeeSalaryDto.setWorked_Day_Count(Integer.parseInt(lblWorkedDays.getText()));
            employeeSalaryDto.setSalary(Double.parseDouble(txtSalary.getText()));
            employeeSalaryDto.setBonus(Double.parseDouble(txtBonus.getText()));
            employeeSalaryDto.setTotal_Payment(Double.parseDouble(lblTotalAmount.getText()));
            employeeSalaryDto.setDate(DateTimeUtil.dateNow());
            employeeSalaryDto.setTime(DateTimeUtil.timeNow());

            boolean save = employeeSalaryModel.save(employeeSalaryDto);
            if (save) {
                Navigation.closePane();
                EmployeeSalaryFormController.getInstance().allSalaryId();
            }
        }
    }

    private boolean validateEmployeeSalary() {
        boolean result = true;

        if ((cmbEmployeeId.getSelectionModel().getSelectedItem()) == null) {
            lblCmbEmployeeIdAlert.setText("Select an Employee!!");
            result = false;
        }

        if (RegExPatterns.salaryOrBonusPattern(txtSalary.getText())) {
            lblSalaryAlert.setText("Invalid Salary!!");
            result = false;
        }

        if (RegExPatterns.salaryOrBonusPattern(txtBonus.getText())) {
            lblBonusAlert.setText("Invalid Bonus!!");
            result = false;
        }
        return result;
    }

    @FXML
    void cmbEmployeeIdOnKeyPressed(KeyEvent event) {
        lblCmbEmployeeIdAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if ((cmbEmployeeId.getSelectionModel().getSelectedItem()) == null) {
                lblCmbEmployeeIdAlert.setText("Select an Employee!!");
                event.consume();
            } else {
                txtSalary.requestFocus();
            }
        }
    }

    @FXML
    void txtSalaryOnKeyPressed(KeyEvent event) {
        lblSalaryAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.salaryOrBonusPattern(txtSalary.getText())) {
                lblSalaryAlert.setText("Invalid Salary!!");
                event.consume();
            } else {
                txtBonus.requestFocus();
            }
        }
    }

    @FXML
    void txtBonusOnKeyPressed(KeyEvent event) throws SQLException {
        lblBonusAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.salaryOrBonusPattern(txtBonus.getText())) {
                lblBonusAlert.setText("Invalid Bonus!!");
                event.consume();
            } else {
                btnAddOnAction();
            }
        }
    }

    @FXML
    void txtSalaryOnMouseClicked(MouseEvent event) {
        lblSalaryAlert.setText(" ");
    }

    @FXML
    void txtBonusOnMouseClicked(MouseEvent event) {
        lblBonusAlert.setText(" ");
    }

    @FXML
    void cmbEmployeeIdOnMouseClicked(MouseEvent event) {
        lblCmbEmployeeIdAlert.setText(" ");
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
        lblWorkedDays.setText(employeeAttendanceModel.workedDayCount(cmbEmployeeId.getSelectionModel().getSelectedItem()));
    }

    public void setDataInComboBox() throws SQLException {
        ArrayList<String> roles = employeeModel.getAllEmployeeId();
        cmbEmployeeId.getItems().addAll(roles);
    }

    @FXML
    void txtBonusOnAction(ActionEvent event) {
        isEmpty();
        lblTotalAmount.setText(String.valueOf(Double.parseDouble(txtSalary.getText())+Double.parseDouble(txtBonus.getText())));
    }

    @FXML
    void txtSalaryOnAction(ActionEvent event) {
        isEmpty();
        lblTotalAmount.setText(String.valueOf((Double.parseDouble(txtSalary.getText()))+Double.parseDouble(txtBonus.getText())));
        txtBonus.setEditable(true);
    }

    public void isEmpty() {
        if (txtBonus.getText().isEmpty()) {
            txtBonus.setText(String.valueOf(0.00));
        }
        if (txtSalary.getText().isEmpty()) {
            txtSalary.setText(String.valueOf(0.00));
        }
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(AddBtnPane);
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(AddBtnPane);
    }

    @FXML
    void btnCloseIconOnMouseEntered(MouseEvent event) {
        StyleUtil.closeIconBtnSelected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCloseIconOnMouseExited(MouseEvent event) {
        StyleUtil.closeIconBtnUnselected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        StyleUtil.cancelBtnSelected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        StyleUtil.cancelBtnUnselected(cancelBtnPane, lblCancel);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTotalAmount.setText(String.valueOf(0.00));

        try {
            setDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
