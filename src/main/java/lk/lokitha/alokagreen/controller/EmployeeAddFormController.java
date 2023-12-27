package lk.lokitha.alokagreen.controller;

import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.EmployeeBO;
import lk.lokitha.alokagreen.bo.custom.impl.EmployeeBOImpl;
import lk.lokitha.alokagreen.dto.EmployeeDto;
import lk.lokitha.alokagreen.util.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeAddFormController implements Initializable {

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtMobile;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtNo;

    @FXML
    private JFXTextField txtStreet;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXComboBox<String> cmbRole;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblNic;

    @FXML
    private Label lblRole;

    @FXML
    private Label lblMobile;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblAddress;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAdd;

    private final EmployeeBO employeeBO = (EmployeeBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.EMPLOYEE );

    @FXML
    void btnAddOnAction(ActionEvent event) {

        if (validateEmployee()) {
            try {
                boolean isSaved = employeeBO.saveEmployee( new EmployeeDto(
                        null,
                        txtFirstName.getText(),
                        txtLastName.getText(),
                        txtNic.getText(),
                        txtNo.getText(),
                        txtStreet.getText(),
                        txtCity.getText(),
                        txtMobile.getText(),
                        txtEmail.getText(),
                        getRole(),
                        null
                ) );

                if (isSaved) {
                    Navigation.closePane();
                    EmployeeManageFormController.controller.getAllId();
                } else {
                    new  Alert(Alert.AlertType.ERROR, "Employee Registration Failed!").show();
                }

            } catch (SQLException | IOException | WriterException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void cmbRoleOnMouseClicked(MouseEvent event) {
        lblRole.setText(null);
    }

    @FXML
    void cmbRoleOnAction(ActionEvent event) {
        lblRole.setText(null);

        if (cmbRole.getSelectionModel().getSelectedItem() == null) {
            lblRole.setText("Please select a role for employee");
        } else {
            txtMobile.requestFocus();
        }
    }

    @FXML
    void txtCityOnMouseClicked(MouseEvent event) {
        lblAddress.setText(null);
    }

    @FXML
    void txtCityOnAction(ActionEvent event) {
        lblAddress.setText(null);

        String city = txtCity.getText();

        if (Regex.city(city)) {
            lblAddress.setText("Please enter valid city");
        } else {
            btnAddOnAction(event);
        }
    }

    @FXML
    void txtEmailOnMouseClicked(MouseEvent event) {
        lblEmail.setText(null);
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        lblEmail.setText(null);

        String email = txtEmail.getText();

        if (Regex.email(email)) {
            lblEmail.setText("Please enter valid email");
        } else {
            txtNo.requestFocus();
        }
    }

    @FXML
    void txtFirstNameOnMouseClicked(MouseEvent event) {
        lblFirstName.setText(null);
    }

    @FXML
    void txtFirstNameOnAction(ActionEvent event) {
        lblFirstName.setText(null);

        String firstName = txtFirstName.getText();

        if (Regex.name(firstName)) {
            lblFirstName.setText("Should contain at least 3 letters");
        } else {
            txtLastName.requestFocus();
        }
    }

    @FXML
    void txtLastNameOnMouseClicked(MouseEvent event) {
        lblLastName.setText(null);
    }

    @FXML
    void txtLastNameOnAction(ActionEvent event) {
        lblLastName.setText(null);

        String lastName = txtLastName.getText();

        if (Regex.name(lastName)) {
            lblLastName.setText("Should contain at least 3 letters");
        } else {
            txtNic.requestFocus();
        }
    }

    @FXML
    void txtMobileOnMOuseClicked(MouseEvent event) {
        lblMobile.setText(null);
    }

    @FXML
    void txtMobileOnAction(ActionEvent event) {
        lblMobile.setText(null);

        String mobile = txtMobile.getText();

        if (Regex.mobile(mobile)) {
            lblMobile.setText("Please enter valid mobile number");
        } else {
            txtEmail.requestFocus();
        }
    }

    @FXML
    void txtNicOnMouseClicked(MouseEvent event) {
        lblNic.setText(null);
    }

    @FXML
    void txtNicOnAction(ActionEvent event) {
        lblNic.setText(null);

        String nic = txtNic.getText();

        if (Regex.nic(nic)) {
            lblNic.setText("Please enter valid NIC");
        } else {
            cmbRole.requestFocus();
        }
    }

    @FXML
    void txtNoOnMOuseCicked(MouseEvent event) {
        lblAddress.setText(null);
    }

    @FXML
    void txtNoOnAction(ActionEvent event) {
        lblAddress.setText(null);

        String houseNo = txtNo.getText();

        if (Regex.houseNo(houseNo)) {
            lblAddress.setText("Please enter valid house number");
        } else {
            txtStreet.requestFocus();
        }
    }

    @FXML
    void txtStreetOnMouseClicked(MouseEvent event) {
        lblAddress.setText(null);
    }

    @FXML
    void txtStreetOnAction(ActionEvent event) {
        lblAddress.setText(null);

        String street = txtStreet.getText();

        if (Regex.street(street)) {
            lblAddress.setText("Please enter valid street");
        } else {
            txtCity.requestFocus();
        }
    }

    public boolean validateEmployee() {
        String firstName = txtFirstName.getText();

        if (Regex.name(firstName)) {
            lblFirstName.setText("Should contain at least 3 letters");
            return false;
        }

        String lastName = txtLastName.getText();

        if (Regex.name(lastName)) {
            lblLastName.setText("Should contain at least 3 letters");
            return false;
        }

        String nic = txtNic.getText();

        if (Regex.nic(nic)) {
            lblNic.setText("Please enter valid NIC");
            return false;
        }

        if (cmbRole.getSelectionModel().getSelectedItem() == null) {
            lblRole.setText("Please select a role for employee");
            return false;
        }

        String mobile = txtMobile.getText();

        if (Regex.mobile(mobile)) {
            lblMobile.setText("Please enter valid mobile number");
            return false;
        }

        String email = txtEmail.getText();

        if (Regex.email(email)) {
            lblEmail.setText("Please enter valid email");
            return false;
        }

        String houseNo = txtNo.getText();

        if (Regex.houseNo(houseNo)) {
            lblAddress.setText("Please enter valid house number");
            return false;
        }

        String street = txtStreet.getText();

        if (Regex.street(street)) {
            lblAddress.setText("Please enter valid street");
            return false;
        }

        String city = txtCity.getText();

        if (Regex.city(city)) {
            lblAddress.setText("Please enter valid city");
            return false;
        }

        return true;
    }

    public String getRole() {
        return String.valueOf(cmbRole.getSelectionModel().getSelectedItem());
    }

    public void setDataInComboBox() {
        cmbRole.getItems().addAll(employeeBO.getEmployeeRoles());
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #1DBC5D;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        btnAdd.setStyle(
                "-fx-background-color: #139547;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-text-fill:  #FFFFFF;");
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #C7FFDE;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #139547;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #139547;");
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        btnCancel.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-color: #727374;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 15px;" +
                        "-fx-text-fill:  #727374;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbRole.setStyle("-fx-font-size: 16;");
        setDataInComboBox();
    }
}
