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
import lk.grb.ceylonPottersPalette.util.DateTimeUtil;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.NewId;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeAttendanceMarkPopUpController implements Initializable {

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private Label lblCmbEmployeeIdAlert;

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
    private Label lblMarkAttendance;

    @FXML
    private Pane markAttendaceBtnPane;

    EmployeeModel employeeModel = new EmployeeModel();
    EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnMarkAttendanceOnAction(ActionEvent event) throws SQLException {

        EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();

        ArrayList<String> list = employeeAttendanceModel.getAllAttendanceId();

        employeeAttendanceDto.setAttendance_Id(NewId.newId(list, NewId.GetType.ATTENDANCE_ID));
        employeeAttendanceDto.setEmployee_Id(cmbEmployeeId.getSelectionModel().getSelectedItem());
        employeeAttendanceDto.setDate(DateTimeUtil.dateNow());
        employeeAttendanceDto.setTime(DateTimeUtil.timeNow());

        boolean save = employeeAttendanceModel.save(employeeAttendanceDto);

        if (save) {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
