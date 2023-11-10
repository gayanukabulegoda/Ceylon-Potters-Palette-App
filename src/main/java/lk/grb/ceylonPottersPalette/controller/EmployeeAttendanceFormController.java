package lk.grb.ceylonPottersPalette.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPalette.utill.Navigation;

import java.io.IOException;

public class EmployeeAttendanceFormController {

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
}
