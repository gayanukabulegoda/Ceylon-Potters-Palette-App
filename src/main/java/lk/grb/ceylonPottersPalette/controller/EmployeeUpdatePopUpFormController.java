package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.util.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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

    @FXML
    private Label lblCityAlert;

    @FXML
    private Label lblCmbRoleAlert;

    @FXML
    private Label lblContactNoAlert;

    @FXML
    private Label lblEmailAlert;

    @FXML
    private Label lblFirstNameAlert;

    @FXML
    private Label lblHouseNoAlert;

    @FXML
    private Label lblLastNameAlert;

    @FXML
    private Label lblNicAlert;

    @FXML
    private Label lblStreetAlert;

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

        if (validateEmployee()) {
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
    }

    private boolean validateEmployee() {

        boolean validateFirstName = Pattern.matches("([A-Z][a-z]{2,})", txtFirstName.getText());
        if (!validateFirstName) {
            lblFirstNameAlert.setText("Invalid First name!!");
            return false;
        }

        boolean validateLastName = Pattern.matches("([A-Z][a-z]{2,})", txtLastName.getText());
        if (!validateLastName) {
            lblLastNameAlert.setText("Invalid Last Name!!");
            return false;
        }

        boolean validateNic = Pattern.matches("([\\dV]{10,12})", txtNic.getText());
        if (!validateNic) {
            lblNicAlert.setText("Invalid NIC!!");
            return false;
        }

        if ((cmbRole.getSelectionModel().getSelectedItem()) == null) {
            lblCmbRoleAlert.setText("Select a Role!!");
            return false;
        }

        boolean validateContactNo = Pattern.matches("([0]\\d{1,9})", txtContactNo.getText());
        if (!validateContactNo) {
            lblContactNoAlert.setText("Invalid Contact Number!!");
            return false;
        }

        boolean validateEmail = Pattern.matches("([A-Za-z0-9]{3,}@[A-Za-z]{3,}\\.[A-Za-z]{1,})", txtEmail.getText());
        if (!validateEmail) {
            lblEmailAlert.setText("Invalid Email!!");
            return false;
        }

        boolean validateHouseNo = Pattern.matches("([\\d]{1,})", txtHouseNo.getText());
        if (!validateHouseNo) {
            lblHouseNoAlert.setText("Invalid House No!!");
            return false;
        }

        boolean validateStreet = Pattern.matches("[A-Za-z\\s]{3,}", txtStreet.getText());
        if (!validateStreet) {
            lblStreetAlert.setText("Invalid Street Name!!");
            return false;
        }

        boolean validateCity = Pattern.matches("[A-Za-z\\s]{3,}", txtCity.getText());
        if (!validateCity) {
            lblCityAlert.setText("Invalid City Name!!");
            return false;
        }
        return true;
    }

    @FXML
    void cityOnMouseClick(MouseEvent event) {
        lblCityAlert.setText(" ");
    }

    @FXML
    void cmbRoleOnMouseClick(MouseEvent event) {
        lblCmbRoleAlert.setText(" ");
    }

    @FXML
    void contactNoOnMouseClick(MouseEvent event) {
        lblContactNoAlert.setText(" ");
    }

    @FXML
    void emailOnMouseClick(MouseEvent event) {
        lblEmailAlert.setText(" ");
    }

    @FXML
    void firstNameOnMouseClick(MouseEvent event) {
        lblFirstNameAlert.setText(" ");
    }

    @FXML
    void houseNoOnMouseClick(MouseEvent event) {
        lblHouseNoAlert.setText(" ");
    }

    @FXML
    void lastNameOnMouseClick(MouseEvent event) {
        lblLastNameAlert.setText(" ");
    }

    @FXML
    void nicOnMouseClick(MouseEvent event) {
        lblNicAlert.setText(" ");
    }

    @FXML
    void streetOnMouseClick(MouseEvent event) {
        lblStreetAlert.setText(" ");
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
