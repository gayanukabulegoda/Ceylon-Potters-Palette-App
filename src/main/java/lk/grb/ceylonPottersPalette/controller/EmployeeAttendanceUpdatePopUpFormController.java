package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.EmployeeAttendanceDto;
import lk.grb.ceylonPottersPalette.model.EmployeeAttendanceModel;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeAttendanceUpdatePopUpFormController implements Initializable {

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private JFXButton btnMarkAttendance;

    @FXML
    private Pane closeIconPane;

    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblUpdateAttendance;

    @FXML
    private Pane updateAttendaceBtnPane;

    public static String employeeAttendanceId;

    EmployeeModel employeeModel = new EmployeeModel();
    EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateAttendanceOnAction(ActionEvent event) throws SQLException {

        EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();

        employeeAttendanceDto.setAttendance_Id(employeeAttendanceId);
        employeeAttendanceDto.setEmployee_Id(cmbEmployeeId.getSelectionModel().getSelectedItem());
        employeeAttendanceDto.setDate(employeeAttendanceDto.getDate());
        employeeAttendanceDto.setTime(employeeAttendanceDto.getTime());

        boolean updated = employeeAttendanceModel.update(employeeAttendanceDto);

        if (updated) {
            Navigation.closePane();
            EmployeeAttendanceFormController.getInstance().allAttendanceId();
        }
    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) throws SQLException {
        lblEmployeeName.setText(employeeModel.getEmployeeName(String.valueOf(cmbEmployeeId.getSelectionModel().getSelectedItem())));
    }

    public void setDataInComboBox() throws SQLException {
        ArrayList<String> roles = employeeModel.getAllEmployeeId();
        cmbEmployeeId.getItems().addAll(roles);
    }

    public void setData() {
        try {
            EmployeeAttendanceDto employeeAttendanceDto = employeeAttendanceModel.getData(employeeAttendanceId);

            lblEmployeeName.setText(employeeModel.getEmployeeName(employeeAttendanceDto.getEmployee_Id()));
            cmbEmployeeId.setValue(employeeAttendanceDto.getEmployee_Id());

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
