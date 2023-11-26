package lk.grb.ceylonPottersPalette.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteUserFormController implements Initializable {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnCloseIcon;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private Pane deleteBtnPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblRole;

    @FXML
    private Label lblUsername;

    UserModel userModel = new UserModel();
    EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        StyleUtil.cancelBtnSelected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        StyleUtil.cancelBtnUnselected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
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
    void btnDeleteOnAction(ActionEvent event) throws SQLException, IOException {
        boolean delete = userModel.delete(GlobalFormController.user);
        if (delete) {
            Navigation.close(event);
            Navigation.switchNavigation("loginForm.fxml", event);
        }
    }

    @FXML
    void btnDeleteOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(deleteBtnPane);
    }

    @FXML
    void btnDeleteOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(deleteBtnPane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblUsername.setText(GlobalFormController.user);

        try {
            lblEmployeeId.setText(userModel.getEmployeeId(GlobalFormController.user));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            lblEmployeeName.setText(employeeModel.getEmployeeName(lblEmployeeId.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            lblRole.setText(employeeModel.getEmployeeRole(lblEmployeeId.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
