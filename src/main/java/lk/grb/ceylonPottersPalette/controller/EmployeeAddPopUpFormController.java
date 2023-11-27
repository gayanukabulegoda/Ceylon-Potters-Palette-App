package lk.grb.ceylonPottersPalette.controller;

import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.qr.QrGenerator;
import lk.grb.ceylonPottersPalette.util.DateTimeUtil;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.NewId;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    void btnAddOnAction(ActionEvent event) throws SQLException, IOException, WriterException {

        if(true) {
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
                QrGenerator.generateQr(employeeDto.getEmployee_Id());
                Navigation.closePane();
                EmployeeManageFormController.getInstance().allEmployeeId();
            }
        }
    }

    private boolean validateEmployee() {

        boolean validateFirstName = Pattern.matches("([A-Z][a-z]{2,})", txtFirstName.getText());
        if (!validateFirstName) {
            lblFirstNameAlert.setText("Invalid First Name!!");
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
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
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
        setDataInComboBox();
    }
}
