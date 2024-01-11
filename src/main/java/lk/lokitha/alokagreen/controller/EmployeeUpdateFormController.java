package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.bo.BOFactory;
import lk.lokitha.alokagreen.bo.custom.EmployeeBO;
import lk.lokitha.alokagreen.bo.custom.impl.EmployeeBOImpl;
import lk.lokitha.alokagreen.dto.EmployeeDto;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.Regex;
import lk.lokitha.alokagreen.util.Style;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeUpdateFormController implements Initializable {

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
    private JFXButton btnUpdate;

    public static String id;

    private final EmployeeBO employeeBO = (EmployeeBOImpl) BOFactory.getBoFactory().getBO( BOFactory.BOType.EMPLOYEE );

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if ( validateEmployee() ) {
            try {
                boolean isSaved = employeeBO.updateEmployee( new EmployeeDto(
                        id,
                        txtFirstName.getText(),
                        txtLastName.getText(),
                        txtNic.getText(),
                        txtNo.getText (),
                        txtStreet.getText(),
                        txtCity.getText(),
                        txtMobile.getText(),
                        txtEmail.getText(),
                        cmbRole.getSelectionModel().getSelectedItem(),
                        null
                ) );

                if (isSaved) {
                    Navigation.closePane();
                    EmployeeManageFormController.controller.getAllId();
                }

            } catch (SQLException e) {
                throw new RuntimeException( e );
            }
        }
    }

    @FXML
    void cmbRoleOnMouseClicked(MouseEvent event) {
        lblRole.setText(null);
    }

    @FXML
    void cmbRoleOnAction(ActionEvent event) {

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
        String city = txtCity.getText();

        if (Regex.city(city)) {
            lblAddress.setText("Please enter valid city");
        }
    }

    @FXML
    void txtEmailOnMouseClicked(MouseEvent event) {
        lblEmail.setText(null);
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
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

    private void setData(EmployeeDto employeeDto) {
        txtFirstName.setText(employeeDto.getFirst_Name());
        txtLastName.setText(employeeDto.getLast_Name());
        txtNic.setText(employeeDto.getNic());
        cmbRole.setValue(employeeDto.getRole());
        txtMobile.setText(employeeDto.getMobile());
        txtEmail.setText(employeeDto.getEmail());
        txtNo.setText(employeeDto.getHouse_No());
        txtStreet.setText(employeeDto.getStreet());
        txtCity.setText(employeeDto.getCity());
    }

    public void setDataInComboBox() {
        cmbRole.setStyle("-fx-font-size: 16;");
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Manager");
        roles.add("System Manager");
        roles.add("Field Staff");
        roles.add("Shop Staff");
        roles.add("Other");

        cmbRole.getItems().addAll(roles);
    }

    @FXML
    void btnUpdateOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEntered ( btnUpdate );
    }

    @FXML
    void btnUpdateOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExited ( btnUpdate );
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        Style.btnOnMouseEnteredWithBorder ( btnCancel );
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        Style.btnOnMouseExitedWithBorder ( btnCancel );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        EmployeeDto eDto = null;
        try {
            eDto = employeeBO.getEmployeeData( id );
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }

        setDataInComboBox();
        setData(eDto);
    }
}
