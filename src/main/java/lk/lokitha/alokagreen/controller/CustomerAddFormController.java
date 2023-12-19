package lk.lokitha.alokagreen.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lk.lokitha.alokagreen.dto.CustomerDto;
import lk.lokitha.alokagreen.model.CustomerModel;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.Navigation;
import lk.lokitha.alokagreen.util.NewId;
import lk.lokitha.alokagreen.util.Regex;

import java.util.regex.Pattern;

public class CustomerAddFormController {

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtCustMobile;

    @FXML
    private JFXTextField txtCustEmail;

    @FXML
    private JFXTextField txtCustAddress;

    @FXML
    private Label lblCustName;

    @FXML
    private Label lblCustMobile;

    @FXML
    private Label lblCustEmail;

    @FXML
    private Label lblCustAddress;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAdd;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        if (validateCustomer()) {
            CustomerDto customerDto = new CustomerDto();

            customerDto.setCustomer_Id(NewId.newCustomerId());
            customerDto.setName(txtCustName.getText());
            customerDto.setMobile(txtCustMobile.getText());
            customerDto.setEmail(txtCustEmail.getText());
            customerDto.setAddress(txtCustAddress.getText());
            customerDto.setDate(DateTime.dateNow());
            customerDto.setTime(DateTime.timeNow());

            boolean isSaved = CustomerModel.saveCustomer(customerDto);

            if (isSaved) {
                Navigation.closePane();
                CustomerManageFormController.controller.getAllId();
            }
        }
    }

    @FXML
    void txtcustAddressOnMouseClicked(MouseEvent event) {
        lblCustAddress.setText(null);
    }

    @FXML
    void txtCustAddressOnAction(ActionEvent event) {
        lblCustAddress.setText(null);

        String address = txtCustAddress.getText();

        if (Regex.address(address)) {
            lblCustAddress.setText("Please enter valid address");
        } else {
            btnAddOnAction(event);
        }
    }

    @FXML
    void txtcustEmailOnMouseClicked(MouseEvent event) {
        lblCustEmail.setText(null);
    }

    @FXML
    void txtCustEmailOnAction(ActionEvent event) {
        lblCustEmail.setText(null);

        String email = txtCustEmail.getText();

        if (Regex.email(email)) {
            lblCustEmail.setText("Please enter valid email");
        } else {
            txtCustAddress.requestFocus();
        }
    }

    @FXML
    void txtcustMobileOnMouseClicked(MouseEvent event) {
        lblCustMobile.setText(null);
    }

    @FXML
    void txtCustMobileOnAction(ActionEvent event) {
        lblCustMobile.setText(null);

        String mobile = txtCustMobile.getText();

        if (Regex.mobile(mobile)) {
            lblCustMobile.setText("Please enter valid mobile number");
        } else {
            txtCustEmail.requestFocus();
        }
    }

    @FXML
    void txtcustNameMouseClicked(MouseEvent event) {
        lblCustName.setText(null);
    }

    @FXML
    void txtCustNameOnAction(ActionEvent event) {
        lblCustName.setText(null);

        String name = txtCustName.getText();

        if (Regex.fullName(name)) {
            lblCustName.setText("Name should contain at least 3 letters and only letters");
        } else {
            txtCustMobile.requestFocus();
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    public boolean validateCustomer() {
        String name = txtCustName.getText();

        if (Regex.fullName(name)) {
            lblCustName.setText("Name should contain at least 3 letters and only letters");
            return false;
        }

        String mobile = txtCustMobile.getText();

        if (Regex.mobile(mobile)) {
            lblCustMobile.setText("Please enter valid mobile number");
            return false;
        }

        String email = txtCustEmail.getText();

        if (Regex.email(email)) {
            lblCustEmail.setText("Please enter valid email");
            return false;
        }

        String address = txtCustAddress.getText();

        if (Regex.address(address)) {
            lblCustAddress.setText("Please enter valid address");
            return false;
        }

        return true;
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

}
