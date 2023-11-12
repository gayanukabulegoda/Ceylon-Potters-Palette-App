package lk.grb.ceylonPottersPalette.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.dto.EmployeeAttendanceDto;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.dto.SupplierDto;
import lk.grb.ceylonPottersPalette.model.EmployeeAttendanceModel;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;

import java.sql.SQLException;

public class EmployeeAttendanceBarFormController {

    @FXML
    private Text date;

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text id;

    @FXML
    private Text name;

    @FXML
    private Text time;

    EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();
    EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    void deleteOnMouseClick(MouseEvent event) {

    }

    @FXML
    void deleteOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void deleteOnMouseExited(MouseEvent event) {

    }

    public void setData(String id) {
        try {
            EmployeeAttendanceDto employeeAttendanceDto = employeeAttendanceModel.getData(id);
            EmployeeDto employeeDto = employeeModel.getData(employeeAttendanceDto.getEmployee_Id());

            this.id.setText(employeeAttendanceDto.getEmployee_Id());
            name.setText(employeeDto.getFirst_Name() + " " + employeeDto.getLast_Name());
            time.setText(employeeAttendanceDto.getTime());
            date.setText(employeeAttendanceDto.getDate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
