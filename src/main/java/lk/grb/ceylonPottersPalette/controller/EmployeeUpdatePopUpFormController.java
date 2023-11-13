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
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeUpdatePopUpFormController implements Initializable {

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
    private JFXComboBox<String> cmbRole;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblUpdate;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtHouseNo;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtStreet;

    @FXML
    private Pane updateBtnPane;

    public static String employeeId;

    public static EmployeeUpdatePopUpFormController controller;
    EmployeeModel employeeModel = new EmployeeModel();

    public EmployeeUpdatePopUpFormController() {
        controller = this;
    }

    public static EmployeeUpdatePopUpFormController getInstance() {
        return controller;
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setEmployee_Id(EmployeeUpdatePopUpFormController.employeeId);
        employeeDto.setFirst_Name(txtFirstName.getText());
        employeeDto.setLast_Name(txtLastName.getText());
        employeeDto.setNic(txtNic.getText());
        employeeDto.setHouse_No(txtHouseNo.getText());
        employeeDto.setStreet(txtStreet.getText());
        employeeDto.setCity(txtCity.getText());
        employeeDto.setContact_No(txtContactNo.getText());
        employeeDto.setEmail(txtEmail.getText());
        employeeDto.setRole(getRole());

        boolean updated = employeeModel.update(employeeDto);

        if (updated) {
            Navigation.closePane();
            EmployeeManageFormController.getInstance().allEmployeeId();
        }
    }

    @FXML
    void cmbRoleOnAction(ActionEvent event) {

    }

    public void setDataInComboBox() {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Manager");
        roles.add("Manufacturing Staff");
        roles.add("Sales Staff");
        roles.add("Minor Staff");
        roles.add("other");

        cmbRole.getItems().addAll(roles);
    }

    public String getRole() {
        return String.valueOf(cmbRole.getSelectionModel().getSelectedItem());
    }

    public void setData() {
        try {
            EmployeeDto employeeDto = employeeModel.getData(employeeId);

            txtFirstName.setText(employeeDto.getFirst_Name());
            txtLastName.setText(employeeDto.getLast_Name());
            txtNic.setText(employeeDto.getNic());
            txtContactNo.setText(employeeDto.getContact_No());
            txtHouseNo.setText(employeeDto.getHouse_No());
            txtCity.setText(employeeDto.getCity());
            txtStreet.setText(employeeDto.getStreet());
            txtEmail.setText(employeeDto.getEmail());
            cmbRole.setValue(employeeDto.getRole());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
        setDataInComboBox();
    }
}
