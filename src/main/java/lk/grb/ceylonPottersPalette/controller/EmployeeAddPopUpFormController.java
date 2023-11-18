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
import lk.grb.ceylonPottersPalette.util.DateTimeUtil;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.NewId;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeAddPopUpFormController implements Initializable {

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
    public JFXComboBox<String> cmbRole;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblAdd;

    @FXML
    private Label lblCancel;

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

    public static EmployeeAddPopUpFormController controller;
    EmployeeModel employeeModel = new EmployeeModel();

    public EmployeeAddPopUpFormController() {
        controller = this;
    }

    public static EmployeeAddPopUpFormController getInstance() {
        return controller;
    }

    public String getRole() {
        return String.valueOf(cmbRole.getSelectionModel().getSelectedItem());
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

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {
        EmployeeDto employeeDto = new EmployeeDto();

        ArrayList<String> list = employeeModel.getAllEmployeeId();

        employeeDto.setEmployee_Id(NewId.newId(list, NewId.GetType.EMPLOYEE));
        employeeDto.setFirst_Name(txtFirstName.getText());
        employeeDto.setLast_Name(txtLastName.getText());
        employeeDto.setNic(txtNic.getText());
        employeeDto.setHouse_No(txtHouseNo.getText());
        employeeDto.setStreet(txtStreet.getText());
        employeeDto.setCity(txtCity.getText());
        employeeDto.setContact_No(txtContactNo.getText());
        employeeDto.setEmail(txtEmail.getText());
        employeeDto.setRole(getRole());
        employeeDto.setDate(DateTimeUtil.dateNow());
        employeeDto.setTime(DateTimeUtil.timeNow());
        employeeDto.setUserName(GlobalFormController.user);

        boolean save = employeeModel.save(employeeDto);

        if (save) {
            Navigation.closePane();
            EmployeeManageFormController.getInstance().allEmployeeId();
        }
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
    void cmbRoleOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDataInComboBox();
    }
}
