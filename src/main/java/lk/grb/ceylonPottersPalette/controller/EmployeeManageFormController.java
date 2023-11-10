package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.utill.Navigation;

import java.io.IOException;

public class EmployeeManageFormController {

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
}
