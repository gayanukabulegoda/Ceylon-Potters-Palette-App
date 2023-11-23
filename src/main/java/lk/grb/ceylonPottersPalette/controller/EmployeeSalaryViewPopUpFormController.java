package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.dto.EmployeeSalaryDto;
import lk.grb.ceylonPottersPalette.model.EmployeeSalaryModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeSalaryViewPopUpFormController implements Initializable {

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private Pane btnClosePane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblBonus;

    @FXML
    private Label lblClose;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private Label lblPaymentDate;

    @FXML
    private Label lblPaymentTime;

    @FXML
    private Label lblSalary;

    @FXML
    private Label lblTotalPayment;

    public static String salaryId;

    EmployeeSalaryModel employeeSalaryModel = new EmployeeSalaryModel();

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Navigation.closePane();
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
    void btnCloseOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(btnClosePane);
    }

    @FXML
    void btnCloseOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(btnClosePane);
    }

    public void setData() throws SQLException {

        EmployeeSalaryDto employeeSalaryDto = employeeSalaryModel.getData(salaryId);

        lblEmployeeId.setText(employeeSalaryDto.getEmployee_Id());
        lblSalary.setText(String.valueOf(employeeSalaryDto.getSalary()));
        lblBonus.setText(String.valueOf(employeeSalaryDto.getBonus()));
        lblTotalPayment.setText(String.valueOf(employeeSalaryDto.getTotal_Payment()));
        lblPaymentDate.setText(employeeSalaryDto.getDate());
        lblPaymentTime.setText(employeeSalaryDto.getTime());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
