package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.dto.EmployeeAttendanceDto;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.model.EmployeeAttendanceModel;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeAttendanceBarFormController {

    @FXML
    private Text date;

    @FXML
    private ImageView deleteImg;

    @FXML
    private ImageView updateImg;

    @FXML
    private Text id;

    @FXML
    private Text name;

    @FXML
    private Text time;

    private String employee_Attendance_Id;

    EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();
    EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    void deleteOnMouseClick(MouseEvent event) throws IOException {
        ConfirmationPopUpFormController.setId(employee_Attendance_Id);
        Navigation.imgPopUpBackground("confirmationPopUpForm.fxml");
    }

    @FXML
    void deleteOnMouseEntered(MouseEvent event) {
        StyleUtil.deleteImgSelected(deleteImg);
    }

    @FXML
    void deleteOnMouseExited(MouseEvent event) {
        StyleUtil.deleteImgUnselected(deleteImg);
    }


    @FXML
    void updateOnMouseClick(MouseEvent event) throws IOException {
        EmployeeAttendanceUpdatePopUpFormController.employeeAttendanceId = employee_Attendance_Id;
        Navigation.imgPopUpBackground("employeeAttendanceUpdatePopUpForm.fxml");
    }

    @FXML
    void updateOnMouseEntered(MouseEvent event) {
        StyleUtil.updateImgSelected(updateImg);
    }

    @FXML
    void updateOnMouseExited(MouseEvent event) {
        StyleUtil.updateImgUnselected(updateImg);
    }

    public void setData(String id) {
        try {
            EmployeeAttendanceDto employeeAttendanceDto = employeeAttendanceModel.getData(id);
            EmployeeDto employeeDto = employeeModel.getData(employeeAttendanceDto.getEmployee_Id());

            this.id.setText(employeeAttendanceDto.getEmployee_Id());
            name.setText(employeeDto.getFirst_Name() + " " + employeeDto.getLast_Name());
            time.setText(employeeAttendanceDto.getTime());
            date.setText(employeeAttendanceDto.getDate());
            employee_Attendance_Id = id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
